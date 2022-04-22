package br.edu.ifpb.dac.sistemadehorarios.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="course")
public class CourseModel {
	
	@Id
    @Column(name = "uuid", nullable = false)
    private String uuid = String.valueOf(UUID.randomUUID());
	
	@Column(unique = true, nullable = false)
	private String name;
	
	public CourseModel(){
	}

	public CourseModel(String name) {
		this.uuid = String.valueOf(UUID.randomUUID());
		this.name = name;
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
