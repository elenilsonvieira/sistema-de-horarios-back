package br.edu.ifpb.dac.sistemadehorarios.exception;

public abstract class ErrorProject extends Exception{

    private int status;

    public ErrorProject(String message, int status) {
        super(message);
        this.status = status;
    }
    public int getStatus(){
           return this.status;
    }
}
