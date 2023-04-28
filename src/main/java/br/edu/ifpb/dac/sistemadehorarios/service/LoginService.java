package br.edu.ifpb.dac.sistemadehorarios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

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

    public UserModel login(String enrollment, String password) {
        try {
            if (userService.existsByEnrollment(enrollment)) {
                // Will throw exception if SUAP login fails
                this.suapToken = suapLogin(enrollment, password);
                return userService.findByEnrollment(enrollment);
            }
            throw new Exception("Você não tem permissão para acessar esse sistema");
        } catch (Exception error) {
            return null;
        }
    }

    private String suapLogin(String enrollment, String password) {
        String token = suapService.login(enrollment, password);
        if (token == null) {
            throw new IllegalArgumentException("Incorrect Email or Password");
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
