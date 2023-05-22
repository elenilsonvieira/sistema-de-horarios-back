package br.edu.ifpb.dac.sistemadehorarios.suite;

import br.edu.ifpb.dac.sistemadehorarios.integration.*;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        HealthCheckEndpointsTest.class,
        CurricularComponentIntegrationTest.class,
        ClassroomIntegrationTest.class,
        CalendarIntegrationTest.class,
        ProfileServiceIntegrationTest.class,
        ProfessorServiceIntegrationTest.class,
        CourseServiceIntegrationTest.class,
        TurmaServiceIntegrationTest.class,
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