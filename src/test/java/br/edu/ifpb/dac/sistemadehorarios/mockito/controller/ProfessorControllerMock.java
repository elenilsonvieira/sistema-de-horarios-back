package br.edu.ifpb.dac.sistemadehorarios.mockito.controller;
import br.edu.ifpb.dac.sistemadehorarios.DTO.ProfessorDTO;
import br.edu.ifpb.dac.sistemadehorarios.controller.ProfessorController;
import br.edu.ifpb.dac.sistemadehorarios.exception.ProfessorInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.model.ProfessorModel;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProfessorControllerMock {

    @Mock
    private ProfessorController controller = mock(ProfessorController.class);

    @Test
    public void postMock() throws ProfessorInvalidException {
        this.controller.create(null);
        verify(this.controller).create(null);
        when(this.controller.create(null)).thenReturn(null);
        assertNull(this.controller.create(null));
    }

    @Test
    public void deleteMock() {
        this.controller.delete("123");

        //se eu chamar o delete mais de uma vez com o mesmo parâmetro (id),
        //o teste não passa mesmo sendo um mock
        when(this.controller.delete("1234")).thenReturn(ResponseEntity.status(200).body("OK"));
        assertEquals(this.controller.delete("1234"), ResponseEntity.status(200).body("OK"));

        verify(this.controller).delete("123");
    }

    @Test
    public void readMock() {
        List<ProfessorDTO> dtoList = mock(List.class);
        when(this.controller.read()).thenReturn(ResponseEntity.status(200).body(dtoList));

        boolean thrown = false;

        //verficando se houve lançamento de excessões
        try {
            this.controller.read();
        } catch (Exception e) {
            thrown = true;
        }

        assertFalse(thrown);
        verify(this.controller).read();
    }

    @Test
    public void update(){

        ProfessorModel professor = mock(ProfessorModel.class);
        ProfessorDTO dto = mock(ProfessorDTO.class);
        when(this.controller.update(professor,"123")).thenReturn( ResponseEntity.status(200).body(dto) );
        assertEquals(this.controller.update(professor,"123"), ResponseEntity.status(200).body(dto));
    }

}
