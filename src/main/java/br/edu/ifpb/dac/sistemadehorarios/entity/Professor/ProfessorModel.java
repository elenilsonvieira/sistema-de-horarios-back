package br.edu.ifpb.dac.sistemadehorarios.entity.Professor;

import br.edu.ifpb.dac.sistemadehorarios.entity.Profile.ProfileModel;
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
	
	@ManyToOne
    @JoinColumn(name = "profile", nullable = false)
    private ProfileModel profileModel;
	
	@Id
	private String uuid;
	private Date create_at = new Date();
	@Column(updatable = true)
	private Date update_at;

	public ProfessorModel() {
		this.uuid= Generators.randomBasedGenerator().generate().toString();
	}

	@Override
	public String toString() {
		return "ProfessorModel{" +
				"name='" + name + '\'' +
				", profileModel=" + profileModel +
				", uuid='" + uuid + '\'' +
				", create_at=" + create_at +
				", update_at=" + update_at +
				'}';
	}
}
