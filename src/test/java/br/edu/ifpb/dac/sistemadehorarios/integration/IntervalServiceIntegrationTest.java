package br.edu.ifpb.dac.sistemadehorarios.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;

import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Gap.GapModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Shift.ShiftModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.WeekDay.WeekDayModel;
import br.edu.ifpb.dac.sistemadehorarios.interfaces.ServiceTest;
import org.junit.Ignore;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Gap.GapService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Interval.IntervalModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Interval.IntervalService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Shift.ShiftService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.WeekDay.WeekDayService;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IntervalServiceIntegrationTest implements ServiceTest {

    private static IntervalModel intervalModel;

    @Autowired
    private IntervalService intervalService;

    @Autowired
    private ShiftService shiftService;

    @Autowired
    private WeekDayService weekDayService;

    @Autowired
    private GapService gapService;

    @BeforeAll
    public static void setUp() throws Exception {
        intervalModel = new IntervalModel();
        intervalModel.setUuid("id-test");
        intervalModel.setCreate_at(new Date());
    }

    @Test
    @Order(1)
    @DisplayName("Create from intervalService")
    @Override
    public void testCreateNewEntity() {
        try {
            ShiftModel shiftModel = shiftService.findByUuid("b9a9c5d3-a472-4f4b-a9c5-d3a4729f4bf3");
            WeekDayModel weekDayModel = weekDayService.findByUuid("54460d47-25bf-4170-860d-4725bf217007");
            GapModel gapModel = gapService.findByUuid("4adbab20-118b-4660-9bab-20118bc660e7");

            intervalModel.setShiftModel(shiftModel);
            intervalModel.setWeekDayModel(weekDayModel);
            intervalModel.setGapModel(gapModel);

            assertNotEquals(intervalService.create(intervalModel), false);

        } catch (Exception e) {
            e.printStackTrace();
            assertFalse(false);
        }
    }

    @Test
    @Order(2)
    @DisplayName("List IntervalModel from intervalService")
    @Override
    public void testReadEntities() {
        try {
            List<IntervalModel>  listIntervalModelTest = intervalService.read();
            assertNotEquals(listIntervalModelTest.size(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(3)
    @DisplayName("Return IntervalModel from intervalService")
    @Override
    public void testFindOneEntityById() {
        try {
            String id = "id-test";
            IntervalModel intervalModelTest = intervalService.findByUuid(id);
            assertNotEquals(intervalModelTest, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Ignore
    @Override
    public void testUpdateOneEntityById() { }

    @Test
    @Order(4)
    @DisplayName("Delete IntervalModel from intervalService")
    @Override
    @Ignore
    public void testDeleteOneEntityById() {
        try {
            String id = "id-test";
            boolean check = intervalService.delete(id);
            assertTrue(check);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}