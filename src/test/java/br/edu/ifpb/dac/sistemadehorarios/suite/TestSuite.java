package br.edu.ifpb.dac.sistemadehorarios.suite;

import br.edu.ifpb.dac.sistemadehorarios.integration.IntervalDTOSTest;
import br.edu.ifpb.dac.sistemadehorarios.integration.LessonDTOTest;
import br.edu.ifpb.dac.sistemadehorarios.newUnity.CourseServiceUnityTest;
import br.edu.ifpb.dac.sistemadehorarios.newUnity.ProfessorServiceUnityTest;
import br.edu.ifpb.dac.sistemadehorarios.newUnity.ProfileServiceUnityTest;
import br.edu.ifpb.dac.sistemadehorarios.newUnity.TurmaServiceUnityTest;
import br.edu.ifpb.dac.sistemadehorarios.unity.*;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;


@Suite
@SelectClasses({
        ProfileServiceUnityTest.class,
        ProfessorServiceUnityTest.class,
        CourseServiceUnityTest.class,
        TurmaServiceUnityTest.class,
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