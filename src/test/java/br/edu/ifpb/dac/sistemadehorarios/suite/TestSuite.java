package br.edu.ifpb.dac.sistemadehorarios.suite;

import br.edu.ifpb.dac.sistemadehorarios.integration.*;
import br.edu.ifpb.dac.sistemadehorarios.system.CreateInfoTest;
import br.edu.ifpb.dac.sistemadehorarios.system.EditInfoTest;
import br.edu.ifpb.dac.sistemadehorarios.system.LoginTest;
import br.edu.ifpb.dac.sistemadehorarios.system.RoutesTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
//        LoginTest.class,
        CreateInfoTest.class,
        EditInfoTest.class,
//        RoutesTest.class
})
public class TestSuite {
}