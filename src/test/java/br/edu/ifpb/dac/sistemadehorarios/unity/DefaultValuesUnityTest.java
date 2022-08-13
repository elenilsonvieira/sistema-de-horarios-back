package br.edu.ifpb.dac.sistemadehorarios.unity;

import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Gap.GapModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Gap.GapService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Shift.ShiftModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Shift.ShiftService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.WeekDay.WeekDayModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.WeekDay.WeekDayService;
import br.edu.ifpb.dac.sistemadehorarios.entity.User.UserModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.User.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

public class DefaultValuesUnityTest {

    private GapService gapService = mock(GapService.class);
    private ShiftService shiftService = mock(ShiftService.class);
    private WeekDayService weekDayService = mock(WeekDayService.class);
    private UserService userService = mock(UserService.class);
    @Test
    public void gapDefaultValues(){
        List<GapModel> gapMock = new ArrayList<GapModel>();
        gapMock.add(new GapModel());
        when(this.gapService.read()).thenReturn(gapMock);

        List<GapModel> list = this.gapService.read();
        assertNotEquals(0, list.size());

    }
    @Test
    public void shiftDefaultValues(){
        List<ShiftModel> shiftMock = new ArrayList<ShiftModel>();
        shiftMock.add(new ShiftModel());
        when(this.shiftService.read()).thenReturn(shiftMock);

        List<ShiftModel> list = this.shiftService.read();
        assertNotEquals(0, list.size());
    }
    @Test
    public void weekDayDefaultValues(){
        List<WeekDayModel> weekdayMock = new ArrayList<WeekDayModel>();
        weekdayMock.add(new WeekDayModel());
        when(this.weekDayService.read()).thenReturn(weekdayMock);

        List<WeekDayModel> list = this.weekDayService.read();
        assertNotEquals(0, list.size());
    }
    @Test
    public void userDefaultValues(){
        List<UserModel> userMock = new ArrayList<UserModel>();
        userMock.add(new UserModel());
        when(this.userService.read()).thenReturn(userMock);

        List<UserModel> list = this.userService.read();
        assertNotEquals(0, list.size());
    }
}
