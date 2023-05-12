package br.edu.ifpb.dac.sistemadehorarios.integration;

import br.edu.ifpb.dac.sistemadehorarios.entity.Course.CourseModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.CurricularComponent.CurricularComponentDRO;
import br.edu.ifpb.dac.sistemadehorarios.entity.CurricularComponent.CurricularComponentModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.CurricularComponent.CurricularComponentService;
import br.edu.ifpb.dac.sistemadehorarios.exception.CurricularComponentInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.interfaces.ServiceTest;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CurricularComponentIntegrationTest implements ServiceTest {

     private static CourseModel courseModel;

     private static CurricularComponentModel curricularComponentModel;

     private static CurricularComponentService curricularComponentService;

     private static CurricularComponentDRO curricularComponentDRO;

    @BeforeAll
    public static void setUp() {
        courseModel = new CourseModel();
        courseModel.setName("Análise e Desenvolvimento de Sistemas");

        curricularComponentModel = new CurricularComponentModel();
        curricularComponentModel.setName("TESTES");
        curricularComponentModel.setWorkload( (byte) 120);
        curricularComponentModel.setCourseUuid(courseModel);

        curricularComponentDRO = new CurricularComponentDRO();
        curricularComponentDRO.setName(curricularComponentModel.getName());
        curricularComponentDRO.setWorkload(curricularComponentModel.getWorkload());
        curricularComponentDRO.setCourseUuid(curricularComponentModel.getCourseUuid().getUuid());
    }

    @Test
    @Order(1)
    @DisplayName("Attributes are not null")
    @Override
    public void attributesAreNotNull() {
        assertNotNull(courseModel.getName());
        assertNotNull(courseModel.getUuid());

        assertNotNull(curricularComponentModel.getName());
        assertNotNull(curricularComponentModel.getUuid());
        assertNotNull(curricularComponentModel.getWorkload());
        assertNotNull(curricularComponentModel.getCourseUuid());

        assertNotNull(curricularComponentDRO.getCourseUuid());
        assertNotNull(curricularComponentDRO.getName());
        assertNotNull(curricularComponentDRO.getWorkload());
    }

    @Test
    @Order(2)
    @DisplayName("should be created a new CurricularComponent")
    @Override
    public void testCreateNewEntity() {
        CurricularComponentInvalidException exception = Assertions.assertThrows(CurricularComponentInvalidException.class, () -> {
            curricularComponentService.create(curricularComponentDRO);
        });

        assertNull("Something exception happened", exception.getMessage());
    }

    @Test
    @Order(3)
    @DisplayName("should be listed CurricularComponents")
    @Override
    public void testReadEntities() {
        List<CurricularComponentModel> listC = curricularComponentService.read();

        assertEquals("TESTES", listC.get(0).getName());
        assertNotNull(listC);
    }

    @Test
    @Order(4)
    @DisplayName("should be found a CurricularComponent")
    @Override
    public void testFindOneEntityById() {
        List<CurricularComponentModel> listC = curricularComponentService.read();
        CurricularComponentModel curricularComponentModelFinded = curricularComponentService.findByUuid(listC.get(0).getUuid());

        assertEquals("TESTES", curricularComponentModelFinded.getName());
        assertNotNull(curricularComponentModelFinded);
    }

    @Test
    @Order(5)
    @DisplayName("should be updated a CurricularComponent")
    @Override
    public void testUpdateOneEntityById() {
        List<CurricularComponentModel> listC = curricularComponentService.read();
        listC.get(0).setName("DAC");
        CurricularComponentModel curricularComponentModelUpdated = curricularComponentService.update(listC.get(0), listC.get(0).getUuid());

        assertEquals("DAC", curricularComponentModelUpdated.getName());
        assertNotNull(curricularComponentModelUpdated);
    }

    @Test
    @Order(6)
    @DisplayName("should be deleted a CurricularComponents")
    @Override
    public void testDeleteOneEntityById() {
        List<CurricularComponentModel> listC = curricularComponentService.read();
        boolean isDeleted = curricularComponentService.delete(listC.get(0).getUuid());

        assertTrue(isDeleted);
    }
}
