package br.edu.ifpb.dac.sistemadehorarios.DTO;

import java.util.List;
import java.util.stream.Collectors;

import br.edu.ifpb.dac.sistemadehorarios.model.GapModel;

public class GapDTO {

	private String start;
	private String dayOfWeek;
	
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	
	public GapDTO(String start, String dayOfWeek) {
		this.start = start;
		this.dayOfWeek = dayOfWeek;
	}
	
	public GapDTO(GapModel gap) {
		this.start = gap.getStart();
		this.dayOfWeek = gap.getDayOfWeek();
	}
	
	public static List<GapDTO> convert(List<GapModel> gaps){
		return gaps.stream().map(GapDTO::new).collect(Collectors.toList());
	}
	
	
	
}
