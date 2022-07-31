package br.edu.ifpb.dac.sistemadehorarios.exception.interval;

import br.edu.ifpb.dac.sistemadehorarios.exception.ErrorProject;

public class GapException extends Exception {
    public GapException(String message) {
        super(message);
    }
}
