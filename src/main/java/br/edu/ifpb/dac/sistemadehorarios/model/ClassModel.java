package br.edu.ifpb.dac.sistemadehorarios.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity(name = "class")
public class ClassModel implements Serializable {
    @Id
    @Column(name = "uuid", nullable = false)
    private String uuid;
    private String name;
    @ManyToOne
    @JoinColumn(name = "course_uuid")
    private CourseModel courseModel;
    private Date create_at;

    public ClassModel() {
        this.uuid = String.valueOf(UUID.randomUUID());
        this.create_at = new Date();
    }

    public ClassModel(String name) {
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

	public CourseModel getCourseModel() {
		return courseModel;
	}

	public void setCourseModel(CourseModel courseModel) {
		this.courseModel = courseModel;
	}

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

}
