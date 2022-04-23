package br.edu.ifpb.dac.sistemadehorarios.model;
import br.edu.ifpb.dac.sistemadehorarios.ENUM.DayOfWeekEnum;
import br.edu.ifpb.dac.sistemadehorarios.ENUM.ShiftEnum;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.*;

@Entity(name = "gap")
public class GapModel  implements Serializable {
	
	@Id
	@Column(name = "uuid", nullable = false)
	private String uuid = String.valueOf(UUID.randomUUID());

	@Enumerated(EnumType.STRING)
	private DayOfWeekEnum dayOfWeek;

	@Enumerated(EnumType.STRING)
	private ShiftEnum shiftEnum;

	@Column(name = "interval_class")
	private int interval;

	public GapModel() {
	}

	public GapModel(int interval, DayOfWeekEnum dayOfWeek, ShiftEnum shiftEnum) {
		this.interval = interval;
		this.dayOfWeek = dayOfWeek;
		this.shiftEnum = shiftEnum;
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

	public ShiftEnum getShiftEnum() {
		return shiftEnum;
	}

	public void setShiftEnum(ShiftEnum shiftEnum) {
		this.shiftEnum = shiftEnum;
	}
}
