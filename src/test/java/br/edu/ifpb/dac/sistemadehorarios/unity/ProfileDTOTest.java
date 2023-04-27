package br.edu.ifpb.dac.sistemadehorarios.unity;

import br.edu.ifpb.dac.sistemadehorarios.DTO.ProfileDTO;
import br.edu.ifpb.dac.sistemadehorarios.DTO.UserDTO;
import br.edu.ifpb.dac.sistemadehorarios.entity.Profile.ProfileModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.User.UserModel;
import br.edu.ifpb.dac.sistemadehorarios.interfaces.DTOTest;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ProfileDTOTest implements DTOTest {

    @Test
    @Override
    @DisplayName("Test Conversion Model to DTO")
    public void convert(){
        List<ProfileModel> listProfile = new ArrayList<ProfileModel>();
        ProfileModel profileModel = new ProfileModel();
        profileModel.setStandard(1);
        profileModel.setField("12345678");
        listProfile.add(profileModel);

        List<ProfileDTO> listDTO = new ArrayList<ProfileDTO>();
        listDTO.add(new ProfileDTO(profileModel));

        assertEquals(listDTO.get(0).getUuid() , ProfileDTO.convert(listProfile).get(0).getUuid());
        assertEquals(listDTO.get(0).getStandard() , ProfileDTO.convert(listProfile).get(0).getStandard());
        assertEquals(listDTO.get(0).getField() , ProfileDTO.convert(listProfile).get(0).getField());
        assertNotEquals(-1, ProfileDTO.convert(listProfile).get(0).getUuid());
    }
}
