package br.edu.ifpb.dac.sistemadehorarios.model;

import com.fasterxml.uuid.Generators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@Getter
@Setter
@Entity(name="course")
public class CourseModel implements Serializable  {

	@Column(unique = true, nullable = false)
	private String name;


	@Id
	private String uuid;
	private Date create_at = new Date();
	@Column(updatable = true)
	private Date update_at;

	public CourseModel() {
		this.uuid= Generators.randomBasedGenerator().generate().toString();
	}



}
