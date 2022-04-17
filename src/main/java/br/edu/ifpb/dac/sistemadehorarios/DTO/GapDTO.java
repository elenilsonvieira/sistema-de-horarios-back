package br.edu.ifpb.dac.sistemadehorarios.DTO;

import java.util.List;
import java.util.stream.Collectors;

import br.edu.ifpb.dac.sistemadehorarios.ENUM.DayOfWeekEnum;
import br.edu.ifpb.dac.sistemadehorarios.model.GapModel;

public class GapDTO {

	private String interval;
	private DayOfWeekEnum dayOfWeek;
	private String uuid;

	
	public GapDTO(GapModel gap) {
		switch (gap.getInterval()){
			case 1:
				this.interval = "18:30";
				break;
			case 2:
				this.interval = "19:20";
				break;
			case 3:
				this.interval = "20:10";
				break;
			case 4:
				this.interval = "21:00";
				break;
			case 5:
				this.interval = "21:50";
				break;
			default:
				this.interval = "22:00+";
				break;
		}

		this.uuid = gap.getUuid();
		this.dayOfWeek = gap.getDayOfWeek();
	}
	
	public static List<GapDTO> convert(List<GapModel> gaps){
		return gaps.stream().map(GapDTO::new).collect(Collectors.toList());
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
}
