package br.edu.ifpb.dac.sistemadehorarios.controller;

import br.edu.ifpb.dac.sistemadehorarios.exception.ErrorProject;
import br.edu.ifpb.dac.sistemadehorarios.exception.ProfessorInvalidException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = {ErrorProject.class})
    protected ResponseEntity<String> professorInvalid(ErrorProject error){
        System.out.println();
        return ResponseEntity.status(error.getStatus()).body(error.getMessage());
    }
}
