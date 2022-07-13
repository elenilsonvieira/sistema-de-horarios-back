package br.edu.ifpb.dac.sistemadehorarios.exception.classroom;

import br.edu.ifpb.dac.sistemadehorarios.exception.ErrorProject;

public class ClassNameInvalidException extends ErrorProject {
    public ClassNameInvalidException(String message, int status) {
        super(message, status);
    }
}
