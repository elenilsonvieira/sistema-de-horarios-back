package br.edu.ifpb.dac.sistemadehorarios.DTO;

import br.edu.ifpb.dac.sistemadehorarios.model.ProfessorModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProfessorDTO {
    private String name;
    private String area;
	private String uuid;
	private CourseDTO course;

	public ProfessorDTO(ProfessorModel professor) {

        this.name = professor.getName();
        this.area = professor.getArea();
		this.uuid = professor.getUuid();
		this.course = new CourseDTO(professor.getCourseUuid());
    }

	public static List<ProfessorDTO> convert(List<ProfessorModel> professor){
        return professor.stream().map(ProfessorDTO::new).collect(Collectors.toList());
    }

}
