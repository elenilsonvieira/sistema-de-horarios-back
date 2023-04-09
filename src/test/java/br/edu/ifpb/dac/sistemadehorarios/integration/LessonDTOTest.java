package br.edu.ifpb.dac.sistemadehorarios.integration;

import br.edu.ifpb.dac.sistemadehorarios.DTO.LessonDTO;
import br.edu.ifpb.dac.sistemadehorarios.DTO.interval.GapDTO;
import br.edu.ifpb.dac.sistemadehorarios.DTO.interval.IntervalDTO;
import br.edu.ifpb.dac.sistemadehorarios.DTO.interval.ShiftDTO;
import br.edu.ifpb.dac.sistemadehorarios.DTO.interval.WeekDayDTO;
import br.edu.ifpb.dac.sistemadehorarios.entity.Calendar.CalendarModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Classroom.ClassBlock.ClassBlockModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Classroom.Classroom.ClassroomModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Course.CourseModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.CurricularComponent.CurricularComponentModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Gap.GapEnum;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Gap.GapModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Interval.IntervalModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Shift.ShiftEnum;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Shift.ShiftModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.WeekDay.WeekDayModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Lesson.LessonModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Professor.ProfessorModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Profile.ProfileModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Turma.TurmaModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LessonDTOTest {

    private static WeekDayModel weekDayModel = new WeekDayModel();

    private static ShiftModel shiftModel = new ShiftModel();

    private static GapModel gapModel = new GapModel();

    private static IntervalModel intervalModel = new IntervalModel();

    private static CurricularComponentModel curricularComponentModel = new CurricularComponentModel();

    private static ProfessorModel professorModel = new ProfessorModel();

    private static ProfileModel profileModel = new ProfileModel();

    private static TurmaModel turmaModel = new TurmaModel();

    private static ClassBlockModel classBlockModel = new ClassBlockModel();

    private static ClassroomModel classroomModel = new ClassroomModel();

    private static CalendarModel calendarModel = new CalendarModel();

    private static CourseModel courseModel = new CourseModel();

    private static LessonModel lessonModel = new LessonModel();

    @BeforeAll
    public static void setUp() {
        weekDayModel.setDayOfWeek(DayOfWeek.FRIDAY);
        weekDayModel.setDisplayName("Sexta-Feira");

        shiftModel.setShiftEnum(ShiftEnum.NIGHT);
        shiftModel.setDisplayName("Noite");

        gapModel.setGapEnum(GapEnum.FRIST);
        gapModel.setDisplayName("Primeira Aula");

        intervalModel.setWeekDayModel(weekDayModel);
        intervalModel.setShiftModel(shiftModel);
        intervalModel.setGapModel(gapModel);

        profileModel.setField("Professor");
        profileModel.setStandard(1);

        professorModel.setName("Tiago");
        professorModel.setProfileModel(profileModel);

        classBlockModel.setBlockName("D");

        classroomModel.setName("LAB 5");
        classroomModel.setCapacity(40);
        classroomModel.setClassBlockModel(classBlockModel);

        calendarModel.setSemester("5");

        courseModel.setName("ADS");

        turmaModel.setName("FERAS");

        curricularComponentModel.setName("DAC");
        curricularComponentModel.setWorkload((byte) 120);
        curricularComponentModel.setCourseUuid(courseModel);

        lessonModel.setIntervalModel(intervalModel);
        lessonModel.setProfessorModel(professorModel);
        lessonModel.setClassroomModel(classroomModel);
        lessonModel.setCalendarModel(calendarModel);
        lessonModel.setCourseModel(courseModel);
        lessonModel.setTurmaModel(turmaModel);
        lessonModel.setCurricularComponentModel(curricularComponentModel);
    }

    @Test
    @Order(1)
    @DisplayName("Conversion Model to DTO")
    public void convert(){
        List<LessonModel> listLessons = new ArrayList<LessonModel>();
        listLessons.add(lessonModel);

        List<LessonDTO> listDTO = new ArrayList<LessonDTO>();
        listDTO.add(new LessonDTO(lessonModel));

        assertEquals(listDTO.get(0).getUuid() , LessonDTO.convert(listLessons).get(0).getUuid());
        assertEquals(listDTO.get(0).getCalendar().getUuid() , LessonDTO.convert(listLessons).get(0).getCalendar().getUuid());
        assertEquals(listDTO.get(0).getClassroom().getUuid() , LessonDTO.convert(listLessons).get(0).getClassroom().getUuid());
        assertEquals(listDTO.get(0).getCourse().getUuid() , LessonDTO.convert(listLessons).get(0).getCourse().getUuid());
        assertEquals(listDTO.get(0).getInterval().getUuid() , LessonDTO.convert(listLessons).get(0).getInterval().getUuid());
        assertEquals(listDTO.get(0).getTurma().getUuid() , LessonDTO.convert(listLessons).get(0).getTurma().getUuid());
        assertEquals(listDTO.get(0).getCurricularComponent().getUuid() , LessonDTO.convert(listLessons).get(0).getCurricularComponent().getUuid());

    }
}
