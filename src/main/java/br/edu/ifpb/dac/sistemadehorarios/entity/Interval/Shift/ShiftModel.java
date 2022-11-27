package br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Shift;

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
@Entity(name="shift")
public class ShiftModel implements Serializable, Comparable<ShiftModel> {

    @Column(nullable = false)
    private String shift;
    @Id
    private String uuid;
    private Date create_at = new Date();
    @Column(updatable = true)
    private Date update_at;

    public ShiftModel() {
        this.uuid= Generators.randomBasedGenerator().generate().toString();
    }

    @Override
    public int compareTo(ShiftModel o) {
        return ShiftEnum.valueOf(this.shift).compareTo(ShiftEnum.valueOf(o.getShift()));
    }
}
