package br.edu.ifpb.dac.sistemadehorarios.entity.Turma;

import br.edu.ifpb.dac.sistemadehorarios.entity.Course.CourseModel;
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
@Entity(name = "turma")
public class TurmaModel implements Serializable {
    @Column(nullable = false)
    private String name;
    @Id
    private String uuid;
    private Date create_at = new Date();
    @Column(updatable = true)
    private Date update_at;

    @ManyToOne
    @JoinColumn(name = "course_uuid", nullable = true)
    private CourseModel courseModel;

    public TurmaModel() {
        this.uuid = Generators.randomBasedGenerator().generate().toString();
    }

    @Override
    public String toString() {
        return "TurmaModel{" +
                "name='" + name + '\'' +
                ", uuid='" + uuid + '\'' +
                ", create_at=" + create_at +
                ", update_at=" + update_at +
                ", courseModel=" + courseModel.getUuid() +
                '}';
    }
}
