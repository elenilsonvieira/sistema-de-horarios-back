package br.edu.ifpb.dac.sistemadehorarios.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@Entity(name = "class")
public class ClassModel implements Serializable {
    @Id
    @Column(name = "uuid", nullable = false)
    private String uuid = String.valueOf(UUID.randomUUID());
    private String name;
    private String course;

    public ClassModel() {
    }

    public ClassModel(String name, String course) {
        this.name = name;
        this.course = course;
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

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
