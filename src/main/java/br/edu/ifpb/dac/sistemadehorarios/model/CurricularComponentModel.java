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
@Entity(name="corricular_component")
public class CurricularComponentModel implements Serializable {

    private byte workload;
    private String name;
    @ManyToOne
    @JoinColumn(name = "course_uuid")
    private CourseModel courseUuid;

    @Id
    private String uuid;
    private Date create_at = new Date();
    @Column(updatable = true)
    private Date update_at;

    public CurricularComponentModel() {
        this.uuid= Generators.randomBasedGenerator().generate().toString();
    }

}
