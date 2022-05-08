package br.edu.ifpb.dac.sistemadehorarios.mockito.model;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.edu.ifpb.dac.sistemadehorarios.DTO.IntervalDTO;
import br.edu.ifpb.dac.sistemadehorarios.ENUM.DayOfWeekEnum;
import static org.junit.jupiter.api.Assertions.*;


@RunWith(MockitoJUnitRunner.class)
public class IntervalModelMock {
	
	@Mock
	private IntervalDTO mockTest = mock(IntervalDTO.class);
	
	@Mock
	private List<IntervalDTO> intervalDTOList;
	

	@Test
	public void verifyHour(){
		intervalDTOList = mock(List.class);
		IntervalDTO dto =  mock(IntervalDTO.class);

		when(intervalDTOList.get(0)).thenReturn(dto);
		when(intervalDTOList.get(0).getInterval()).thenReturn("13:50");
		when(intervalDTOList.get(1)).thenReturn(null);

		assertEquals(intervalDTOList.get(0), dto);
		assertEquals(intervalDTOList.get(0).getInterval(), "13:50"); 
		assertNull(intervalDTOList.get(1));
		//apesar do intervalo ser definido por um int (1, 2, 3), 
		//acaba que no seu get o retorno deve ser uma string, e é isso que acontece aqui
	}
	
	@Test
	public void verifyDayOfWeek() {
		intervalDTOList = mock(List.class);
		IntervalDTO dto =  mock(IntervalDTO.class);

		when(intervalDTOList.get(0)).thenReturn(dto);
		when(intervalDTOList.get(0).getDayOfWeek()).thenReturn(DayOfWeekEnum.FRIDAY);
		
		assertEquals(intervalDTOList.get(0).getDayOfWeek(), DayOfWeekEnum.FRIDAY);
		assertNotEquals(intervalDTOList.get(0).getDayOfWeek(), "FRIDAY"); 
		//o tipo recebido fica restrito ao enum, não pod eser comparado a string
	}
	
	@Test
	public void verifyData() {
		intervalDTOList = mock(List.class);
		IntervalDTO dto1 =  mock(IntervalDTO.class);
		IntervalDTO dto2 =  mock(IntervalDTO.class);
		
		intervalDTOList.add(dto1);
		intervalDTOList.add(dto2);
		
		when(intervalDTOList.get(0)).thenReturn(dto1);//sem o when/thenReturn, o teste não passa
		when(intervalDTOList.get(1)).thenReturn(dto2);
		
		assertNotEquals(intervalDTOList.get(0), intervalDTOList.get(2));
		
		verify(intervalDTOList).add(dto1);
		verify(intervalDTOList).add(dto2);
	}
	

}
