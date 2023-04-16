package br.edu.ifpb.dac.sistemadehorarios.entity.Profile;

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
@Entity(name = "profile")
public class ProfileModel implements Serializable {
	@Column(nullable = false)
    private String field;
	@Column(nullable = false)
    private Integer standard;
	@Id
	private String uuid;
	private Date create_at = new Date();
	@Column(updatable = true)
	private Date update_at;
	public ProfileModel() {
		this.uuid= Generators.randomBasedGenerator().generate().toString();
	}
}
