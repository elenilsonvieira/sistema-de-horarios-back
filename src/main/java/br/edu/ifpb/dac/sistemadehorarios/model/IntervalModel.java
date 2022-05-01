package br.edu.ifpb.dac.sistemadehorarios.model;
import br.edu.ifpb.dac.sistemadehorarios.ENUM.DayOfWeekEnum;
import br.edu.ifpb.dac.sistemadehorarios.ENUM.ShiftEnum;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.*;

@Entity(name = "interval_time")
public class IntervalModel implements Serializable {
	
	@Id
	@Column(name = "uuid", nullable = false)
	private String uuid;
	@Enumerated(EnumType.STRING)
	private DayOfWeekEnum dayOfWeek;
	@Enumerated(EnumType.STRING)
	private ShiftEnum shift;
	@Column(name = "interval_class") // da erro de sintaxe sql se for só interval
	private int interval;
	private Date create_at;

	public IntervalModel() {
		this.uuid=String.valueOf(UUID.randomUUID());
		this.create_at = new Date();
	}

	public IntervalModel(int interval, DayOfWeekEnum dayOfWeek, ShiftEnum shiftEnum) {
		this.uuid=String.valueOf(UUID.randomUUID());
		this.interval = interval;
		this.dayOfWeek = dayOfWeek;
		this.shift = shiftEnum;
		this.create_at = new Date();
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public DayOfWeekEnum getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(DayOfWeekEnum dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public ShiftEnum getShift() {
		return shift;
	}

	public void setShift(ShiftEnum shiftEnum) {
		this.shift = shiftEnum;
	}

	public Date getCreate_at() {
		return create_at;
	}

	public void setCreate_at(Date create_at) {
		this.create_at = create_at;
	}
}
