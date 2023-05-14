package br.edu.ifpb.dac.sistemadehorarios.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import br.edu.ifpb.dac.sistemadehorarios.DTO.UserDTO;
import br.edu.ifpb.dac.sistemadehorarios.entity.User.UserModel;
import br.edu.ifpb.dac.sistemadehorarios.interfaces.DTOTest;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

public class UserDTOTest implements DTOTest {
    @Test
    @Override
    @DisplayName("Test Conversion Model to DTO")
    public void convert(){
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
        assertNotEquals(-1,listDTO.get(0).getName() , UserDTO.convert(listUser).get(0).getName() );
    }
}
