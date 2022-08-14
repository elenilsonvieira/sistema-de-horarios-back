package br.edu.ifpb.dac.sistemadehorarios.entity.Classroom.ClassBlock;

import lombok.Getter;

@Getter
public enum ClassBlockEnum {
    ONE("Bloco 1"),
    TWO("Bloco 2"),
    THREE("Bloco 3"),
    FOUR("Bloco 4"),
    FIVE("Bloco 5"),
    SIX("Bloco 6");

    private String name;

    ClassBlockEnum(String name) {
        this.name = name;
    }

}
