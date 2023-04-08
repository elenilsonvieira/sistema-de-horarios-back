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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CurricularComponentUnityTest {
    @Autowired
    private CurricularComponentService curricularComponentService = mock(CurricularComponentService.class);
    private CourseService courseService = mock(CourseService.class);

    @Test
    @Order(1)
    public void create() {

        try {
            CourseModel course  =new CourseModel();
            course.setName("ADS");
            when(this.courseService.create(course)).thenReturn(true);

            this.courseService.create(course);

            CurricularComponentDRO DRO = new CurricularComponentDRO();
            DRO.setWorkload((byte) 120);
            DRO.setName("TÉCNICAS DE TESTES");
            DRO.setCourseUuid(course.getUuid());

            when(this.curricularComponentService.create(DRO)).thenReturn(new CurricularComponentModel());

            CurricularComponentModel curricularComponentModel = this.curricularComponentService.create(DRO);

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

            when(this.curricularComponentService.create(DRO)).thenReturn(null);
            CurricularComponentModel curricularComponentModel = this.curricularComponentService.create(DRO);
            assertNull(curricularComponentModel);
        } catch (CurricularComponentInvalidException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(3)
    public void read() {

        List<CurricularComponentModel> mockCurricularComponent = new ArrayList<>();
        mockCurricularComponent.add(new CurricularComponentModel());

        when(this.curricularComponentService.read()).thenReturn(mockCurricularComponent);
        List<CurricularComponentModel> corricularComponentModelList = this.curricularComponentService.read();
        assertNotEquals(0, corricularComponentModelList.size() );
    }


    @Test
    @Order(5)
    public void delete() {
        when(this.curricularComponentService.delete("")).thenReturn(true);
        boolean result = this.curricularComponentService.delete("");
        assertTrue(result);
    }
}
