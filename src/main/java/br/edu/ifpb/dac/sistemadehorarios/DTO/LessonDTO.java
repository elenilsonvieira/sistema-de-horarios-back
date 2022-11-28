package br.edu.ifpb.dac.sistemadehorarios.DTO;

import br.edu.ifpb.dac.sistemadehorarios.DTO.classroom.ClassroomDTO;
import br.edu.ifpb.dac.sistemadehorarios.DTO.interval.IntervalDTO;
import br.edu.ifpb.dac.sistemadehorarios.entity.Lesson.LessonModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class LessonDTO {
    private String uuid;
    private CurricularComponentDTO curricularComponent;
    private ProfessorDTO professor;
    private TurmaDTO turma;
    private IntervalDTO interval;
    private ClassroomDTO classroom;
    private CalendarDTO calendar;
    private CourseDTO course;
    private String tipMessage;

    public LessonDTO(LessonModel lessonModel) {
        this.uuid = lessonModel.getUuid();

        this.curricularComponent = new CurricularComponentDTO(lessonModel.getCurricularComponentModel());
        this.turma = new TurmaDTO(lessonModel.getTurmaModel());
        this.classroom = new ClassroomDTO(lessonModel.getClassroomModel());
        this.calendar = new CalendarDTO(lessonModel.getCalendarModel());
        this.course = new CourseDTO(lessonModel.getCourseModel());

        var professorModel = lessonModel.getProfessorModel();
        this.professor = professorModel != null ? new ProfessorDTO(professorModel) : null;


        var intervalModel = lessonModel.getIntervalModel();
        this.interval = intervalModel != null ? new IntervalDTO(intervalModel) : null;
    }

    public static List<LessonDTO> convert(List<LessonModel> lessons) {
        return lessons.stream().map(LessonDTO::new).collect(Collectors.toList());
    }
}
