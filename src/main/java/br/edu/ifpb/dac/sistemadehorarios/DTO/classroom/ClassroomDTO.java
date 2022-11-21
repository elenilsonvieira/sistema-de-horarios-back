package br.edu.ifpb.dac.sistemadehorarios.DTO.classroom;

import java.util.List;
import java.util.stream.Collectors;

import br.edu.ifpb.dac.sistemadehorarios.entity.Classroom.Classroom.ClassroomModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassroomDTO {

	private String uuid;
	private String name;
    private Integer capacity;
	private ClassBlockDTO classBlockDTO;
	
	public ClassroomDTO(ClassroomModel classroom) {
		this.uuid = classroom.getUuid();
		this.name = classroom.getName();
		this.capacity = classroom.getCapacity();
		this.classBlockDTO = new ClassBlockDTO(classroom.getClassBlockModel());
	}
	
	public static List<ClassroomDTO> convert(List<ClassroomModel> classes){
		return classes.stream().map(ClassroomDTO::new).collect(Collectors.toList());
	}
}
