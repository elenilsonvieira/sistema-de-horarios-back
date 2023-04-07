package br.edu.ifpb.dac.sistemadehorarios.suite;

import br.edu.ifpb.dac.sistemadehorarios.unity.CalendarDTOTest;
import br.edu.ifpb.dac.sistemadehorarios.unity.ClassDTOTest;
import br.edu.ifpb.dac.sistemadehorarios.unity.CourseDTOTest;
import br.edu.ifpb.dac.sistemadehorarios.unity.UserDTOTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        UserDTOTest.class,
        ClassDTOTest.class,
        CalendarDTOTest.class,
        CourseDTOTest.class,
})

public class JunitTestSuite {}
