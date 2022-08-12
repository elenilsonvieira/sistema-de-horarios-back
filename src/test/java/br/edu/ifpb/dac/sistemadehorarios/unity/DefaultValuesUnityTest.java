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

import java.util.List;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DefaultValuesUnityTest {

    @Autowired
    private GapService gapService;
    @Autowired
    private ShiftService shiftService;
    @Autowired
    private WeekDayService weekDayService;
    @Autowired
    private UserService userService;
    @Test
    public void gapDefaultValues(){
        List<GapModel> list = this.gapService.read();
        assertNotEquals(0, list.size());

    }
    @Test
    public void shiftDefaultValues(){
        List<ShiftModel> list = this.shiftService.read();
        assertNotEquals(0, list.size());
    }
    @Test
    public void weekDayDefaultValues(){
        List<WeekDayModel> list = this.weekDayService.read();
        assertNotEquals(0, list.size());
    }
    @Test
    public void userDefaultValues(){
        List<UserModel> list = this.userService.read();
        assertNotEquals(0, list.size());
    }
}
