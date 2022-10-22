package br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Shift;

import lombok.Getter;

@Getter
public enum ShiftEnum {
    MORNING("Manhã"),
    AFTERNOON("Tarde"),
    NIGHT("Noite"),
    ALL_DAY("Dia todo");

    private String name;
    ShiftEnum(String name) {
        this.name = name;
    }
}
