package br.edu.ifpb.dac.sistemadehorarios.entity.Classroom.Classroom;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassroomDRO {
    private String name;
    private Integer capacity;
    private String classBlockUuid;
}
