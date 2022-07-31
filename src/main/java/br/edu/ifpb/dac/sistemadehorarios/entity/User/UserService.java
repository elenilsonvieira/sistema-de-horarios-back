package br.edu.ifpb.dac.sistemadehorarios.entity.User;


import br.edu.ifpb.dac.sistemadehorarios.exception.UserInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.template.ServiceTemplate;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends ServiceTemplate implements UserDetailsService {

    private final UserRepository repository;
    private BCryptPasswordEncoder enconder;

    @Value("${password.karlos}")
    private String passwordKarlos;

    @Value("${password.victor}")
    private String passwordVictor;

    public UserService(UserRepository repository) {
        this.repository = repository;
        this.enconder = new BCryptPasswordEncoder();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = this.findByEmail(username);

        if(userModel == null){
            throw new UsernameNotFoundException("O username não foi encontrado. User: "+ username);
        }
        return userModel;
    }

    public void createDefaultValues() throws UserInvalidException {


        UserModel victor = findByEmail("victor.queiroz@academico.ifpb.edu.br");
        UserModel karlos = findByEmail("karlos.macedo@academico.ifpb.edu.br");

        if(victor == null){
            victor = new UserModel();
            victor.setPass(this.passwordVictor);
            victor.setName("João Victor Lacerda de Queiroz");
            victor.setEmail("victor.queiroz@academico.ifpb.edu.br");
            this.create(victor);
        }
        if(karlos == null){
            karlos = new UserModel();
            karlos.setPass(this.passwordKarlos);
            karlos.setName("Karlos Macedo");
            karlos.setEmail("karlos.macedo@academico.ifpb.edu.br");
            this.create(karlos);
        }

    }

    public UserModel login(String email, String password){
        try{
            UserModel userModel = this.findByEmail(email);
            if(userModel!= null && this.enconder.matches(password, userModel.getPassword())){
                return userModel;
            }
            return null;
        }catch (Exception error){
            return null;
        }
    }

    public UserModel create(UserModel userModel) throws UserInvalidException {
        try{
            String password = this.enconder.encode(userModel.getPassword());
            userModel.setPass(password);
            super.create(userModel, repository);
            return userModel;
        }catch (Exception error){
            throw new UserInvalidException("User é inválido "+error.getMessage(), 400);
        }
    }

    public UserModel findByEmail(String email){
        try {
            return this.repository.findByEmail(email);
        }catch (Exception error){
            return null;
        }
    }

    public UserModel findByUuid(String uuid){
        return (UserModel) super.findByUuid(uuid, this.repository);
    }

    public boolean delete(String uuid){
        return super.delete(uuid, this.repository);
    }
    public List<UserModel> read(){
        return (List<UserModel>) super.read(this.repository);
    }
}
