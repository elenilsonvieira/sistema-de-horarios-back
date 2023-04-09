package br.edu.ifpb.dac.sistemadehorarios.unity;

import br.edu.ifpb.dac.sistemadehorarios.DTO.ProfessorDTO;
import br.edu.ifpb.dac.sistemadehorarios.entity.Professor.ProfessorModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Profile.ProfileModel;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeacherDTOTest {

    @Test
    @DisplayName("Test Conversion Model to DTO")
    public void convertTest(){
        List<ProfessorModel> listTeatcher = new ArrayList<ProfessorModel>();
        ProfessorModel teatcherModel = new ProfessorModel();
        ProfileModel profileModel = new ProfileModel();
        profileModel.setField("Professor");
        profileModel.setStandard(1);
        teatcherModel.setName("Tiago");
        teatcherModel.setProfileModel(profileModel);

        listTeatcher.add(teatcherModel);

        List<ProfessorDTO> listDTO = new ArrayList<ProfessorDTO>();
        listDTO.add(new ProfessorDTO(teatcherModel));

        assertEquals(listDTO.get(0).getUuid() , ProfessorDTO.convert(listTeatcher).get(0).getUuid());
        assertEquals(listDTO.get(0).getName() , ProfessorDTO.convert(listTeatcher).get(0).getName());
        assertEquals(listDTO.get(0).getProfile().getStandard() , ProfessorDTO.convert(listTeatcher).get(0).getProfile().getStandard());
        assertEquals(listDTO.get(0).getProfile().getField() , ProfessorDTO.convert(listTeatcher).get(0).getProfile().getField());
    }
}
