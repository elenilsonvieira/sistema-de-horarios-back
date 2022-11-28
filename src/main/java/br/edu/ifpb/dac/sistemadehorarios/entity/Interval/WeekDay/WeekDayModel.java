package br.edu.ifpb.dac.sistemadehorarios.entity.Interval.WeekDay;

import com.fasterxml.uuid.Generators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@Entity(name="week_day")
public class WeekDayModel implements Serializable, Comparable<WeekDayModel> {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @Column(nullable = false)
    private String displayName;

    @Id
    private String uuid;
    private Date create_at = new Date();
    @Column(updatable = true)
    private Date update_at;

    public WeekDayModel() {
        this.uuid= Generators.randomBasedGenerator().generate().toString();
    }

    @Override
    public int compareTo(WeekDayModel o) {
        return this.dayOfWeek.compareTo(o.getDayOfWeek());
    }
}
