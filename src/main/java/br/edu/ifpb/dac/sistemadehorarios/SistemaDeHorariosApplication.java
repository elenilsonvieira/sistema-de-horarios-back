package br.edu.ifpb.dac.sistemadehorarios;

import br.edu.ifpb.dac.sistemadehorarios.entity.Classroom.ClassBlock.ClassBlockService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Gap.GapService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Shift.ShiftService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.WeekDay.WeekDayService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Interval.IntervalService;
import br.edu.ifpb.dac.sistemadehorarios.entity.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SistemaDeHorariosApplication implements CommandLineRunner {

    @Autowired
    private GapService gapService;
    @Autowired
    private ShiftService shiftService;
    @Autowired
    private WeekDayService weekDayService;
    @Autowired
    private UserService userService;
    @Autowired
    private ClassBlockService classBlockService;
    @Autowired
    private IntervalService intervalService;

    public static void main(String[] args) {

        SpringApplication.run(SistemaDeHorariosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        gapService.createDefaultValues();
        shiftService.createDefaultValues();
        weekDayService.createDefaultValues();
        userService.createDefaultValues();
        classBlockService.createDefaultValues();
        intervalService.createDefaultValues();
    }
}
