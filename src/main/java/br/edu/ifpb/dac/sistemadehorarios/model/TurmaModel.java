package br.edu.ifpb.dac.sistemadehorarios.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity(name = "turma")
public class TurmaModel implements Serializable {
    @Id
    @Column(name = "uuid", nullable = false)
    private String uuid;
    private String name;
    @ManyToOne
    @JoinColumn(name = "course_uuid")
    private CourseModel courseModel;
    private Date create_at;

    public TurmaModel() {
        this.uuid = String.valueOf(UUID.randomUUID());
        this.create_at = new Date();
    }

    public TurmaModel(String name) {
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

	public CourseModel getCourse() {
		return courseModel;
	}

	public void setCourse(CourseModel courseModel) {
		this.courseModel = courseModel;
	}

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

}
