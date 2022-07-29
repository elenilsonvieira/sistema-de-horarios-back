package br.edu.ifpb.dac.sistemadehorarios.entity.User;

import br.edu.ifpb.dac.sistemadehorarios.entity.User.security.EncryptPasswordSecurity;
import br.edu.ifpb.dac.sistemadehorarios.exception.UserInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.template.ServiceTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceTemplate implements UserDetailsService {


    private final EncryptPasswordSecurity encryptPasswordSecurity;
    private final UserRepository repository;

    @Value("${password.victor}")
    private String passwordKarlos;
    @Value("${password.karlos}")
    private String passwordVictor;

    public UserService(EncryptPasswordSecurity encryptPasswordSecurity, UserRepository repository) {
        this.encryptPasswordSecurity = encryptPasswordSecurity;
        this.repository = repository;
    }

    public void createDefaultValues() throws UserInvalidException {


        UserModel victor = findByEmail("victor.queiroz@academico.ifpb.edu.br");
        UserModel karlos = findByEmail("karlos.macedo@academico.ifpb.edu.br");

        if(victor == null){
            victor = new UserModel();
            victor.setPassword(this.passwordVictor);
            victor.setName("João Victor Lacerda de Queiroz");
            victor.setEmail("victor.queiroz@academico.ifpb.edu.br");
            this.create(victor);
        }
        if(karlos == null){
            karlos = new UserModel();
            karlos.setPassword(this.passwordKarlos);
            karlos.setName("Karlos Macedo");
            karlos.setEmail("karlos.macedo@academico.ifpb.edu.br");
            this.create(karlos);
        }

    }

    public UserModel create(UserModel userModel) throws UserInvalidException {
        try{
            String password = this.encryptPasswordSecurity.encryptPassWord(userModel.getPassword());
            userModel.setPassword(password);
            super.create(userModel, repository);
            return userModel;
        }catch (Exception error){
            throw new UserInvalidException("User é inválido "+error.getMessage(), 400);
        }
    }

    public UserModel findByEmail(String email){
        try {
            UserModel user = this.repository.findByEmail(email);
            return user;
        }catch (Exception error){
            return null;
        }
    }

    @Override
    public UserModel loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = findByEmail(username);

        if(userModel == null){
            throw new UsernameNotFoundException("O username não foi encontrado. User: "+ username);
        }
        return userModel;
    }

    public UserModel findByUuid(String uuid){
        return (UserModel) super.findByUuid(uuid, this.repository);
    }
}
