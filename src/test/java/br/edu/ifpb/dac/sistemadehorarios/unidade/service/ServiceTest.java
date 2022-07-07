package br.edu.ifpb.dac.sistemadehorarios.unidade.service;

import br.edu.ifpb.dac.sistemadehorarios.exception.CurricularComponentInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.exception.ProfessorInvalidException;
import org.springframework.http.ResponseEntity;

public interface ServiceTest {

    public void create() throws ProfessorInvalidException, CurricularComponentInvalidException;
    public void read();
    public void update();
    public void delete();
}
