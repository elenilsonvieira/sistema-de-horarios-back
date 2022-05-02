package br.edu.ifpb.dac.sistemadehorarios.mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.edu.ifpb.dac.sistemadehorarios.DTO.IntervalDTO;
import br.edu.ifpb.dac.sistemadehorarios.model.IntervalModel;
import static org.junit.jupiter.api.Assertions.*;


@RunWith(MockitoJUnitRunner.class)
public class IntervalMock {
	
	@Mock
	private IntervalDTO mockTest = mock(IntervalDTO.class);
	
	
	@Test
	public void verifyHour() {
		when(mockTest.getInterval()).thenReturn("18:30");
		assertEquals("18:30", mockTest.getInterval());
	}

	@Test
	public void verifyShiftWithHour(){
		List<IntervalDTO> intervalDTOList = mock(List.class);
		IntervalModel intervalModel = null;
		IntervalDTO dto = new IntervalDTO(intervalModel);

		when(intervalDTOList.get(0)).thenReturn(dto);
		when(intervalDTOList.get(1)).thenReturn(null);

		assertEquals(intervalDTOList.get(0), dto);
		assertEquals(intervalDTOList.get(0).getInterval(), "13:50");
		assertNull(intervalDTOList.get(1));
	}


}
