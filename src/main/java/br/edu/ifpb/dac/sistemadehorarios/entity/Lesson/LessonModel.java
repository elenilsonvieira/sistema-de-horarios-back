package br.edu.ifpb.dac.sistemadehorarios.entity.Lesson;

import br.edu.ifpb.dac.sistemadehorarios.entity.Calendar.CalendarModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Classroom.Classroom.ClassroomModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Course.CourseModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.CurricularComponent.CurricularComponentModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Interval.IntervalModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Professor.ProfessorModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Turma.TurmaModel;
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
public class LessonModel implements Serializable, Comparable<LessonModel> {

    @ManyToOne
    @JoinColumn(name = "corricular_component_uuid", nullable = false)
    private CurricularComponentModel curricularComponentModel;

    @ManyToOne
    @JoinColumn(name = "professor_uuid", nullable = true)
    private ProfessorModel professorModel;

    @ManyToOne
    @JoinColumn(name = "interval_uuid", nullable = true)
    private IntervalModel intervalModel;

    @ManyToOne
    @JoinColumn(name = "turma_uuid", nullable = true)
    private TurmaModel turmaModel;

    @ManyToOne
    @JoinColumn(name = "classroom_uuid", nullable = false)
    private ClassroomModel classroomModel;

    @ManyToOne
    @JoinColumn(name = "calendar_uuid", nullable = false)
    private CalendarModel calendarModel;

    @ManyToOne
    @JoinColumn(name = "course_uuid", nullable = false)
    private CourseModel courseModel;

    @Id
    private String uuid;
    private Date create_at = new Date();
    @Column(updatable = true)
    private Date update_at;

    public LessonModel() {
        this.uuid = Generators.randomBasedGenerator().generate().toString();
    }

    @Override
    public String toString() {
        return "LessonModel{" +
                "curricularComponentModel=" + curricularComponentModel + "\n" +
                ", professorModel=" + professorModel + "\n" +
                ", intervalModel=" + intervalModel + "\n" +
                ", turmaModel=" + turmaModel + "\n" +
                ", classroomModel=" + classroomModel + "\n" +
                ", calendarModel=" + calendarModel + "\n" +
                ", courseModel=" + courseModel + "\n" +
                ", uuid='" + uuid + '\'' + "\n" +
                ", create_at=" + create_at + "\n" +
                ", update_at=" + update_at + "\n" +
                '}';
    }

    @Override
    public int compareTo(LessonModel o) {
        return intervalModel.compareTo(o.getIntervalModel());
    }
}
