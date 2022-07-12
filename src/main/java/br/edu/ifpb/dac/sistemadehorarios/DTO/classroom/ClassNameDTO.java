package br.edu.ifpb.dac.sistemadehorarios.DTO.classroom;

import br.edu.ifpb.dac.sistemadehorarios.model.classroom.ClassNameModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ClassNameDTO {

    private String uuid;
    private String name;
    private int capacity;

    public ClassNameDTO(ClassNameModel classNameModel) {
        this.name = classNameModel.getName();
        this.capacity = classNameModel.getCapacity();
        this.uuid = classNameModel.getUuid();
    }

    public static List<ClassNameDTO> convert(List<ClassNameModel> classes){
        return classes.stream().map(ClassNameDTO::new).collect(Collectors.toList());
    }
}
