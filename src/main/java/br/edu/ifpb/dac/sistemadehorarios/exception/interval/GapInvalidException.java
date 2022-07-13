package br.edu.ifpb.dac.sistemadehorarios.exception.interval;

import br.edu.ifpb.dac.sistemadehorarios.exception.ErrorProject;

public class GapInvalidException extends ErrorProject {
    public GapInvalidException(String message, int status) {
        super(message, status);
    }
}
