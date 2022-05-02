package br.edu.ifpb.dac.sistemadehorarios.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity(name = "lesson")
public class LessonModel implements Serializable {

    @Id
    @Column(name = "uuid", nullable = false)
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "corricular_component_uuid")
    private CurricularComponentModel curricularComponentModel;

    @ManyToOne
    @JoinColumn(name = "professor_uuid")
    private ProfessorModel professorModel;

    @ManyToOne
    @JoinColumn(name = "interval_uuid")
    private IntervalModel intervalModel;

    @ManyToOne
    @JoinColumn(name = "turma_uuid")
    private TurmaModel turmaModel;

    @ManyToOne
    @JoinColumn(name = "classroom_uuid")
    private ClassroomModel classroomModel;

    @ManyToOne
    @JoinColumn(name = "calendar_uuid")
    private CalendarModel calendarModel;

    private Date create_at;

    public LessonModel() {
        this.uuid=String.valueOf(UUID.randomUUID());
        this.create_at = new Date();
    }

    public CurricularComponentModel getCorricularComponentModel() {
        return curricularComponentModel;
    }

    public void setCorricularComponentModel(CurricularComponentModel curricularComponentModel) {
        this.curricularComponentModel = curricularComponentModel;
    }

    public ProfessorModel getProfessorModel() {
        return professorModel;
    }

    public void setProfessorModel(ProfessorModel professorModel) {
        this.professorModel = professorModel;
    }

    public IntervalModel getIntervalModel() {
        return intervalModel;
    }

    public void setIntervalModel(IntervalModel intervalModel) {
        this.intervalModel = intervalModel;
    }

    public TurmaModel getTurmaModel() {
        return turmaModel;
    }

    public void setTurmaModel(TurmaModel turmaModel) {
        this.turmaModel = turmaModel;
    }

    public ClassroomModel getClassroomModel() {
        return classroomModel;
    }

    public void setClassroomModel(ClassroomModel classroomModel) {
        this.classroomModel = classroomModel;
    }

    public String getUuid() {
        return uuid;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public CalendarModel getCalendarModel() {
        return calendarModel;
    }

    public void setCalendarModel(CalendarModel calendarModel) {
        this.calendarModel = calendarModel;
    }
}
