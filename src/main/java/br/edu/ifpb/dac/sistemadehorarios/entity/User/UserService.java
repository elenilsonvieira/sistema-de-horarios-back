package br.edu.ifpb.dac.sistemadehorarios.entity.User;

import br.edu.ifpb.dac.sistemadehorarios.exception.UserInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.service.SuapService;
import br.edu.ifpb.dac.sistemadehorarios.template.ServiceTemplate;

import org.apache.tomcat.jni.User;
import org.openqa.selenium.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.List;

@Service
public class UserService extends ServiceTemplate implements UserDetailsService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private SuapService suapService;

    @Override
    public UserDetails loadUserByUsername(String enrollment) throws UsernameNotFoundException {
        try {
            return this.findByEnrollment(enrollment);
        } catch (Exception error) {
            throw new UsernameNotFoundException("A matrícula não foi encontrada: " + enrollment);
        }
    }

    public void createDefaultValues() {
        UserModel wes = null;
        UserModel art = null;
        try{
            wes = findByEnrollment("202125020013");
            art = findByEnrollment("202115020022");
    
            
        }catch (Exception error){
        String fullAcess = String.format("%s,%s,%s,%s",
                RoleEnum.READ,
                RoleEnum.EDIT,
                RoleEnum.CREATE,
                RoleEnum.ADM);
        wes = new UserModel();
        wes.setEnrollment("202125020013");
        wes.setName("Hicaro Ferreira Brasil");
        wes.setRoles(fullAcess);
        repository.save(wes);
        art = new UserModel();
        art.setEnrollment("202115020022");
        art.setName("Arthur Pereira da silva");
        art.setRoles(fullAcess);
        repository.save(art);

        
        }
    }
    public UserModel convertJSonUserModel (String json) {
        System.out.println("json: " + json);
        JsonObject result = JsonParser.parseString(json).getAsJsonObject();
        System.out.println("result: " + result);
        UserModel user = new UserModel();
        System.out.println("criei o user");
        user.setName(result.get("nome").getAsString());
        System.out.println("setei o nome");
        user.setEnrollment(result.get("matricula").getAsString());
        System.out.println("setei a matricula");
        user.setRoles(RoleEnum.ADM.name());
        
        System.out.println("user: " + user.getName() + " " + user.getEnrollment() + " " + user.getRoles() + " " + user.getUuid() + "");
        return user;
    }

    public UserModel create(String token, String enrollment, String roles) throws UserInvalidException {
        try {
            JsonObject result = JsonParser.parseString(suapService.findUser(token, enrollment)).getAsJsonObject();

            UserModel user = new UserModel();
            user.setName(result.get("nome").getAsString());
            user.setEnrollment(result.get("matricula").getAsString());
            user.setRoles(roles == null
                    ? RoleEnum.READ.name()
                    : roles);

            super.create(user, repository);
            return user;
        } catch (Exception error) {
            throw new UserInvalidException("User é inválido " + error.getMessage(), 400);
        }
    }

    public UserModel findByName(String name) {
        try {
            return this.repository.findByName(name);
        } catch (Exception error) {
            return null;
        }
    }

    public UserModel findByEnrollment(String enrollment) throws Exception{
        if (enrollment == null) {
            throw new IllegalArgumentException("Matrícula inválida");
        }
        UserModel user = repository.findByEnrollment(enrollment);
        if (user == null) {
            throw new Exception("Usuário não encontrado");
        }
        return user;
    }

    public UserModel findByUuid(String uuid) {
        return (UserModel) super.findByUuid(uuid, this.repository);
    }

    public boolean existsByUuid(String uuid) {
        return repository.existsById(uuid);
    }

    public boolean existsByEnrollment(String enrollment) {
        return repository.existsByEnrollment(enrollment);
    }

    public boolean delete(String uuid) {
        return super.delete(uuid, this.repository);
    }

    public List<UserModel> read() {
        return (List<UserModel>) super.read(this.repository);
    }
}
