package br.edu.ifpb.dac.sistemadehorarios.serviceTest;

import br.edu.ifpb.dac.sistemadehorarios.ENUM.DayOfWeekEnum;
import br.edu.ifpb.dac.sistemadehorarios.model.GapModel;
import br.edu.ifpb.dac.sistemadehorarios.model.ProfessorModel;
import br.edu.ifpb.dac.sistemadehorarios.service.GapService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GapServiceTest {

    @Autowired
    private GapService service;
    private GapModel gap = new GapModel(1, DayOfWeekEnum.THURSDAY);

    @Test
    @Order(1)
    void createGapAndInvalidGap(){
        GapModel gapModel =new GapModel(1, DayOfWeekEnum.THURSDAY);

        boolean resultOne = this.service.create(this.gap);
        assertTrue(resultOne);

        boolean resultTwo = this.service.create(gapModel);
        assertFalse(resultTwo);
    }

    @Test
    @Order(2)
    void read() {
        List<GapModel> gap = service.read();
        assertNotEquals(0,gap.size());
    }

    @Test
    @Order(3)
    void update() {
        GapModel professorModel = new GapModel(2, DayOfWeekEnum.FRIDAY);
        boolean result = this.service.update(professorModel, this.gap.getUuid());
        assertTrue(result);
    }

    @Test
    @Order(4)
    void delete() {
        boolean result = service.delete(this.gap.getUuid());
        assertTrue(result);
    }
}
