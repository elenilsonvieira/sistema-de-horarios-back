package br.edu.ifpb.dac.sistemadehorarios.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity(name = "lesson")
public class LessonModel implements Serializable {

    @Id
    @Column(name = "uuid", nullable = false)
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "corricular_component_model_uuid")
    private CorricularComponentModel corricularComponentModel;

    @ManyToOne
    @JoinColumn(name = "professor_model_uuid")
    private ProfessorModel professorModel;

    @ManyToOne
    @JoinColumn(name = "gap_model_uuid")
    private GapModel gapModel;

    @ManyToOne
    @JoinColumn(name = "class_model_uuid")
    private ClassModel classModel;

    private Date create_at;

    public LessonModel() {
        this.uuid=String.valueOf(UUID.randomUUID());
        this.create_at = new Date();
    }

    public ClassModel getClassModel() {
        return classModel;
    }

    public GapModel getGapModel() {
        return gapModel;
    }

    public ProfessorModel getProfessorModel() {
        return professorModel;
    }

    public CorricularComponentModel getCorricularComponentModel() {
        return corricularComponentModel;
    }
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public void setCorricularComponentModel(CorricularComponentModel corricularComponentModel) {
        this.corricularComponentModel = corricularComponentModel;
    }

    public void setProfessorModel(ProfessorModel professorModel) {
        this.professorModel = professorModel;
    }

    public void setGapModel(GapModel gapModel) {
        this.gapModel = gapModel;
    }

    public void setClassModel(ClassModel classModel) {
        this.classModel = classModel;
    }
}
