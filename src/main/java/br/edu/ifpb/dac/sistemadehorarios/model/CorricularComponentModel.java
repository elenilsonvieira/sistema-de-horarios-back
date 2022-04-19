package br.edu.ifpb.dac.sistemadehorarios.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity(name="corricular_component")
public class CorricularComponentModel implements Serializable {
    @Id
    @Column(name = "uuid", nullable = false)
    private String uuid = String.valueOf(UUID.randomUUID());
    private byte workload;
    private String name;

    @ManyToOne
    @JoinColumn(name = "class_uuid")
    private ClassModel classModel;

    public CorricularComponentModel() {
    }

    public CorricularComponentModel( byte workload, String name, ClassModel classModel) {
        this.workload = workload;
        this.name = name;
        this.classModel = classModel;
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
