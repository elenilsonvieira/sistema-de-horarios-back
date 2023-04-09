package br.edu.ifpb.dac.sistemadehorarios.integration;

import br.edu.ifpb.dac.sistemadehorarios.DTO.TurmaDTO;
import br.edu.ifpb.dac.sistemadehorarios.DTO.interval.GapDTO;
import br.edu.ifpb.dac.sistemadehorarios.DTO.interval.IntervalDTO;
import br.edu.ifpb.dac.sistemadehorarios.DTO.interval.ShiftDTO;
import br.edu.ifpb.dac.sistemadehorarios.DTO.interval.WeekDayDTO;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Gap.GapEnum;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Gap.GapModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Interval.IntervalModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Shift.ShiftEnum;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Shift.ShiftModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.WeekDay.WeekDayModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Professor.ProfessorDRO;
import br.edu.ifpb.dac.sistemadehorarios.entity.Turma.TurmaModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class IntervalDTOSTest {

    private static WeekDayModel weekDayModel = new WeekDayModel();

    private static ShiftModel shiftModel = new ShiftModel();

    private static GapModel gapModel = new GapModel();

    private static IntervalModel intervalModel = new IntervalModel();

    @BeforeAll
    public static void setUp() {
        weekDayModel.setDayOfWeek(DayOfWeek.FRIDAY);
        weekDayModel.setDisplayName("Sexta-Feira");

        shiftModel.setShiftEnum(ShiftEnum.NIGHT);
        shiftModel.setDisplayName("Noite");

        gapModel.setGapEnum(GapEnum.FRIST);
        gapModel.setDisplayName("Primeira Aula");

        intervalModel.setWeekDayModel(weekDayModel);
        intervalModel.setShiftModel(shiftModel);
        intervalModel.setGapModel(gapModel);
    }

    @Test
    @Order(1)
    @DisplayName("Conversion WeekDayModel to WeekDayDTO")
    public void convertWeek(){
        List<WeekDayModel> listWeek = new ArrayList<WeekDayModel>();
        listWeek.add(weekDayModel);

        List<WeekDayDTO> listDTO = new ArrayList<WeekDayDTO>();
        listDTO.add(new WeekDayDTO(weekDayModel));

        assertEquals(listDTO.get(0).getUuid() , WeekDayDTO.convert(listWeek).get(0).getUuid());
        assertEquals(listDTO.get(0).getDayOfWeek().getValue() , WeekDayDTO.convert(listWeek).get(0).getDayOfWeek().getValue());
        assertEquals(listDTO.get(0).getDisplayName() , WeekDayDTO.convert(listWeek).get(0).getDisplayName());
        assertNotEquals(-1, WeekDayDTO.convert(listWeek).get(0).getUuid());
    }

    @Test
    @Order(2)
    @DisplayName("Conversion ShiftModel to ShiftDTO")
    public void convertShift(){
        List<ShiftModel> listShift = new ArrayList<ShiftModel>();
        listShift.add(shiftModel);

        List<ShiftDTO> listDTO = new ArrayList<ShiftDTO>();
        listDTO.add(new ShiftDTO(shiftModel));

        assertEquals(listDTO.get(0).getUuid() , ShiftDTO.convert(listShift).get(0).getUuid());
        assertEquals(listDTO.get(0).getShift().getName() , ShiftDTO.convert(listShift).get(0).getShift().getName());
        assertEquals(listDTO.get(0).getDisplayName() , ShiftDTO.convert(listShift).get(0).getDisplayName());
        assertNotEquals(-1, ShiftDTO.convert(listShift).get(0).getUuid());
    }

    @Test
    @Order(3)
    @DisplayName("Conversion GapModel to GapDTO")
    public void convertGap(){
        List<GapModel> listGap = new ArrayList<GapModel>();
        listGap.add(gapModel);

        List<GapDTO> listDTO = new ArrayList<GapDTO>();
        listDTO.add(new GapDTO(gapModel));

        assertEquals(listDTO.get(0).getUuid() , GapDTO.convert(listGap).get(0).getUuid());
        assertEquals(listDTO.get(0).getGap().getName() , GapDTO.convert(listGap).get(0).getGap().getName());
        assertEquals(listDTO.get(0).getDisplayName() , GapDTO.convert(listGap).get(0).getDisplayName());
        assertNotEquals(-1, GapDTO.convert(listGap).get(0).getUuid());
    }

    @Test
    @Order(4)
    @DisplayName("Conversion IntervalModel to IntervalDTO")
    public void convertInterval(){
        List<IntervalModel> listInterval = new ArrayList<IntervalModel>();
        listInterval.add(intervalModel);

        List<IntervalDTO> listDTO = new ArrayList<IntervalDTO>();
        listDTO.add(new IntervalDTO(intervalModel));

        assertEquals(listDTO.get(0).getUuid() , IntervalDTO.convert(listInterval).get(0).getUuid());
        assertEquals(listDTO.get(0).getGapDTO().getUuid() , IntervalDTO.convert(listInterval).get(0).getGapDTO().getUuid());
        assertEquals(listDTO.get(0).getShiftDTO().getUuid() , IntervalDTO.convert(listInterval).get(0).getShiftDTO().getUuid());
        assertEquals(listDTO.get(0).getWeekDayDTO().getUuid() , IntervalDTO.convert(listInterval).get(0).getWeekDayDTO().getUuid());
        assertNotEquals(-1, IntervalDTO.convert(listInterval).get(0).getUuid());
    }
}
