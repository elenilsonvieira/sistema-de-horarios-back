package br.edu.ifpb.dac.sistemadehorarios.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "classroom")
public class ClassroomModel  implements Serializable {
	
	@Id
	@Column(name = "uuid", nullable = false)
	private String uuid=String.valueOf(UUID.randomUUID());
	private String name;
	private String block;
	private int capacity;
	
	public ClassroomModel() {
	}

	public ClassroomModel(String name, String block, int capacity) {
		this.name = name;
		this.block = block;
		this.capacity = capacity;
	}
	
	public String getUuid() {
		return uuid;
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
