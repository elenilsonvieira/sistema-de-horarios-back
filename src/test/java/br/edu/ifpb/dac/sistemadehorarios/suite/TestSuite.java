package br.edu.ifpb.dac.sistemadehorarios.suite;

import br.edu.ifpb.dac.sistemadehorarios.newUnity.CourseServiceUnityTest;
import br.edu.ifpb.dac.sistemadehorarios.newUnity.ProfessorServiceUnityTest;
import br.edu.ifpb.dac.sistemadehorarios.newUnity.ProfileServiceUnityTest;
import br.edu.ifpb.dac.sistemadehorarios.newUnity.TurmaServiceUnityTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;


@Suite
@SelectClasses({
        ProfileServiceUnityTest.class,
        ProfessorServiceUnityTest.class,
        CourseServiceUnityTest.class,
        TurmaServiceUnityTest.class
})
public class TestSuite {

}