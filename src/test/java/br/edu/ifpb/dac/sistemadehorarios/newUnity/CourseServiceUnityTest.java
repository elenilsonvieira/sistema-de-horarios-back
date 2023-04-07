package br.edu.ifpb.dac.sistemadehorarios.newUnity;

import br.edu.ifpb.dac.sistemadehorarios.entity.Course.CourseModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Course.CourseService;
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
public class CourseServiceUnityTest {

    @Autowired
    private CourseService courseService;

    private static CourseModel courseModel;

    @BeforeAll
    public static void setUp() {
        courseModel = new CourseModel();
        courseModel.setName("Curso 1");
        courseModel.setUuid("id-test");
        courseModel.setCreate_at(new Date());
    }


    @Test
    @Order(1)
    @DisplayName("should be created a new course")
    public void createNewProfile() {
        try {
            assertNotEquals(courseService.create(courseModel), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(2)
    @DisplayName("should be listed courses")
    public void readProfiles() {
        try {
            List<CourseModel> courseModels = courseService.read();
            courseModels.add(courseModel);
            assertNotEquals(courseModels.size(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(3)
    @DisplayName("should be founded a course")
    public void findOneProfileById() {
        try {
            String id = "id-test";
            assertNotEquals(courseService.findByUuid(id), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(4)
    @DisplayName("should be updated a course")
    public void updateOneProfileById() {
        try {
            String id = "id-test";
            courseModel.setName("Curso 2");
            assertNotEquals(courseService.update(courseModel, id), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(5)
    @DisplayName("should be deleted a course")
    public void deleteOneProfileById() {
        try {
            String id = "id-test";
            assertNotEquals(courseService.delete(id), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
