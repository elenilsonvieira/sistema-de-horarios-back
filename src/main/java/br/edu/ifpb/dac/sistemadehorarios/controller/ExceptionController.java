package br.edu.ifpb.dac.sistemadehorarios.controller;

import br.edu.ifpb.dac.sistemadehorarios.exception.ErrorProject;
import br.edu.ifpb.dac.sistemadehorarios.exception.ProfessorInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.exception.UserInvalidException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletException;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = {ErrorProject.class})
    protected ResponseEntity<String> errorHandler(ErrorProject error){

        return ResponseEntity.status(error.getStatus()).body(error.getMessage());
    }
}
