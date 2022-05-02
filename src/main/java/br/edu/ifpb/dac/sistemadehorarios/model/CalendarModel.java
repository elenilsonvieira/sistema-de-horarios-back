package br.edu.ifpb.dac.sistemadehorarios.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity(name="calendar")
public class CalendarModel implements Serializable {
    @Id
    @Column(name = "uuid", nullable = false)
    private String uuid;
    private String Semester;
    private Date create_at;

    public CalendarModel(String uuid, String semester) {
        this.uuid = uuid;
        Semester = semester;
        this.uuid = UUID.randomUUID().toString();
        this.create_at = new Date();
    }

    public CalendarModel() {
        this.uuid = UUID.randomUUID().toString();
        this.create_at = new Date();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getSemester() {
        return Semester;
    }

    public void setSemester(String semester) {
        Semester = semester;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }
}
