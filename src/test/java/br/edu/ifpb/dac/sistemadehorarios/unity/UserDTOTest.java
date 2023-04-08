package br.edu.ifpb.dac.sistemadehorarios.unity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import br.edu.ifpb.dac.sistemadehorarios.DTO.UserDTO;
import br.edu.ifpb.dac.sistemadehorarios.entity.User.UserModel;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

public class UserDTOTest {
    @Test
    @DisplayName("Conversion Test")
    public void convertTest(){
        List<UserModel> listUser = new ArrayList<UserModel>();
        UserModel user = new UserModel();
        user.setName("Fulano");
        user.setEnrollment("12345678");
        listUser.add(user);

        List<UserDTO> listDTO = new ArrayList<UserDTO>();
        listDTO.add(new UserDTO(user));

        assertEquals(listDTO.get(0).getName() , UserDTO.convert(listUser).get(0).getName());
        assertEquals(listDTO.get(0).getEnrollment() , UserDTO.convert(listUser).get(0).getEnrollment());
        assertEquals(listDTO.get(0).getToken() , UserDTO.convert(listUser).get(0).getToken());

    }
}
