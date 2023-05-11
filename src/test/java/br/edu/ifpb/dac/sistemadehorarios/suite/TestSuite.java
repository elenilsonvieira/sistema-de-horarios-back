package br.edu.ifpb.dac.sistemadehorarios.suite;

import br.edu.ifpb.dac.sistemadehorarios.integration.IntervalDTOSTest;
import br.edu.ifpb.dac.sistemadehorarios.integration.LessonDTOTest;
import br.edu.ifpb.dac.sistemadehorarios.unity.*;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
                ProfileServiceTest.class,
                ProfessorServiceTest.class,
                CourseServiceTest.class,
                TurmaServiceTest.class,
                TeacherDTOTest.class,
                CalendarDTOTest.class,
                ClassBlockDTOTest.class,
                ClassroomDTOTest.class,
                CourseDTOTest.class,
                ProfileDTOTest.class,
                TeacherDTOTest.class,
                UserDTOTest.class,
                IntervalDTOSTest.class,
                RestrictionDTOTest.class,
                CurricularComponentDTOTest.class,
                LessonDTOTest.class

})
public class TestSuite {
}