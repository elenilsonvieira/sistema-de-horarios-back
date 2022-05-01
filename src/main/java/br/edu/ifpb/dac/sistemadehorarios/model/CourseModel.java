package br.edu.ifpb.dac.sistemadehorarios.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="course")
public class CourseModel implements Serializable {
	
	@Id
    @Column(name = "uuid", nullable = false)
    private String uuid;
	@Column(unique = true, nullable = false)
	private String name;
	private Date create_at;
	
	public CourseModel(){
		this.uuid= String.valueOf(UUID.randomUUID());
		this.create_at = new Date();
	}

	public CourseModel(String name) {
		this.uuid = String.valueOf(UUID.randomUUID());
		this.name = name;
		this.create_at = new Date();
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

	public Date getCreate_at() {
		return create_at;
	}


	public void setCreate_at(Date create_at) {
		this.create_at = create_at;
	}
}
