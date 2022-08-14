package br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Gap;

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

    @Column(nullable = false)
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
