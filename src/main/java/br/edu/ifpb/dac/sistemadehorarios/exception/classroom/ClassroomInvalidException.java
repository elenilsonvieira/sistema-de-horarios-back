package br.edu.ifpb.dac.sistemadehorarios.exception.classroom;

import br.edu.ifpb.dac.sistemadehorarios.exception.ErrorProject;

public class ClassroomInvalidException extends ErrorProject {

    public ClassroomInvalidException(String message, int status) {
        super(message, status);
    }
}
