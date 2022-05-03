package br.edu.ifpb.dac.sistemadehorarios.mockito.callAPI;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    public void postMock(){
		IntervalModel model = new IntervalModel();
        this.controller.create(model);
        verify(this.controller).create(model);       
    }
	
	@Test
	public void deleteMock() {
        this.controller.delete("123");
        when(this.controller.delete("123")).thenReturn(ResponseEntity.status(200).body("OK"));
        verify(this.controller).delete("123");      
	}
	
	@Test
	public void readMock() {
		List<IntervalDTO> dtoList = mock(List.class);
		when(this.controller.read()).thenReturn(ResponseEntity.status(200).body(dtoList));
		
		boolean thrown = false;

	    try {
	      this.controller.read();
	    } catch (Exception e) {
	      thrown = true;
	    }
		assertFalse(thrown);
	}

}
