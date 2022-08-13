package br.edu.ifpb.dac.sistemadehorarios.exception;

import lombok.Getter;

public class UserInvalidException extends ErrorProject{

    public UserInvalidException(String message, int status) {
        super(message, status);
    }
}
