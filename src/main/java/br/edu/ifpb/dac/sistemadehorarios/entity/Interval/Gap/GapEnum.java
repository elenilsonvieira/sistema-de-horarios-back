package br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Gap;

import lombok.Getter;

@Getter
public enum GapEnum {
    FRIST("Primeira Aula"),
    SECOND("Segunda Aula"),
    THIRD("Terceira Aula"),
    FOURTH("Quarta Aula"),
    FIFTH("Quinta Aula"),
    SIXTH("Sexta Aula");

    private String name;

    GapEnum(String name) {
        this.name = name;
    }

}
