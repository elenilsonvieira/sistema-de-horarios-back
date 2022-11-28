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
        UserModel userModel = this.findByUuid(enrollment);

        if (userModel == null) {
            throw new UsernameNotFoundException("A matrícula não foi encontrada: " + enrollment);
        }
        return userModel;
    }

    public void createDefaultValues() {
        UserModel jan = findByUuid("202025020010");
        UserModel iri = findByUuid("202025020005");

        String fullAcess = String.format("%s,%s,%s,%s",
                RoleEnum.READ,
                RoleEnum.EDIT,
                RoleEnum.CREATE,
                RoleEnum.ADM);

        if (jan == null) {
            jan = new UserModel();
            jan.setEnrollment("202025020010");
            jan.setName("Jan Joris Tomé de Lira");
            jan.setRoles(fullAcess);
            repository.save(jan);
        }
        if (iri == null) {
            iri = new UserModel();
            iri.setEnrollment("202025020005");
            iri.setName("Iriedson Souto Maior de Moraes Vilar");
            iri.setRoles(fullAcess);
            repository.save(iri);
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

    public UserModel findByUuid(String uuid) {
        return (UserModel) super.findByUuid(uuid, this.repository);
    }

    public boolean existsByUuid(String uuid) {
        return repository.existsById(uuid);
    }

    public boolean delete(String uuid) {
        return super.delete(uuid, this.repository);
    }

    public List<UserModel> read() {
        return (List<UserModel>) super.read(this.repository);
    }
}
