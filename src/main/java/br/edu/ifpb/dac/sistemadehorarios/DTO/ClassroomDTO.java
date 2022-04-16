package br.edu.ifpb.dac.sistemadehorarios.DTO;

import java.util.List;
import java.util.stream.Collectors;

import br.edu.ifpb.dac.sistemadehorarios.model.ClassroomModel;

public class ClassroomDTO {
	
	private String name;
	private String block;
	private int capacity;
	
	public ClassroomDTO(ClassroomModel classroom) {
		this.name = classroom.getName();
		this.block = classroom.getBlock();
		this.capacity = classroom.getCapacity();
	}

	public ClassroomDTO(String name, String block, int capacity) {
		this.name = name;
		this.block = block;
		this.capacity = capacity;
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

}
