package br.edu.ifpb.dac.sistemadehorarios.DTO;

import br.edu.ifpb.dac.sistemadehorarios.model.*;

import java.util.List;
import java.util.stream.Collectors;

public class LessonDTO {
    private String uuid;
    private CurricularComponentDTO curricularComponent;
    private ProfessorDTO professor;
    private TurmaDTO turma;
    private IntervalDTO interval;
    private ClassroomDTO classroom;
    private CalendarDTO calendar;

    public LessonDTO(LessonModel lessonModel){
        this.uuid = lessonModel.getUuid();
        this.curricularComponent = new CurricularComponentDTO(lessonModel.getCorricularComponentModel());
        this.professor = new ProfessorDTO(lessonModel.getProfessorModel());
        this.turma = new TurmaDTO(lessonModel.getTurmaModel());
        this.classroom = new ClassroomDTO(lessonModel.getClassroomModel());
        this.calendar = new CalendarDTO(lessonModel.getCalendarModel());
        var intervalModel = lessonModel.getIntervalModel();
        this.interval = intervalModel != null? new IntervalDTO(lessonModel.getIntervalModel()): null;
    }

    public static List<LessonDTO> convert(List<LessonModel> gaps){
        return gaps.stream().map(LessonDTO::new).collect(Collectors.toList());
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public CurricularComponentDTO getCurricularComponent() {
        return curricularComponent;
    }

    public void setCurricularComponent(CurricularComponentDTO curricularComponent) {
        this.curricularComponent = curricularComponent;
    }

    public ProfessorDTO getProfessor() {
        return professor;
    }

    public void setProfessor(ProfessorDTO professor) {
        this.professor = professor;
    }

    public TurmaDTO getTurma() {
        return turma;
    }

    public void setTurma(TurmaDTO turma) {
        this.turma = turma;
    }

    public ClassroomDTO getClassroom() {
        return classroom;
    }

    public void setClassroom(ClassroomDTO classroom) {
        this.classroom = classroom;
    }

    public CalendarDTO getCalendar() {
        return calendar;
    }

    public void setCalendar(CalendarDTO calendar) {
        this.calendar = calendar;
    }

    public IntervalDTO getInterval() {
        return interval;
    }

    public void setInterval(IntervalDTO interval) {
        this.interval = interval;
    }
}
