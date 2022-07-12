package br.edu.ifpb.dac.sistemadehorarios.DTO;

import br.edu.ifpb.dac.sistemadehorarios.model.TurmaModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class TurmaDTO {

    private String uuid;
    private String name;
    private CourseDTO course;

    public TurmaDTO(TurmaModel turmaModel) {
        this.name = turmaModel.getName();
        this.uuid = turmaModel.getUuid();
        this.course = new CourseDTO(turmaModel.getCourseModel());
    }

    public static List<TurmaDTO> convert(List<TurmaModel> turmaModel){
        return turmaModel.stream().map(TurmaDTO::new).collect(Collectors.toList());
    }
}
