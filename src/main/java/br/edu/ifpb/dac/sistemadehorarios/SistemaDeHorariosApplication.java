package br.edu.ifpb.dac.sistemadehorarios;

import br.edu.ifpb.dac.sistemadehorarios.entity.Classroom.ClassBlock.ClassBlockService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Course.CourseService;
import br.edu.ifpb.dac.sistemadehorarios.entity.CurricularComponent.CurricularComponentService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Gap.GapService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Shift.ShiftService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.WeekDay.WeekDayService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Interval.IntervalService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Professor.ProfessorService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Turma.TurmaService;
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
    @Autowired
    private TurmaService turmaService;
    @Autowired
    private ProfessorService professorService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private CurricularComponentService curricularComponentService;

    public static void main(String[] args) {
        SpringApplication.run(SistemaDeHorariosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // courseService.createDefaultValues();
        gapService.createDefaultValues();
        shiftService.createDefaultValues();
        weekDayService.createDefaultValues();
        userService.createDefaultValues();
        classBlockService.createDefaultValues();
        intervalService.createDefaultValues();
        turmaService.createDefaultValues();
        curricularComponentService.createDefaultValues();
        professorService.createDefaultValues();
    }
}
