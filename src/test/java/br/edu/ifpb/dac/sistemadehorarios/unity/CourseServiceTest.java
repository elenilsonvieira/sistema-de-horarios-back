package br.edu.ifpb.dac.sistemadehorarios.unity;

import br.edu.ifpb.dac.sistemadehorarios.entity.Course.CourseModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Course.CourseService;
import br.edu.ifpb.dac.sistemadehorarios.interfaces.ServiceTest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CourseServiceTest implements ServiceTest {

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
    @Override
    public void testCreateNewEntity() {
        try {
            assertNotEquals(courseService.create(courseModel), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(2)
    @DisplayName("should be listed courses")
    @Override
    public void testReadEntities() {
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
    @DisplayName("should be found a course")
    @Override
    public void testFindOneEntityById() {
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
    @Override
    public void testUpdateOneEntityById() {
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
    @Override
    public void testDeleteOneEntityById() {
        try {
            String id = "id-test";
            assertNotEquals(courseService.delete(id), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
