package br.edu.ifpb.dac.sistemadehorarios.newUnity;

import br.edu.ifpb.dac.sistemadehorarios.entity.Professor.ProfessorDRO;
import br.edu.ifpb.dac.sistemadehorarios.entity.Professor.ProfessorModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Professor.ProfessorService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Profile.ProfileModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Profile.ProfileService;
import br.edu.ifpb.dac.sistemadehorarios.interfaces.ServiceUnityTest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProfessorServiceUnityTest implements ServiceUnityTest {

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private ProfileService profileService;

    private static ProfessorDRO professorDRO;

    private static ProfessorModel professorModel;

    @BeforeAll
    public static void setUp() {
        professorDRO = new ProfessorDRO();
        professorDRO.setName("Fernando");
        professorDRO.setProfileUuid("id-test2");

    }


    @Test
    @Order(1)
    @DisplayName("should be created a new professor")
    @Override
    public void testCreateNewEntity() {
        try {
            ProfileModel profileModel = new ProfileModel();
            profileModel.setUuid("id-test2");
            profileModel.setField("Field");
            profileModel.setCreate_at(new Date());
            profileModel.setStandard(1);
            profileService.create(profileModel);

            professorModel = professorService.create(professorDRO);
            assertNotEquals(professorModel, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(2)
    @DisplayName("should be listed professors")
    @Override
    public void testReadAndFundedEntities() {
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
    @DisplayName("should be found a professor")
    @Override
    public void testFindOneEntityById() {
        try {
            assertNotEquals(profileService.findByUuid(professorDRO.getProfileUuid()), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(4)
    @DisplayName("should be updated a professor")
    @Override
    public void testUpdateOneEntityById() {
        try {
            professorModel.setName("Junior");
            assertNotEquals(professorService.update(professorModel, professorModel.getUuid()), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(5)
    @DisplayName("should be deleted a professor")
    @Override
    public void testDeleteOneEntityById() {
        try {
            assertNotEquals(professorService.delete(professorModel.getUuid()), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}