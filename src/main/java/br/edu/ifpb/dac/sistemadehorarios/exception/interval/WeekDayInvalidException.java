package br.edu.ifpb.dac.sistemadehorarios.exception.interval;

import br.edu.ifpb.dac.sistemadehorarios.exception.ErrorProject;

public class WeekDayInvalidException extends ErrorProject {
    public WeekDayInvalidException(String message, int status) {
        super(message, status);
    }
}
