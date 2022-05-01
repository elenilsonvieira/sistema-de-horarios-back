package br.edu.ifpb.dac.sistemadehorarios.unidade.service;

import br.edu.ifpb.dac.sistemadehorarios.ENUM.DayOfWeekEnum;
import br.edu.ifpb.dac.sistemadehorarios.ENUM.ShiftEnum;
import br.edu.ifpb.dac.sistemadehorarios.exception.IntervalInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.model.IntervalModel;
import br.edu.ifpb.dac.sistemadehorarios.service.IntervalService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ShiftTemplateServiceTest implements ServiceTest{

    @Autowired
    private IntervalService intervalService;
    private String uuid = "cf7846a6-630a-4008-b961-fb67c16868fc";

    @Override
    @Test
    @Order(1)
    public void create() {
        IntervalModel gap = new IntervalModel(1, DayOfWeekEnum.FRIDAY, ShiftEnum.NIGHT);
        gap.setUuid(this.uuid);
        try{
            boolean result = this.intervalService.create(gap);
            assertTrue(result);
        } catch (Exception error) {
            assertThrows(IntervalInvalidException.class, () -> this.intervalService.create(gap));

        }
    }
    @Test
    @Order(2)
    public void createInvalidGap(){
        IntervalModel gap = new IntervalModel(1, DayOfWeekEnum.FRIDAY, ShiftEnum.NIGHT);
        assertThrows(IntervalInvalidException.class, () -> this.intervalService.create(gap));
    }

    @Override
    @Test
    @Order(3)
    public void read() {
        List<IntervalModel> intervalModelList = this.intervalService.read();
        assertNotEquals(0, intervalModelList.size() );
    }

    @Override
    public void update() {
        //Method not implemented
    }

    @Override
    @Test
    @Order(4)
    public void delete() {
        boolean result = this.intervalService.delete(this.uuid);
        assertTrue(result);
    }
}
