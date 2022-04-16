package br.edu.ifpb.dac.sistemadehorarios.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity(name = "professor")
public class ProfessorModel implements Serializable {
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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Date getCreate_at() {
		return create_at;
	}

	public void setCreate_at(Date create_at) {
		this.create_at = create_at;
	}

	@Id
    @Column(name = "uuid", nullable = false)
    private String uuid;
    private String name;
    private String area;
    private Date create_at;

   
    
    public ProfessorModel() {
	}

	public ProfessorModel(String name, String area) {
        this.uuid =  String.valueOf(UUID.randomUUID());
        this.create_at = new Date();
        this.name = name;
        this.area = area;
    }

    public ProfessorModel(String name, String area, String uuid) {
        this.uuid =  uuid;
        this.create_at = new Date();
        this.name = name;
        this.area = area;
    }

}
