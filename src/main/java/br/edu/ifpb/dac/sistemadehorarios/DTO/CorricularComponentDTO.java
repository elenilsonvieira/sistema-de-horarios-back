package br.edu.ifpb.dac.sistemadehorarios.DTO;

import br.edu.ifpb.dac.sistemadehorarios.model.ClassModel;
import br.edu.ifpb.dac.sistemadehorarios.model.ClassroomModel;
import br.edu.ifpb.dac.sistemadehorarios.model.CorricularComponentModel;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CorricularComponentDTO {

    private String uuid;
    private byte workload;
    private String name;
    private ClassModel classModel;


    public CorricularComponentDTO(CorricularComponentModel corricularComponentModel) {
        this.uuid = corricularComponentModel.getUuid();
        this.workload = corricularComponentModel.getWorkload();
        this.name = corricularComponentModel.getName();
        this.classModel = corricularComponentModel.getClassModel();
    }

    public static List<CorricularComponentDTO> convert(List<CorricularComponentModel> corricularComponentModel){
        return corricularComponentModel.stream().map(CorricularComponentDTO::new).collect(Collectors.toList());
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
