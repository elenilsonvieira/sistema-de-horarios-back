package br.edu.ifpb.dac.sistemadehorarios.entity.Classroom.Classroom;

import br.edu.ifpb.dac.sistemadehorarios.entity.Classroom.ClassBlock.ClassBlockModel;
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
@Entity(name = "classroom")
public class ClassroomModel implements Serializable {

    @Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private Integer capacity;

    @ManyToOne
    @JoinColumn(name = "block", nullable = false)
    private ClassBlockModel classBlockModel;

    @Id
    private String uuid;
    private Date create_at = new Date();
    @Column(updatable = true)
    private Date update_at;

    public ClassroomModel() {
        this.uuid= Generators.randomBasedGenerator().generate().toString();
    }
}
