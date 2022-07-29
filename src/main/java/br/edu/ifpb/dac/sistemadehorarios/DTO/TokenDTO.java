package br.edu.ifpb.dac.sistemadehorarios.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenDTO {

    private String token;
    private UserDTO userDto;

    public TokenDTO(String token, UserDTO userDto) {
        this.token = token;
        this.userDto = userDto;
    }
}
