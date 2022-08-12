package br.edu.ifpb.dac.sistemadehorarios.unity;

import br.edu.ifpb.dac.sistemadehorarios.entity.Course.CourseModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Course.CourseService;
import br.edu.ifpb.dac.sistemadehorarios.entity.CurricularComponent.CurricularComponentDRO;
import br.edu.ifpb.dac.sistemadehorarios.entity.CurricularComponent.CurricularComponentModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.CurricularComponent.CurricularComponentService;
import br.edu.ifpb.dac.sistemadehorarios.exception.CourseInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.exception.CurricularComponentInvalidException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CurricularComponentUnityTest {
    @Autowired
    private CurricularComponentService curricularComponentService;
    @Autowired
    private CourseService courseService;
    public static String uuid;

    @Test
    @Order(1)
    public void create() {
        try {
            CourseModel course  =new CourseModel();
            course.setName("ADS");
            courseService.create(course);

            CurricularComponentDRO DRO = new CurricularComponentDRO();
            DRO.setWorkload((byte) 120);
            DRO.setName("TÉCNICAS DE TESTES");
            DRO.setCourseUuid(course.getUuid());

            CurricularComponentModel curricularComponentModel = this.curricularComponentService.create(DRO);;

            CurricularComponentUnityTest.uuid = curricularComponentModel.getUuid();
            assertNotNull(curricularComponentModel);
        } catch (CurricularComponentInvalidException | CourseInvalidException e) {
            e.printStackTrace();
        }
    }
    @Test
    @Order(2)
    public void createWithoutCourse(){

        try {
            CurricularComponentDRO DRO = new CurricularComponentDRO();
            DRO.setWorkload((byte) 120);
            DRO.setName("TÉCNICAS DE TESTES");

            CurricularComponentModel curricularComponentModel = this.curricularComponentService.create(DRO);
            assertNull(curricularComponentModel);
        } catch (CurricularComponentInvalidException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(3)
    public void read() {
        List<CurricularComponentModel> corricularComponentModelList = this.curricularComponentService.read();
        assertNotEquals(0, corricularComponentModelList.size() );
    }


    @Test
    @Order(4)
    public void update() {
        CurricularComponentModel curricularComponentModel = new CurricularComponentModel();
        curricularComponentModel.setWorkload((byte) 60);
        curricularComponentModel.setName("Análise de Algoritmos");
        boolean result = this.curricularComponentService.update(curricularComponentModel, CurricularComponentUnityTest.uuid);
        assertTrue(result);
    }

    @Test
    @Order(5)
    public void delete() {
        boolean result = this.curricularComponentService.delete(CurricularComponentUnityTest.uuid);
        assertTrue(result);
    }
}
