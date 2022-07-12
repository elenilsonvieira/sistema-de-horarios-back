package br.edu.ifpb.dac.sistemadehorarios.model.interval;

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
@Entity(name="gap")
public class GapModel implements Serializable {

    private String gap;
    @Id
    private String uuid;
    private Date create_at = new Date();
    @Column(updatable = true)
    private Date update_at;

    public GapModel() {
        this.uuid= Generators.randomBasedGenerator().generate().toString();
    }
}
