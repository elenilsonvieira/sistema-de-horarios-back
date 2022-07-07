package br.edu.ifpb.dac.sistemadehorarios.mockito.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.edu.ifpb.dac.sistemadehorarios.exception.IntervalInvalidException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.List;
import br.edu.ifpb.dac.sistemadehorarios.DTO.IntervalDTO;
import br.edu.ifpb.dac.sistemadehorarios.controller.IntervalController;
import br.edu.ifpb.dac.sistemadehorarios.model.IntervalModel;

@RunWith(MockitoJUnitRunner.class)
public class IntervalControllerMock {
	
	@Mock
    private IntervalController controller = mock(IntervalController.class);
	
	@Test
    public void postMock() throws IntervalInvalidException {
		IntervalModel model = new IntervalModel();
        this.controller.create(model);
        
        //teste básico utilizando o verify
        verify(this.controller).create(model);       
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
		List<IntervalDTO> dtoList = mock(List.class);
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

}
