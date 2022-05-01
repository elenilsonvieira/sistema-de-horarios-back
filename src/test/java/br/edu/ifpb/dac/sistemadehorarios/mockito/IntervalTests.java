package br.edu.ifpb.dac.sistemadehorarios.mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.edu.ifpb.dac.sistemadehorarios.DTO.IntervalDTO;
import br.edu.ifpb.dac.sistemadehorarios.ENUM.DayOfWeekEnum;
import br.edu.ifpb.dac.sistemadehorarios.ENUM.ShiftEnum;
import br.edu.ifpb.dac.sistemadehorarios.model.IntervalModel;
import br.edu.ifpb.dac.sistemadehorarios.ENUM.DayOfWeekEnum;
import br.edu.ifpb.dac.sistemadehorarios.ENUM.ShiftEnum;

@SuppressWarnings("unchecked")
@RunWith(MockitoJUnitRunner.class)
public class IntervalTests {
	
	@Mock
	private IntervalDTO mockTest;
	
	
	@Test
	public void verifyHour() {
		
		when(mockTest.getInterval()).thenReturn("18:30");
		//System.out.println(mockTest.getInterval());
		assertEquals("18:30", mockTest.getInterval());
	}
	
	

}
