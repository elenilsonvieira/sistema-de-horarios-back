package br.edu.ifpb.dac.sistemadehorarios.DTO;

import br.edu.ifpb.dac.sistemadehorarios.model.*;

import java.util.List;
import java.util.stream.Collectors;

public class LessonDTO {
    private String uuid;
    private CurricularComponentDTO corricularComponent;
    private ProfessorDTO professor;
    private IntervalDTO interval;
    private TurmaDTO turma;
    private ClassroomDTO classroom;
    private CalendarDTO calendarModel;

    public LessonDTO(LessonModel lessonModel){
        this.uuid = lessonModel.getUuid();
        this.corricularComponent = new CurricularComponentDTO(lessonModel.getCorricularComponentModel());
        this.professor = new ProfessorDTO(lessonModel.getProfessorModel());
        this.interval = new IntervalDTO(lessonModel.getIntervalModel());
        this.turma = new TurmaDTO(lessonModel.getTurmaModel());
        this.classroom = new ClassroomDTO(lessonModel.getClassroomModel());
        this.calendarModel = new CalendarDTO(lessonModel.getCalendarModel());
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

    public CurricularComponentDTO getCorricularComponent() {
        return corricularComponent;
    }

    public void setCorricularComponent(CurricularComponentDTO corricularComponent) {
        this.corricularComponent = corricularComponent;
    }

    public ProfessorDTO getProfessor() {
        return professor;
    }

    public void setProfessor(ProfessorDTO professor) {
        this.professor = professor;
    }

    public IntervalDTO getInterval() {
        return interval;
    }

    public void setInterval(IntervalDTO interval) {
        this.interval = interval;
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

    public CalendarDTO getCalendarModel() {
        return calendarModel;
    }

    public void setCalendarModel(CalendarDTO calendarModel) {
        this.calendarModel = calendarModel;
    }
}
