package br.edu.ifpb.dac.sistemadehorarios.integration;

import br.edu.ifpb.dac.sistemadehorarios.entity.Classroom.ClassBlock.ClassBlockModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Classroom.ClassBlock.ClassBlockService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Classroom.Classroom.ClassroomDRO;
import br.edu.ifpb.dac.sistemadehorarios.entity.Classroom.Classroom.ClassroomModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Classroom.Classroom.ClassroomService;
import br.edu.ifpb.dac.sistemadehorarios.exception.classroom.ClassBlockInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.exception.classroom.ClassroomInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.interfaces.ServiceTest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClassroomIntegrationTest implements ServiceTest {


    private static ClassroomModel classroomModel;

    @Autowired
    private ClassBlockService classBlockService;
    private static ClassBlockModel classBlockModel;

    @Autowired
    private ClassroomService classroomService;

    private static ClassroomDRO classroomDRO;

    @BeforeAll
    public static void setUp() {
     classBlockModel = new ClassBlockModel();
     classBlockModel.setBlockName("Bloco D");
     classBlockModel.setUuid("test-id");

     classroomModel =  new ClassroomModel();
     classroomModel.setName("Lab 6");
     classroomModel.setUuid("test-id");
     classroomModel.setCapacity(40);
     classroomModel.setClassBlockModel(classBlockModel);

     classroomDRO = new ClassroomDRO();
     classroomDRO.setCapacity(classroomModel.getCapacity());
     classroomDRO.setName(classroomModel.getName());
     classroomDRO.setClassBlockUuid(classBlockModel.getUuid());
    }

    @Test
    @Order(1)
    @DisplayName("Attributes are not null")
    @Override
    public void attributesAreNotNull() {
        assertNotNull(classroomModel.getName());
        assertNotNull(classroomModel.getUuid());
        assertNotNull(classroomModel.getClassBlockModel());
        assertNotNull(classroomModel.getCapacity());

        assertNotNull(classBlockModel.getBlockName());
        assertNotNull(classBlockModel.getUuid());

        assertNotNull(classroomDRO.getCapacity());
        assertNotNull(classroomDRO.getName());
        assertNotNull(classroomDRO.getClassBlockUuid());

    }

    @Test
    @Order(2)
    @DisplayName("should be created a new Classroom")
    @Override
    public void testCreateNewEntity() {

        ClassroomInvalidException err = null;
        ClassBlockInvalidException blockErr = null;

        try{
            classBlockService.create(classBlockModel);
        }catch(ClassBlockInvalidException e){
            blockErr = e;
        }

        try{
            classroomService.create(classroomDRO);
        }catch(ClassroomInvalidException e){
            err = e;
        }



        assertNull(err);
        assertNull(blockErr);

    }

    @Test
    @Order(3)
    @DisplayName("should be listed Classrooms")
    @Override
    public void testReadEntities() {
        List<ClassroomModel> listC = classroomService.read();

        assertEquals("Lab 6", listC.get(0).getName());
        assertNotNull(listC);
    }

    @Test
    @Order(4)
    @DisplayName("should be found a Classroom")
    @Override
    public void testFindOneEntityById() {
        List<ClassroomModel> listC = classroomService.read();
        ClassroomModel classroomModelFinded = classroomService.findByUuid(listC.get(0).getUuid());

        assertEquals("Lab 6", classroomModelFinded.getName());
        assertNotNull(classroomModelFinded);
    }

    @Test
    @Order(5)
    @DisplayName("should be updated a Classroom")
    @Override
    public void testUpdateOneEntityById() {
        List<ClassroomModel> listC = classroomService.read();
        listC.get(0).setName("Lab 5");
        ClassroomModel classroomModelUpdated = classroomService.update(listC.get(0), listC.get(0).getUuid());

        assertEquals("Lab 5", classroomModelUpdated.getName());
        assertNotNull(classroomModelUpdated);
    }

    @Test
    @Order(6)
    @DisplayName("should be deleted a Classroom")
    @Override
    public void testDeleteOneEntityById() {
        List<ClassroomModel> listC = classroomService.read();
        boolean isDeleted = classroomService.delete(listC.get(0).getUuid());

        assertTrue(isDeleted);
    }
}
