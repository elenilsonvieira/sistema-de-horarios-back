package br.edu.ifpb.dac.sistemadehorarios.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity(name="corricular_component")
public class CorricularComponentModel implements Serializable {

    @Id
    @Column(name = "uuid", nullable = false)
    private String uuid;
    private byte workload;
    private String name;
    @ManyToOne
    @JoinColumn(name = "class_uuid")
    private ClassModel classModel;
    private Date create_at;

    public CorricularComponentModel() {
        this.uuid=String.valueOf(UUID.randomUUID());
        this.create_at = new Date();
    }

    public CorricularComponentModel(byte workload, String name) {
        this.uuid=String.valueOf(UUID.randomUUID());
		this.workload = workload;
		this.name = name;
        this.create_at = new Date();
	}

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public byte getWorkload() {
        return workload;
    }

    public void setWorkload(byte workload) {
        this.workload = workload;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClassModel getClassModel() {
        return classModel;
    }

    public void setClassModel(ClassModel classModel) {
        this.classModel = classModel;
    }


}
