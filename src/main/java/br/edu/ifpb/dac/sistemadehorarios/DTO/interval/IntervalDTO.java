package br.edu.ifpb.dac.sistemadehorarios.DTO.interval;

import java.util.List;
import java.util.stream.Collectors;

import br.edu.ifpb.dac.sistemadehorarios.model.interval.IntervalModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IntervalDTO {

	private GapDTO gapDTO;
	private WeekDayDTO weekDayDTO;
	private ShiftDTO shiftDTO;
	private String uuid;

	
	public IntervalDTO(IntervalModel interval) {
		this.uuid = interval.getUuid();
		this.gapDTO = new GapDTO(interval.getGapModel());
		this.weekDayDTO = new WeekDayDTO(interval.getWeekDayModel());
		this.shiftDTO = new ShiftDTO(interval.getShiftModel());
	}
	
	public static List<IntervalDTO> convert(List<IntervalModel> intervalList){
		return intervalList.stream().map(IntervalDTO::new).collect(Collectors.toList());
	}
}