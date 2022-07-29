package br.edu.ifpb.dac.sistemadehorarios.exception;

public class UserInvalidException extends ErrorProject{
    public UserInvalidException(String message, int status) {
        super(message, status);
    }
}
