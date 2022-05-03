package br.edu.ifpb.dac.sistemadehorarios.mockito.callAPI;

import br.edu.ifpb.dac.sistemadehorarios.controller.ProfessorController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProfessorControllerMock {

    @Mock
    private ProfessorController controller = mock(ProfessorController.class);

    @Test
    public void postMock(){

        this.controller.create(null);
        verify(this.controller).create(null);
        when(this.controller.create(null)).thenReturn(null);
        assertNull(this.controller.create(null));
    }

}
