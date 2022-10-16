package br.edu.ifpb.dac.sistemadehorarios.DTO;


import java.util.List;
import java.util.stream.Collectors;

import br.edu.ifpb.dac.sistemadehorarios.DTO.interval.WeekDayDTO;
import br.edu.ifpb.dac.sistemadehorarios.entity.Restriction.RestrictionModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestrictionDTO {

	private ProfessorDTO professorDTO;
	private WeekDayDTO weekDayDTO;
	private String uuid;

	
	public RestrictionDTO (RestrictionModel restrictionModel) {
		this.uuid = restrictionModel.getUuid();
		this.weekDayDTO = new WeekDayDTO(restrictionModel.getWeekDayUuid());
		this.professorDTO = new ProfessorDTO(restrictionModel.getProfessorUuid());
		
	}
	
	public static List<RestrictionDTO> convert(List<RestrictionModel> restrictionList){
		return restrictionList.stream().map(RestrictionDTO::new).collect(Collectors.toList());
	}
	
}
