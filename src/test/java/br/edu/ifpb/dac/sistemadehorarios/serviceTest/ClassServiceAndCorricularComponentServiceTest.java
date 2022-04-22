package br.edu.ifpb.dac.sistemadehorarios.serviceTest;

import br.edu.ifpb.dac.sistemadehorarios.model.ClassModel;
import br.edu.ifpb.dac.sistemadehorarios.model.CorricularComponentModel;
import br.edu.ifpb.dac.sistemadehorarios.service.ClassService;
import br.edu.ifpb.dac.sistemadehorarios.service.CorricularComponentService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ClassServiceAndCorricularComponentServiceTest {

    private  ClassModel turma = new ClassModel("5° Período","ADS");


    @Autowired
    private CorricularComponentService corricularComponentService;
    @Autowired
    private ClassService classService;

    @Test
    @Order(1)
    void createClassModel(){
        boolean result = this.classService.create(turma);
        assertTrue(result);

    }

    @Test
    @Order(2)
    void createCorricularComponent(){
        CorricularComponentModel corricularComponentModel = new CorricularComponentModel(
                (byte) 120,
                "DAC",
                this.turma
        );
        boolean result = this.corricularComponentService.create(corricularComponentModel,"uuid");
        assertTrue(result);
    }

    @Test
    @Order(2)
    void createInvalidCorricularComponent(){
        CorricularComponentModel corricularComponentModel = new CorricularComponentModel(
                (byte) 120,
                "DAC",
                null
        );
        boolean result = this.corricularComponentService.create(corricularComponentModel, "<Fake UUID>");
        assertFalse(result);
    }



}
