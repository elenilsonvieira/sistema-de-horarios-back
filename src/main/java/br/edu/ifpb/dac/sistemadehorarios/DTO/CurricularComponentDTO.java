package br.edu.ifpb.dac.sistemadehorarios.DTO;

import br.edu.ifpb.dac.sistemadehorarios.model.TurmaModel;
import br.edu.ifpb.dac.sistemadehorarios.model.CurricularComponentModel;

import java.util.List;
import java.util.stream.Collectors;

public class CurricularComponentDTO {

    private String uuid;
    private byte workload;
    private String name;
    private CourseDTO course;


    public CurricularComponentDTO(CurricularComponentModel curricularComponentModel) {
        this.uuid = curricularComponentModel.getUuid();
        this.workload = curricularComponentModel.getWorkload();
        this.name = curricularComponentModel.getName();
        this.course = new CourseDTO(curricularComponentModel.getCourseUuid());
    }

    public static List<CurricularComponentDTO> convert(List<CurricularComponentModel> curricularComponentModel){
        return curricularComponentModel.stream().map(CurricularComponentDTO::new).collect(Collectors.toList());
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
