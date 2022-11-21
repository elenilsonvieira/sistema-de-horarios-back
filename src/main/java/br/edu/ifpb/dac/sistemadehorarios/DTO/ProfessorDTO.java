package br.edu.ifpb.dac.sistemadehorarios.DTO;

import br.edu.ifpb.dac.sistemadehorarios.entity.Professor.ProfessorModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProfessorDTO {
	
    private String uuid;
    private String name;
    private ProfileDTO profileDTO;

	public ProfessorDTO(ProfessorModel professor) {
        
		this.uuid = professor.getUuid();
        this.name = professor.getName();
        this.profileDTO = new ProfileDTO(professor.getProfileModel());
    }

	public static List<ProfessorDTO> convert(List<ProfessorModel> professor){
        return professor.stream().map(ProfessorDTO::new).collect(Collectors.toList());
    }

}
