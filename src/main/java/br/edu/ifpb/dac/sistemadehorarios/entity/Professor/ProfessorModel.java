package br.edu.ifpb.dac.sistemadehorarios.entity.Professor;

import com.fasterxml.uuid.Generators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@Entity(name = "professor")
public class ProfessorModel implements Serializable {

	@Column(unique = true, nullable = false)
    private String name;
	@Column(nullable = false)
    private String area;
	@Id
	private String uuid;
	private Date create_at = new Date();
	@Column(updatable = true)
	private Date update_at;

	public ProfessorModel() {
		this.uuid= Generators.randomBasedGenerator().generate().toString();
	}
}
