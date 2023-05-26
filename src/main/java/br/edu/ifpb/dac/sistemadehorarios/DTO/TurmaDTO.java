package br.edu.ifpb.dac.sistemadehorarios.DTO;

import br.edu.ifpb.dac.sistemadehorarios.entity.Turma.TurmaModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class TurmaDTO {

    private String uuid;
    private String name;

    private String course_uuid;

    public TurmaDTO(TurmaModel turmaModel) {
        this.name = turmaModel.getName();
        this.uuid = turmaModel.getUuid();
        if (turmaModel.getCourseModel() != null) {
            this.course_uuid = turmaModel.getCourseModel().getUuid();
        }
    }

    public static List<TurmaDTO> convert(List<TurmaModel> turmaModel){
        return turmaModel.stream().map(TurmaDTO::new).collect(Collectors.toList());
    }
}
