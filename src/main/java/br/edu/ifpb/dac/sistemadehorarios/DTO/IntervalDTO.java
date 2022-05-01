package br.edu.ifpb.dac.sistemadehorarios.DTO;

import java.util.List;
import java.util.stream.Collectors;

import br.edu.ifpb.dac.sistemadehorarios.ENUM.DayOfWeekEnum;
import br.edu.ifpb.dac.sistemadehorarios.ENUM.ShiftEnum;
import br.edu.ifpb.dac.sistemadehorarios.model.IntervalModel;
import br.edu.ifpb.dac.sistemadehorarios.utils.shift.ShiftTemplate;

public class IntervalDTO {

	private String interval;
	private DayOfWeekEnum dayOfWeek;
	private String uuid;
	private ShiftEnum shift;

	
	public IntervalDTO(IntervalModel interval) {
		this.uuid = interval.getUuid();
		this.dayOfWeek = interval.getDayOfWeek();
		this.shift = interval.getShift();
		ShiftTemplate shiftTemplate = this.shift.factory(this.shift);

		switch (interval.getInterval()){
			case 1:
				this.interval = shiftTemplate.one();
				break;
			case 2:
				this.interval = shiftTemplate.two();
				break;
			case 3:
				this.interval = shiftTemplate.three();
				break;
			case 4:
				this.interval = shiftTemplate.four();
				break;
			case 5:
				this.interval = shiftTemplate.five();
				break;
			default:
				this.interval = shiftTemplate.six();
				break;
		}
	}
	
	public static List<IntervalDTO> convert(List<IntervalModel> intervalList){
		return intervalList.stream().map(IntervalDTO::new).collect(Collectors.toList());
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public DayOfWeekEnum getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(DayOfWeekEnum dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public ShiftEnum getShift() {
		return shift;
	}

	public void setShift(ShiftEnum shift) {
		this.shift = shift;
	}
}