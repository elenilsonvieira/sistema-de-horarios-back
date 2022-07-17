package br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Interval;

import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Gap.GapModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Shift.ShiftModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.WeekDay.WeekDayModel;
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
@Entity(name="interval_time") // se colocar só interval da erro
public class IntervalModel implements Serializable {

    @ManyToOne
    @JoinColumn(name = "gap_uuid")
    private GapModel gapModel;

    @ManyToOne
    @JoinColumn(name = "shift_uuid")
    private ShiftModel shiftModel;

    @ManyToOne
    @JoinColumn(name = "week_day_uuid")
    private WeekDayModel weekDayModel;

    @Id
    private String uuid;
    private Date create_at = new Date();
    @Column(updatable = true)
    private Date update_at;

    public IntervalModel() {
        this.uuid= Generators.randomBasedGenerator().generate().toString();
    }
}
