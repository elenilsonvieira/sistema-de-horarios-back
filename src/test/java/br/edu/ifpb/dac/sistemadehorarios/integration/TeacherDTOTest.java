package br.edu.ifpb.dac.sistemadehorarios.integration;

import br.edu.ifpb.dac.sistemadehorarios.DTO.ProfessorDTO;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Shift.ShiftEnum;
import br.edu.ifpb.dac.sistemadehorarios.entity.Professor.ProfessorModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Profile.ProfileModel;
import br.edu.ifpb.dac.sistemadehorarios.interfaces.DTOTest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TeacherDTOTest implements DTOTest {

    private static ProfileModel profileModel = new ProfileModel();

    private static ProfessorModel professorModel = new ProfessorModel();
    @BeforeAll
    public static void setUp() {
        profileModel.setStandard(1);
        profileModel.setField("Professor");

        professorModel.setProfileModel(profileModel);
        professorModel.setName("Tiago");

    }

    @Test
    @Override
    @DisplayName("Test Conversion Model to DTO")
    public void convert(){
        List<ProfessorModel> listTeatcher = new ArrayList<ProfessorModel>();

        professorModel.setProfileModel(profileModel);

        listTeatcher.add(professorModel);

        List<ProfessorDTO> listDTO = new ArrayList<ProfessorDTO>();
        listDTO.add(new ProfessorDTO(professorModel));

        assertEquals(listDTO.get(0).getUuid() , ProfessorDTO.convert(listTeatcher).get(0).getUuid());
        assertEquals(listDTO.get(0).getName() , ProfessorDTO.convert(listTeatcher).get(0).getName());
        assertEquals(listDTO.get(0).getProfile().getStandard() , ProfessorDTO.convert(listTeatcher).get(0).getProfile().getStandard());
        assertEquals(listDTO.get(0).getProfile().getField() , ProfessorDTO.convert(listTeatcher).get(0).getProfile().getField());
        assertNotEquals(-1, ProfessorDTO.convert(listTeatcher).get(0).getUuid());
    }
}
