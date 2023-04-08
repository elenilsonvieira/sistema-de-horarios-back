package br.edu.ifpb.dac.sistemadehorarios.newUnity;

import br.edu.ifpb.dac.sistemadehorarios.entity.Professor.ProfessorDRO;
import br.edu.ifpb.dac.sistemadehorarios.entity.Professor.ProfessorModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Professor.ProfessorService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Profile.ProfileModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Profile.ProfileService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProfessorServiceUnityTest {

    @Autowired
    private ProfessorService professorService;

    private static ProfessorDRO professorDRO;

    private static ProfessorModel professorModel;

    @BeforeAll
    public static void setUp() {
        professorDRO = new ProfessorDRO();
        professorDRO.setName("Fernando");
        professorDRO.setProfileUuid("id-test");
    }


    @Test
    @Order(1)
    @DisplayName("should be created a new professor")
    public void createNewProfile() {
        try {
            professorModel = professorService.create(professorDRO);
            assertNotEquals(professorModel, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(2)
    @DisplayName("should be listed professors")
    public void readAndFundedProfiles() {
        try {
            List<ProfessorModel> professorModels = professorService.read();
            professorModels.add(professorModel);
            assertNotEquals(professorModels.size(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    @Order(3)
    @DisplayName("should be updated a professor")
    public void updateOneProfileById() {
        try {
            String id = "id-test";
            professorModel.setName("Junior");
            assertNotEquals(professorService.update(professorModel, id), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(4)
    @DisplayName("should be deleted a professor")
    public void deleteOneProfileById() {
        try {
            String id = "id-test";
            System.out.println(professorModel.getUuid());
            assertNotEquals(professorService.delete(id), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}