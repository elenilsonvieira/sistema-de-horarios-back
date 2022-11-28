package br.edu.ifpb.dac.sistemadehorarios.DTO;

import br.edu.ifpb.dac.sistemadehorarios.entity.User.UserModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class UserDTO {

    private String name;
    private String enrollment;
    private String token;

    public UserDTO(UserModel userModel) {
        this.name = userModel.getName();
        this.enrollment = userModel.getEnrollment();
    }

    public static List<UserDTO> convert(List<UserModel> userModel) {
        List<UserDTO> userDTOList = new ArrayList<>();
        for (UserModel user : userModel) {
            UserDTO userDTO = UserDTO
                    .builder()
                    .name(user.getName())
                    .enrollment(user.getEnrollment())
                    .build();
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }
}
