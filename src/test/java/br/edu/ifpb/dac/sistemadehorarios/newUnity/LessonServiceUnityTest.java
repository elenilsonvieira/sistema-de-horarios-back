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

import br.edu.ifpb.dac.sistemadehorarios.DTO.LessonDTO;
import br.edu.ifpb.dac.sistemadehorarios.entity.Calendar.CalendarModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Classroom.Classroom.ClassroomModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Course.CourseModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.CurricularComponent.CurricularComponentModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Interval.IntervalModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Interval.IntervalRepository;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Shift.ShiftModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.WeekDay.WeekDayModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Lesson.LessonModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Lesson.LessonRepository;
import br.edu.ifpb.dac.sistemadehorarios.entity.Lesson.LessonService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Professor.ProfessorModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Restriction.RestrictionModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Turma.TurmaModel;
import br.edu.ifpb.dac.sistemadehorarios.exception.LessonInvalidException;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LessonServiceUnityTest {

	@MockBean
    private LessonRepository repository;
	@MockBean
    private TurmaModel turmaMock;
	@MockBean
    private CurricularComponentModel curricularComponentMock;
	@MockBean
    private ClassroomModel classroomMock;
	@MockBean
    private CalendarModel calendarMock;
	@MockBean
    private CourseModel courseMock;
	@MockBean
    private RestrictionModel restrictionMock;
	@MockBean
    private ShiftModel shiftMock;
	@MockBean
    private IntervalRepository intervalRepository;
	@MockBean
	private LessonModel lessonMock;
	@MockBean
	private LessonDRO lessonDROMock;
	
	@Autowired
	private LessonService lessonService;
	
	
	@Test
	@Order(1)
    @Tag("creat method")
    @DisplayName("Creat from lessonService")
	void createNewLesson() {	
		try {
			LessonDRO lessonDRO = new LessonDRO();
			lessonDRO.setCalendarUuid(calendarMock.getUuid());
			lessonDRO.setClassroomUuid(classroomMock.getUuid());
			lessonDRO.setTurmaUuid(turmaMock.getUuid());
			assertThrows(LessonInvalidException.class, () -> lessonService.create(lessonDRO), "Não é permitido valro null");
			LessonModel lessonModelTeste = lessonService.create(lessonDROMock);
			assertNotEquals(lessonModelTeste, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	@Test
	@Order(2)
    @Tag("read method")
    @DisplayName("List LessonModel from lessonService")
	void readLesson() {	
		try {
			List<LessonModel> listLessonModelTeste = lessonService.read();
			listLessonModelTeste.add(lessonMock);
			assertEquals(listLessonModelTeste.size(), 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	@Test
	@Order(3)
    @Tag("findByUuid method")
    @DisplayName("Return LessonModel from lessonService")
	void findByUuidLesson() {	
		try {
			String id = "id-mock";
			LessonModel lessonModelTeste = lessonService.findByUuid(id);
			assertNotEquals(lessonModelTeste, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	@Test
	@Order(4)
    @Tag("delete method")
    @DisplayName("Delete LessonModel from lessonService")
	void deleteRestriction() {
		try {
			String id = "id-mock";
			boolean check = lessonService.delete(id);
			assertTrue(check);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	@Order(5)
    @Tag("update method")
    @DisplayName("Update LessonModel")
	void updateLesson() {
		try {
			String id = "id-mock";
			LessonDTO lessonModelTeste = lessonService.update(lessonMock, id);
			assertNotEquals(lessonModelTeste, null);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(5)
    @Tag("update method")
    @DisplayName("Update erro LessonModel")
	void updateErroLesson() {
		try {
			ProfessorModel professorModel = new ProfessorModel();
			ShiftModel shiftModel = new ShiftModel();
			ShiftModel shiftModel2 = new ShiftModel();
			WeekDayModel weekDayModel = new WeekDayModel();
			
			RestrictionModel restrictionModel = new RestrictionModel();
			restrictionModel.setProfessorModel(professorModel);
			restrictionModel.setShiftModel(shiftModel);
			restrictionModel.setWeekDayModel(weekDayModel);
			
			IntervalModel intervalModel = new IntervalModel();
			intervalModel.setShiftModel(shiftModel2);
			intervalModel.setWeekDayModel(weekDayModel);
			
			LessonModel lessonModelOrigin = new LessonModel();
			lessonModelOrigin.setIntervalModel(intervalModel);		
			lessonModelOrigin.setProfessorModel(professorModel);
			
			String id = "id-mock";
			assertThrows(LessonInvalidException.class, () -> lessonService.update(lessonModelOrigin, id), "Não é permitido valro null");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
