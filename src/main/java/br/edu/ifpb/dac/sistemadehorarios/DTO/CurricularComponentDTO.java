package br.edu.ifpb.dac.sistemadehorarios.DTO;

import br.edu.ifpb.dac.sistemadehorarios.entity.CurricularComponent.CurricularComponentModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CurricularComponentDTO {

    private String uuid;
    private int workload;
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
}
