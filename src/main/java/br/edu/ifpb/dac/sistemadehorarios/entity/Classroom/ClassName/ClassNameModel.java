package br.edu.ifpb.dac.sistemadehorarios.entity.Classroom.ClassName;

import com.fasterxml.uuid.Generators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@AllArgsConstructor
@Getter
@Setter
@Entity(name = "class_name")
public class ClassNameModel implements Serializable {

	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private int capacity;
	@Id
	private String uuid;
	private Date create_at = new Date();
	@Column(updatable = true)
	private Date update_at;

	public ClassNameModel() {
		this.uuid= Generators.randomBasedGenerator().generate().toString();
	}

}
