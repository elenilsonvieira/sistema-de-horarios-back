package br.edu.ifpb.dac.sistemadehorarios.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
@Setter
public class LoginDRO {
    private String enrollment;
    private String pass;

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(enrollment, pass);
    }
}
