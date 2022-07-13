package br.edu.ifpb.dac.sistemadehorarios.exception.interval;

import br.edu.ifpb.dac.sistemadehorarios.exception.ErrorProject;

public class IntervalInvalidException extends ErrorProject {

    public IntervalInvalidException(String message, int status) {
        super(message, status);
    }
}
