package br.edu.ifpb.dac.sistemadehorarios.exception.classroom;

import br.edu.ifpb.dac.sistemadehorarios.exception.ErrorProject;

public class ClassBlockInvalidException extends ErrorProject {
    public ClassBlockInvalidException(String message, int status) {
        super(message, status);
    }
}
