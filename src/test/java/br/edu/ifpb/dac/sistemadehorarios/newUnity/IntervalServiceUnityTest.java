package br.edu.ifpb.dac.sistemadehorarios.newUnity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Gap.GapService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Interval.IntervalDRO;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Interval.IntervalModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Interval.IntervalRepository;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Interval.IntervalService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Shift.ShiftService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.WeekDay.WeekDayService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Lesson.LessonModel;
import br.edu.ifpb.dac.sistemadehorarios.exception.interval.IntervalInvalidException;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class IntervalServiceUnityTest {

	@MockBean
	private IntervalRepository repository;
	
	@MockBean
	private IntervalModel intervalMock;
	
	@MockBean
	private IntervalDRO intervalDROMock;

	@MockBean
    private GapService gapService;

	@MockBean
    private ShiftService shiftService;

    @MockBean
    private WeekDayService weekDayService;
    
    @MockBean
    private LessonModel lessonMock;
	
    @Autowired
	private IntervalService intervalService;
	
	
	@Test
	@Order(1)
    @Tag("creat method")
    @DisplayName("Creat from intervalService")
	void createNewInterval() {
		try {
			IntervalModel intervalModelTeste = intervalService.create(intervalDROMock);
			assertNotEquals(intervalModelTeste, null);
			
		} catch (IntervalInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(2)
    @Tag("read method")
    @DisplayName("List IntervalModel from intervalService")
	void readInterval() {
		try {
			List<IntervalModel>  listIntervalModelTeste = intervalService.read();
			listIntervalModelTeste.add(intervalMock);
			assertEquals(listIntervalModelTeste.size(), 1);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	@Order(3)
    @Tag("findByUuid method")
    @DisplayName("Return IntervalModel from intervalService")
	void findByUuidInterval() {
		try {
			String id = "id-mock";
			IntervalModel intervalModelTeste = intervalService.findByUuid(id);
			assertNotEquals(intervalModelTeste, null);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(4)
    @Tag("delete method")
    @DisplayName("Delete IntervalModel from intervalService")
	void deleteRestriction() {
		try {
			String id = "id-mock";
			boolean check = intervalService.delete(id);
			assertTrue(check);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
}
