package br.edu.ifpb.dac.sistemadehorarios.exception.interval;

import br.edu.ifpb.dac.sistemadehorarios.exception.ErrorProject;

public class ShiftInvalidException extends ErrorProject {
    public ShiftInvalidException(String message, int status) {
        super(message, status);
    }
}
