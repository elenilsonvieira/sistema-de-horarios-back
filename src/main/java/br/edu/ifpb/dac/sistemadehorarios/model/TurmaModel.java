package br.edu.ifpb.dac.sistemadehorarios.model;

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

    private String name;

    @Id
    private String uuid;
    private Date create_at = new Date();
    @Column(updatable = true)
    private Date update_at;

    public TurmaModel() {
        this.uuid= Generators.randomBasedGenerator().generate().toString();
    }

    @ManyToOne
    @JoinColumn(name = "course_uuid")
    private CourseModel courseModel;

    public TurmaModel(String name) {
        super();
        this.name = name;
    }
}
