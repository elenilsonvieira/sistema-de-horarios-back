package br.edu.ifpb.dac.sistemadehorarios.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.io.Serializable;
import java.util.UUID;

@Entity(name = "class")
public class ClassModel implements Serializable {
    @Id
    @Column(name = "uuid", nullable = false)
    private String uuid = String.valueOf(UUID.randomUUID());
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "course_uuid")
    private CourseModel courseModel;

    public ClassModel() {
    }

    public ClassModel(String name) {
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

	public CourseModel getCourseModel() {
		return courseModel;
	}

	public void setCourseModel(CourseModel courseModel) {
		this.courseModel = courseModel;
	}
}
