package br.edu.ifpb.dac.sistemadehorarios.DTO.classroom;

import br.edu.ifpb.dac.sistemadehorarios.entity.Classroom.ClassBlock.ClassBlockModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ClassBlockDTO {

    private String uuid;
    private String block;

    public ClassBlockDTO(ClassBlockModel classNameModel) {
        this.block = classNameModel.getBlockName();
        this.uuid = classNameModel.getUuid();
    }

    public static List<ClassBlockDTO> convert(List<ClassBlockModel> classes){
        return classes.stream().map(ClassBlockDTO::new).collect(Collectors.toList());
    }
}
