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
	
	@Mock
	private List<IntervalDTO> intervalDTOList;
	
	
	@Test
	public void verifyHour() {
		when(mockTest.getInterval()).thenReturn("18:30");
		assertEquals("18:30", mockTest.getInterval());
	}

	@Test
	public void verifyShiftWithHour(){
		intervalDTOList = mock(List.class);
		IntervalDTO dto =  mock(IntervalDTO.class);

		when(intervalDTOList.get(0)).thenReturn(dto);
		when(intervalDTOList.get(0).getInterval()).thenReturn("13:50");
		when(intervalDTOList.get(1)).thenReturn(null);

		assertEquals(intervalDTOList.get(0), dto);
		assertEquals(intervalDTOList.get(0).getInterval(), "13:50");
		assertNull(intervalDTOList.get(1));
	}
	
	@Test
	public void verifyDayOfWeek() {
		
		
	}


}
