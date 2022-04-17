package br.edu.ifpb.dac.sistemadehorarios.DTO;

import java.util.List;
import java.util.stream.Collectors;

import br.edu.ifpb.dac.sistemadehorarios.model.ClassroomModel;

public class ClassroomDTO {

	private String uuid;
	private String name;
	private String block;
	private int capacity;
	
	public ClassroomDTO(ClassroomModel classroom) {
		this.name = classroom.getName();
		this.block = classroom.getBlock();
		this.capacity = classroom.getCapacity();
		this.uuid = classroom.getUuid();
	}
	
	public static List<ClassroomDTO> convert(List<ClassroomModel> classes){
		return classes.stream().map(ClassroomDTO::new).collect(Collectors.toList());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
