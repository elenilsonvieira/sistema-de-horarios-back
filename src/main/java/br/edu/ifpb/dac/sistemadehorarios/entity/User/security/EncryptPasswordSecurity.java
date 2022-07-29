package br.edu.ifpb.dac.sistemadehorarios.entity.User.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EncryptPasswordSecurity extends BCryptPasswordEncoder {

    public String encryptPassWord(String  password) {
        return this.encode(password);
    }
}
