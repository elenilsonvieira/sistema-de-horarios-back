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
public class IntervalModel implements Serializable, Comparable<IntervalModel> {

    @ManyToOne
    @JoinColumn(name = "gap_uuid", nullable = false)
    private GapModel gapModel;

    @ManyToOne
    @JoinColumn(name = "shift_uuid",nullable = false)
    private ShiftModel shiftModel;

    @ManyToOne
    @JoinColumn(name = "week_day_uuid",nullable = false)
    private WeekDayModel weekDayModel;

    @Id
    private String uuid;
    private Date create_at = new Date();
    @Column(updatable = true)
    private Date update_at;

    public IntervalModel() {
        this.uuid= Generators.randomBasedGenerator().generate().toString();
    }

    @Override
    public int compareTo(IntervalModel o) {
        if(this.weekDayModel.compareTo(o.getWeekDayModel()) < 0){
            return -1;
        }else if(this.weekDayModel.compareTo(o.getWeekDayModel()) > 0){
            return 1;
        }else{
            if(this.shiftModel.compareTo(o.getShiftModel()) < 0){
                return -1;
            } else if(this.shiftModel.compareTo(o.getShiftModel()) > 0){
                return 1;
            } else {
                if(this.gapModel.compareTo(o.getGapModel()) < 0){
                    return -1;
                } else if(this.gapModel.compareTo(o.getGapModel()) > 0){
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof IntervalModel)) {
            return false;
        }else{
            final IntervalModel intervalComp = (IntervalModel) obj;
            if(this.getGapModel().getGapEnum().equals(intervalComp.getGapModel().getGapEnum())
                && this.getShiftModel().getShiftEnum().equals(intervalComp.getShiftModel().getShiftEnum())
                && this.getWeekDayModel().getDayOfWeek().equals(intervalComp.getWeekDayModel().getDayOfWeek())){
                return true;
            }
            return false;
        }   
    }
    
}
