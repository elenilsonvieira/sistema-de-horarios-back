package br.edu.ifpb.dac.sistemadehorarios.entity.User.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
@Setter
public class LoginDRO {
    private String email;
    private String pass;

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(email, pass);
    }
}
