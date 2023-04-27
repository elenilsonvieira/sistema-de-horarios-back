package br.edu.ifpb.dac.sistemadehorarios.entity.User;

import br.edu.ifpb.dac.sistemadehorarios.exception.UserInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.service.SuapService;
import br.edu.ifpb.dac.sistemadehorarios.template.ServiceTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.google.gson.JsonObject;
import java.util.List;

@Service
public class UserService extends ServiceTemplate implements UserDetailsService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private SuapService suapService;

    @Override
    public UserDetails loadUserByUsername(String enrollment) throws UsernameNotFoundException {
        UserModel userModel = this.findByEnrollment(enrollment);

        if (userModel == null) {
            throw new UsernameNotFoundException("A matrícula não foi encontrada: " + enrollment);
        }
        return userModel;
    }

    public void createDefaultValues() {
        UserModel wes = findByEnrollment("202115020008");
        UserModel art = findByEnrollment("202115020022");

        String fullAcess = String.format("%s,%s,%s,%s",
                RoleEnum.READ,
                RoleEnum.EDIT,
                RoleEnum.CREATE,
                RoleEnum.ADM);

        if (wes == null) {
            wes = new UserModel();
            wes.setEnrollment("202115020008");
            wes.setName("Wesley Alencar Souza");
            wes.setRoles(fullAcess);
            repository.save(wes);
        }
        if (art == null) {
            art = new UserModel();
            art.setEnrollment("202115020022");
            art.setName("Arthur Pereira da silva");
            art.setRoles(fullAcess);
            repository.save(art);
        }
    }

    public UserModel create(String token, String enrollment, String roles) throws UserInvalidException {
        try {
            JsonObject result = suapService.findUser(token, enrollment);

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

    public UserModel findByEnrollment(String enrollment) {
        try {
            return this.repository.findByEnrollment(enrollment);
        } catch (Exception error) {
            return null;
        }
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
