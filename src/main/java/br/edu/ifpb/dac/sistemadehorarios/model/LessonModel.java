package br.edu.ifpb.dac.sistemadehorarios.model;

import br.edu.ifpb.dac.sistemadehorarios.model.classroom.ClassNameModel;
import br.edu.ifpb.dac.sistemadehorarios.model.classroom.ClassroomModel;
import br.edu.ifpb.dac.sistemadehorarios.model.interval.IntervalModel;
import com.fasterxml.uuid.Generators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@Entity(name = "lesson")
public class LessonModel implements Serializable  {

    @ManyToOne
    @JoinColumn(name = "corricular_component_uuid", unique = true)
    private CurricularComponentModel curricularComponentModel;

    @ManyToOne
    @JoinColumn(name = "professor_uuid")
    private ProfessorModel professorModel;

    @ManyToOne
    @JoinColumn(name = "interval_uuid", nullable = true)
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

    @ManyToOne
    @JoinColumn(name = "course_uuid")
    private CourseModel courseModel;

    @Id
    private String uuid;
    private Date create_at = new Date();
    @Column(updatable = true)
    private Date update_at;

    public LessonModel() {
        this.uuid= Generators.randomBasedGenerator().generate().toString();
    }
}
