package br.edu.ifpb.dac.sistemadehorarios.entity.Classroom.ClassBlock;

import com.fasterxml.uuid.Generators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@Entity(name="class_block")
public class ClassBlockModel implements Serializable {

    @Column(nullable = false)
    private String blockName;
    @Id
    private String uuid;
    private Date create_at = new Date();
    @Column(updatable = true)
    private Date update_at;

    public ClassBlockModel() {
        this.uuid= Generators.randomBasedGenerator().generate().toString();
    }
}
