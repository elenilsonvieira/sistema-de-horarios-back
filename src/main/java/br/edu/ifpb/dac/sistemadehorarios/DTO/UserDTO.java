package br.edu.ifpb.dac.sistemadehorarios.DTO;

import br.edu.ifpb.dac.sistemadehorarios.entity.User.UserModel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@Data
public class UserDTO {

    private String uuid;
    private String name;
    private String email;
    private String token;
    private Collection<? extends GrantedAuthority> roles;


    public static List<UserDTO> convert(List<UserModel> userModel){
        List<UserDTO> userDTOList = new ArrayList<>();
        for (UserModel user: userModel) {
            UserDTO userDTO = UserDTO
                    .builder()
                    .uuid(user.getUuid())
                    .email(user.getEmail())
                    .name(user.getName())
                    .build();
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }
}
