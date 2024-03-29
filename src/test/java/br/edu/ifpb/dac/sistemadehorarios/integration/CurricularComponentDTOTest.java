package br.edu.ifpb.dac.sistemadehorarios.integration;

import br.edu.ifpb.dac.sistemadehorarios.DTO.CurricularComponentDTO;
import br.edu.ifpb.dac.sistemadehorarios.DTO.interval.GapDTO;
import br.edu.ifpb.dac.sistemadehorarios.DTO.interval.IntervalDTO;
import br.edu.ifpb.dac.sistemadehorarios.DTO.interval.ShiftDTO;
import br.edu.ifpb.dac.sistemadehorarios.DTO.interval.WeekDayDTO;
import br.edu.ifpb.dac.sistemadehorarios.entity.Course.CourseModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.CurricularComponent.CurricularComponentModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Gap.GapEnum;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Gap.GapModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Interval.IntervalModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Shift.ShiftEnum;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Shift.ShiftModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.WeekDay.WeekDayModel;
import br.edu.ifpb.dac.sistemadehorarios.interfaces.DTOTest;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CurricularComponentDTOTest implements DTOTest {

    private static CourseModel courseModel = new CourseModel();

    private static CurricularComponentModel curricularComponentModel = new CurricularComponentModel();

    @BeforeAll
    public static void setUp() {
        courseModel.setName("ADS");

        curricularComponentModel.setName("DAC");
        curricularComponentModel.setWorkload((byte) 120);
        curricularComponentModel.setCourseUuid(courseModel);
    }

    @Test
    @Override
    @DisplayName("Conversion Model to DTO")
    public void convert(){
        List<CurricularComponentModel> listCurricularComponentModels = new ArrayList<CurricularComponentModel>();
        listCurricularComponentModels.add(curricularComponentModel);

        List<CurricularComponentDTO> listDTO = new ArrayList<CurricularComponentDTO>();
        listDTO.add(new CurricularComponentDTO(curricularComponentModel));

        assertEquals(listDTO.get(0).getUuid() , CurricularComponentDTO.convert(listCurricularComponentModels).get(0).getUuid());
        assertEquals(listDTO.get(0).getWorkload() , CurricularComponentDTO.convert(listCurricularComponentModels).get(0).getWorkload());
        assertEquals(listDTO.get(0).getName() , CurricularComponentDTO.convert(listCurricularComponentModels).get(0).getName());
        assertEquals(listDTO.get(0).getCourse().getUuid() , CurricularComponentDTO.convert(listCurricularComponentModels).get(0).getCourse().getUuid());
        assertNotEquals(-1, CurricularComponentDTO.convert(listCurricularComponentModels).get(0).getUuid());
    }
}
