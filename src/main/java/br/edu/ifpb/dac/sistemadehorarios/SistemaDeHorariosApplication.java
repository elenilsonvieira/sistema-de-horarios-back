package br.edu.ifpb.dac.sistemadehorarios;

import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Gap.GapService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Shift.ShiftService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.WeekDay.WeekDayService;
import br.edu.ifpb.dac.sistemadehorarios.entity.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;

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

    public static void main(String[] args) {

        SpringApplication.run(SistemaDeHorariosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        gapService.createDefaultValues();
        shiftService.createDefaultValues();
        weekDayService.createDefaultValues();
        userService.createDefaultValues();

    }

}
