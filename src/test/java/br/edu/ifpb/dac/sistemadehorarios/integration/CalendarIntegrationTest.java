package br.edu.ifpb.dac.sistemadehorarios.integration;

import br.edu.ifpb.dac.sistemadehorarios.entity.Calendar.CalendarModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Calendar.CalendarService;
import br.edu.ifpb.dac.sistemadehorarios.exception.CalendarInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.interfaces.ServiceTest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CalendarIntegrationTest implements ServiceTest {

    private static CalendarModel calendarModel;

    @Autowired
    private CalendarService calendarService;
    @BeforeAll
    public static void setUp() {
        calendarModel =  new CalendarModel();
        calendarModel.setUuid("test-id");
        calendarModel.setSemester("2 Semestre");
    }

    @Test
    @Order(1)
    @DisplayName("Attributes are not null")
    @Override
    public void attributesAreNotNull() {
        assertNotNull(calendarModel.getSemester());
        assertNotNull(calendarModel.getUuid());
    }

    @Test
    @Order(2)
    @DisplayName("should be created a new Calendar")
    @Override
    public void testCreateNewEntity() {
        CalendarInvalidException err = null;

        try{
            calendarService.create(calendarModel);
        }catch(CalendarInvalidException e){
            err = e;
        }

        assertNull(err);
    }

    @Test
    @Order(3)
    @DisplayName("should be listed Calendars")
    @Override
    public void testReadEntities() {
        List<CalendarModel> listC = calendarService.read();

        assertEquals("2 Semestre", listC.get(0).getSemester());
        assertNotNull(listC);
    }

    @Test
    @Order(4)
    @DisplayName("should be found a Calendar")
    @Override
    public void testFindOneEntityById() {
        List<CalendarModel> listC = calendarService.read();
        CalendarModel calendarModelFinded = calendarService.findByUuid("test-id");

        assertEquals("2 Semestre", calendarModelFinded.getSemester());
        assertNotNull(calendarModelFinded);
    }

    @Test
    @Order(5)
    @DisplayName("should be updated a Calendar")
    @Override
    public void testUpdateOneEntityById() {
        List<CalendarModel> listC = calendarService.read();
        listC.get(0).setSemester("1 Semestre");
        boolean isUpdated = calendarService.update(listC.get(0), "test-id");

        assertTrue(isUpdated);
    }

    @Test
    @Order(6)
    @DisplayName("should be deleted a Calendar")
    @Override
    public void testDeleteOneEntityById() {
        List<CalendarModel> listC = calendarService.read();
        boolean isDeleted = calendarService.delete("test-id");

        assertTrue(isDeleted);
    }
}
