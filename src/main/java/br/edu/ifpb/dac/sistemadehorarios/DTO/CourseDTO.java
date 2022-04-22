package br.edu.ifpb.dac.sistemadehorarios.DTO;

import java.util.List;
import java.util.stream.Collectors;

import br.edu.ifpb.dac.sistemadehorarios.model.CourseModel;

public class CourseDTO {
	
	private String uuid;
	private String name;
	
	public CourseDTO(CourseModel courseModel) {
		this.uuid = courseModel.getUuid();
		this.name = courseModel.getName();
	}
	
	public static List<CourseDTO> convert(List<CourseModel> course){
		return course.stream().map(CourseDTO::new).collect(Collectors.toList());
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
