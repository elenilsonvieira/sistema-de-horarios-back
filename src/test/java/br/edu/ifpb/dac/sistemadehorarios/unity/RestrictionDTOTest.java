package br.edu.ifpb.dac.sistemadehorarios.unity;

import br.edu.ifpb.dac.sistemadehorarios.DTO.ProfessorDTO;
import br.edu.ifpb.dac.sistemadehorarios.DTO.RestrictionDTO;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Gap.GapEnum;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Shift.ShiftEnum;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Shift.ShiftModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.WeekDay.WeekDayEnum;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.WeekDay.WeekDayModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Professor.ProfessorModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Profile.ProfileModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Restriction.RestrictionModel;
import br.edu.ifpb.dac.sistemadehorarios.interfaces.DTOTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class RestrictionDTOTest implements DTOTest {

    private static ProfessorModel professorModel = new ProfessorModel();

    private static ProfileModel profileModel = new ProfileModel();

    private static RestrictionModel restrictionModel = new RestrictionModel();

    private static ShiftModel shiftModel = new ShiftModel();

    private static WeekDayModel weekDayModel = new WeekDayModel();

    @BeforeAll
    public static void setUp() {
        weekDayModel.setDayOfWeek(DayOfWeek.FRIDAY);
        weekDayModel.setDisplayName("Sexta-Feira");

        shiftModel.setShiftEnum(ShiftEnum.NIGHT);
        shiftModel.setDisplayName("Noite");

        profileModel.setStandard(1);
        profileModel.setField("Professor");

        professorModel.setProfileModel(profileModel);
        professorModel.setName("Tiago");

        restrictionModel.setShiftModel(shiftModel);
        restrictionModel.setWeekDayModel(weekDayModel);
        restrictionModel.setProfessorModel(professorModel);
    }

    @Test
    @Override
    @DisplayName("Test Conversion Model to DTO")
    public void convert(){
        List<RestrictionModel> listRestriction = new ArrayList<RestrictionModel>();

        listRestriction.add(restrictionModel);

        List<RestrictionDTO> listDTO = new ArrayList<RestrictionDTO>();
        listDTO.add(new RestrictionDTO(restrictionModel));

        assertEquals(listDTO.get(0).getUuid() , RestrictionDTO.convert(listRestriction).get(0).getUuid());
        assertEquals(listDTO.get(0).getShiftDTO().getUuid() , RestrictionDTO.convert(listRestriction).get(0).getShiftDTO().getUuid());
        assertEquals(listDTO.get(0).getProfessorDTO().getUuid() , RestrictionDTO.convert(listRestriction).get(0).getProfessorDTO().getUuid());
        assertEquals(listDTO.get(0).getWeekDayDTO().getUuid(), RestrictionDTO.convert(listRestriction).get(0).getWeekDayDTO().getUuid());

        assertNotEquals(-1, RestrictionDTO.convert(listRestriction).get(0).getUuid());
    }
}
