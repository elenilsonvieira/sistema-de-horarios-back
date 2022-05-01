package br.edu.ifpb.dac.sistemadehorarios.DTO;

import br.edu.ifpb.dac.sistemadehorarios.model.TurmaModel;
import br.edu.ifpb.dac.sistemadehorarios.model.CorricularComponentModel;

import java.util.List;
import java.util.stream.Collectors;

public class CorricularComponentDTO {

    private String uuid;
    private byte workload;
    private String name;
    private CourseDTO course;


    public CorricularComponentDTO(CorricularComponentModel corricularComponentModel) {
        this.uuid = corricularComponentModel.getUuid();
        this.workload = corricularComponentModel.getWorkload();
        this.name = corricularComponentModel.getName();
        this.course = new CourseDTO(corricularComponentModel.getCourseUuid());
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

    public CourseDTO getCourse() {
        return course;
    }

    public void setCourse(CourseDTO course) {
        this.course = course;
    }
}
