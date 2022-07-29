package br.edu.ifpb.dac.sistemadehorarios.DTO;

import br.edu.ifpb.dac.sistemadehorarios.entity.Turma.TurmaModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.User.UserModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserDTO {

    private String uuid;
    private String name;
    private String email;

    public UserDTO(UserModel userModel) {
        this.uuid = userModel.getUuid();
        this.name = userModel.getName();
        this.email = userModel.getEmail();
    }

    public static List<UserDTO> convert(List<UserModel> userModel){
        return userModel.stream().map(UserDTO::new).collect(Collectors.toList());
    }
}
