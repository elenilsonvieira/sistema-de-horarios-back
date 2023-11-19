package br.edu.ifpb.dac.sistemadehorarios.service;

import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import br.edu.ifpb.dac.sistemadehorarios.entity.User.RoleEnum;
import br.edu.ifpb.dac.sistemadehorarios.entity.User.UserModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.User.UserService;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class LoginService {

    @Autowired
    private SuapService suapService;
    @Autowired
    private UserService userService;

    private String suapToken;

    public UserModel login(String enrollment, String password) throws Exception{
        if (enrollment == null || password == null) {
            throw new IllegalArgumentException("Matrícula ou Senha inválida");
        }
        String jsonToken = suapLogin(enrollment, password);
        System.out.println("entrei nesse login");
        JsonElement jsonElement = JsonParser.parseString(jsonToken);
        System.out.println("fiz o json");
        System.out.println("json: " + jsonElement.toString());
        String token = jsonElement.getAsJsonObject().get("access").getAsString();
        System.out.println("fiz o token");
        this.suapToken = token;
        if(this.suapToken == null){
            throw new Exception("Você não tem permissão para acessar esse sistema");

        }
       
        UserModel user = new UserModel();
        System.out.println("entrei no try");
        try {
        user = userService.findByEnrollment(enrollment);
        
        } catch (Exception error) {

            System.out.println("entrei no catch");
            String json = suapService.findUser(token, enrollment);
            System.out.println("fds");
            user = userService.convertJSonUserModel(json);
            System.out.println("pqp");
            String fullAcess = String.format("%s,%s,%s,%s",
                    RoleEnum.READ,
                    RoleEnum.EDIT,
                    RoleEnum.CREATE,
                    RoleEnum.ADM);
            user.setRoles(fullAcess);
            System.out.println("user: " + user.getName() + " " + user.getEnrollment() + " " + user.getRoles() + " " + user.getUuid() + "");
            userService.create(token, user.getEnrollment(), user.getRoles());
            System.out.println("pqp2");
        }
        System.out.println("user: " + user.getName() + " " + user.getEnrollment() + " " + user.getRoles() + " " + user.getUuid() + "");
        return user;
    }

    private String suapLogin(String enrollment, String password) {
        String token = suapService.login(enrollment, password);
        if (token == null) {
            throw new IllegalArgumentException("Matrícula ou Senha inválida");
        }
        return token;
    }

    public UserModel getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserModel) authentication.getPrincipal();
    }

    public String getSuapToken() {
        return this.suapToken;
    }

}
