package br.edu.ifpb.dac.sistemadehorarios.DTO;

import java.util.List;
import java.util.stream.Collectors;

import br.edu.ifpb.dac.sistemadehorarios.entity.Course.CourseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}
