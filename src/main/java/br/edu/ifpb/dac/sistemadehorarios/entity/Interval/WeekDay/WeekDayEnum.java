package br.edu.ifpb.dac.sistemadehorarios.entity.Interval.WeekDay;

import lombok.Getter;

@Getter
public enum WeekDayEnum {
    MONDAY("Segunda-feira"),
    TUESDAY("Terça-feira"),
    WEDNESDAY("Quarta-feira"),
    THURSDAY("Quinta-feira"),
    FRIDAY("Sexta-feira"),
    SATURDAY("Sábado");

    private String name;
    WeekDayEnum(String name) {
        this.name = name;
    }
}
