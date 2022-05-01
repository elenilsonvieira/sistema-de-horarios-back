package br.edu.ifpb.dac.sistemadehorarios.DTO;

import br.edu.ifpb.dac.sistemadehorarios.model.CourseModel;
import br.edu.ifpb.dac.sistemadehorarios.model.ProfessorModel;
import java.util.List;
import java.util.stream.Collectors;

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

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public CourseDTO getCourse() {
		return course;
	}

	public void setCourse(CourseDTO course) {
		this.course = course;
	}
}
