package br.edu.ifpb.dac.sistemadehorarios.entity.Turma;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TurmaDRO {

    private String name;

    private String course_uuid;

    @Override
    public String toString() {
        return "TurmaDRO{" +
                "name='" + name + '\'' +
                ", course_uuid='" + course_uuid + '\'' +
                '}';
    }
}
