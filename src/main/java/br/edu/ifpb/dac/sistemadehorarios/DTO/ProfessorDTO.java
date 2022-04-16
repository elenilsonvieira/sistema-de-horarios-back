package br.edu.ifpb.dac.sistemadehorarios.DTO;

import br.edu.ifpb.dac.sistemadehorarios.model.ProfessorModel;
import java.util.List;
import java.util.stream.Collectors;

public class ProfessorDTO {
    private String name;
    private String area;

    public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getArea() {
		return area;
	}


	public void setArea(String area) {
		this.area = area;
	}


	public ProfessorDTO(ProfessorModel professor) {

        this.name = professor.getName();
        this.area = professor.getArea();
    }
	

    public ProfessorDTO(String name, String area) {
		this.name = name;
		this.area = area;
	}


	public static List<ProfessorDTO> convert(List<ProfessorModel> professor){
        return professor.stream().map(ProfessorDTO::new).collect(Collectors.toList());
    }
}
