package br.edu.ifpb.dac.sistemadehorarios.exception;

public class CourseInvalidException extends ErrorProject {
    public CourseInvalidException(String message,int status) {
        super(message, status);
    }
}
