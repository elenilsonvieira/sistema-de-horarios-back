package br.edu.ifpb.dac.sistemadehorarios;

import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Gap.GapService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Shift.ShiftService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.WeekDay.WeekDayService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class SistemaDeHorariosApplication implements CommandLineRunner {

    @Autowired
    private RoleService roleService;
    @Autowired
    private GapService gapService;
    @Autowired
    private ShiftService shiftService;
    @Autowired
    private WeekDayService weekDayService;

    public static void main(String[] args) {

        SpringApplication.run(SistemaDeHorariosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        roleService.createDefaultValues();
        gapService.createDefaultValues();
        shiftService.createDefaultValues();
        weekDayService.createDefaultValues();
    }
}
