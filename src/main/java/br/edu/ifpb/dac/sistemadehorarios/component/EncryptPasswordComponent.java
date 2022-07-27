package br.edu.ifpb.dac.sistemadehorarios.component;

import br.edu.ifpb.dac.sistemadehorarios.entity.User.UserModel;
import br.edu.ifpb.dac.sistemadehorarios.exception.UserInvalidException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EncryptPasswordComponent extends BCryptPasswordEncoder {

    public UserModel encryptPassWord(UserModel userModel) throws UserInvalidException {
        if(userModel.getPassword() != null){
            String encryptedPassword = this.encode(userModel.getPassword());
            userModel.setPassword(encryptedPassword);
            return userModel;
        }
        throw new UserInvalidException("Usuário não pode está sem senha", 400);
    }
}
