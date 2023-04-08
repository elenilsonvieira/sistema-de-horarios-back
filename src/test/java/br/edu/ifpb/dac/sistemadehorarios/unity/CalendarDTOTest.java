package br.edu.ifpb.dac.sistemadehorarios.unity;

import br.edu.ifpb.dac.sistemadehorarios.DTO.CalendarDTO;
import br.edu.ifpb.dac.sistemadehorarios.entity.Calendar.CalendarModel;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CalendarDTOTest {

    @Test
    @DisplayName("Conversion Test")
    public void convertTest() {
        List<CalendarModel> listCalendar = new ArrayList<CalendarModel>();
        CalendarModel calendarModel = new CalendarModel();
        calendarModel.setSemester("5");
        listCalendar.add(calendarModel);

        List<CalendarDTO> listDTO = new ArrayList<CalendarDTO>();
        listDTO.add(new CalendarDTO(calendarModel));

        assertEquals(listDTO.get(0).getUuid() , CalendarDTO.convert(listCalendar).get(0).getUuid());
        assertEquals(listDTO.get(0).getSemester() , CalendarDTO.convert(listCalendar).get(0).getSemester());
    }
}