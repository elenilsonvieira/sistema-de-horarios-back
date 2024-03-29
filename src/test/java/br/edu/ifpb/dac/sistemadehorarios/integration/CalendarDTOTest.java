package br.edu.ifpb.dac.sistemadehorarios.integration;

import br.edu.ifpb.dac.sistemadehorarios.DTO.CalendarDTO;
import br.edu.ifpb.dac.sistemadehorarios.entity.Calendar.CalendarModel;
import br.edu.ifpb.dac.sistemadehorarios.interfaces.DTOTest;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CalendarDTOTest implements DTOTest {

    @Test
    @Override
    @DisplayName("Test Conversion Model to DTO")
    public void convert() {
        List<CalendarModel> listCalendar = new ArrayList<CalendarModel>();
        CalendarModel calendarModel = new CalendarModel();
        calendarModel.setSemester("5");
        listCalendar.add(calendarModel);

        List<CalendarDTO> listDTO = new ArrayList<CalendarDTO>();
        listDTO.add(new CalendarDTO(calendarModel));

        assertEquals(listDTO.get(0).getUuid() , CalendarDTO.convert(listCalendar).get(0).getUuid());
        assertEquals(listDTO.get(0).getSemester() , CalendarDTO.convert(listCalendar).get(0).getSemester());
        assertNotEquals(-1, CalendarDTO.convert(listCalendar).get(0).getUuid());
    }
}