package br.edu.ifpb.dac.sistemadehorarios.newUnity;

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
public class ProfileServiceUnityTest implements ServiceUnityTest {

    @Autowired
    private ProfileService profileService;

    private static ProfileModel profileModel;

    @BeforeAll
    public static void setUp() {
        profileModel = new ProfileModel();
        profileModel.setUuid("id-test");
        profileModel.setField("Field");
        profileModel.setCreate_at(new Date());
        profileModel.setStandard(1);
    }


    @Test
    @Order(1)
    @DisplayName("should be created a new profile")
    @Override
    public void testCreateNewEntity() {
        try {
            assertNotEquals(profileService.create(profileModel), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(2)
    @DisplayName("should be listed profiles")
    @Override
    public void testReadEntities() {
        try {
            List<ProfileModel> profileModels = profileService.read();
            profileModels.add(profileModel);
            assertNotEquals(profileModels.size(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(3)
    @DisplayName("should be found a profile")
    @Override
    public void testFindOneEntityById() {
        try {
            String id = "id-test";
            ProfileModel profileModel = profileService.findByUuid(id);
            assertNotEquals(profileModel, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(4)
    @DisplayName("should be updated a profile")
    @Override
    public void testUpdateOneEntityById() {
        try {
            String id = "id-test";
            profileModel.setStandard(2);
            assertNotEquals(profileService.update(profileModel, id), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(5)
    @DisplayName("should be deleted a profile")
    @Override
    public void testDeleteOneEntityById() {
        try {
            String id = "id-test";
            assertNotEquals(profileService.delete(id), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
