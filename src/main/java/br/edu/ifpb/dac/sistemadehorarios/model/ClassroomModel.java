package br.edu.ifpb.dac.sistemadehorarios.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "classroom")
public class ClassroomModel  implements Serializable {
	
	@Id
	@Column(name = "uuid", nullable = false)
	private String uuid;
	private String name;
	private String block;
	private int capacity;
	private Date create_at;

	public ClassroomModel() {
		this.uuid=String.valueOf(UUID.randomUUID());
		this.create_at = new Date();
	}

	public ClassroomModel(String name, String block, int capacity) {
		this.uuid=String.valueOf(UUID.randomUUID());
		this.name = name;
		this.block = block;
		this.capacity = capacity;
		this.create_at = new Date();
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

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Date getCreate_at() {
		return create_at;
	}

	public void setCreate_at(Date create_at) {
		this.create_at = create_at;
	}
}
