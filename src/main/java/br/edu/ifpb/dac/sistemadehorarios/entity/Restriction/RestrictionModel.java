package br.edu.ifpb.dac.sistemadehorarios.entity.Restriction;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.uuid.Generators;

import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Shift.ShiftModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.WeekDay.WeekDayModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Professor.ProfessorModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Entity(name = "restriction")
public class RestrictionModel implements Serializable, Comparable<RestrictionModel> {

	@ManyToOne
    @JoinColumn(name = "professor_uuid", nullable = false)
	private ProfessorModel professorModel;
	
	@ManyToOne
    @JoinColumn(name = "week_day_uuid",nullable = false)
    private WeekDayModel weekDayModel;

	@ManyToOne
    @JoinColumn(name = "shift_uuid",nullable = false)
    private ShiftModel shiftModel;
	
	@Id
	private String uuid;
	private Date create_at = new Date();
	@Column(updatable = true)
	private Date update_at;

	public RestrictionModel() {
		this.uuid= Generators.randomBasedGenerator().generate().toString();
	}

	@Override
    public int compareTo(RestrictionModel o) {
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
                return 0;
            }
        }
    }
	
}
