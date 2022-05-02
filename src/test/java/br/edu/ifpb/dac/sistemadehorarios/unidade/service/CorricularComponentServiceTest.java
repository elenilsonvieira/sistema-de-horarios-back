package br.edu.ifpb.dac.sistemadehorarios.unidade.service;

import br.edu.ifpb.dac.sistemadehorarios.DRO.CorricularComponentDRO;
import br.edu.ifpb.dac.sistemadehorarios.model.CorricularComponentModel;
import br.edu.ifpb.dac.sistemadehorarios.service.CorricularComponentService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CorricularComponentServiceTest implements ServiceTest{

    @Autowired
    private CorricularComponentService corricularComponentService;
    private String uuid = "cf7846a6-630a-4008-b961-fb67c16868fc";

    @Override
    @Test
    @Order(1)
    public void create() {
        CorricularComponentDRO DRO = new CorricularComponentDRO();
        DRO.setWorkload((byte) 120);
        DRO.setName("TÉCNICAS DE TESTES");
        DRO.setCourseUuid("e38dd5cc-a220-4c82-8648-2d24903b4877");
        DRO.setUuid(this.uuid);

        CorricularComponentModel corricularComponentModel = this.corricularComponentService.create(DRO);
        assertNotNull(corricularComponentModel);
    }
    @Test
    @Order(2)
    public void createWithoutCourse(){
        CorricularComponentDRO DRO = new CorricularComponentDRO();
        DRO.setWorkload((byte) 120);
        DRO.setName("TÉCNICAS DE TESTES");
        DRO.setUuid(this.uuid);

        CorricularComponentModel corricularComponentModel = this.corricularComponentService.create(DRO);
        assertNull(corricularComponentModel);
    }

    @Override
    @Test
    @Order(3)
    public void read() {
        List<CorricularComponentModel> corricularComponentModelList = this.corricularComponentService.read();
        assertNotEquals(0, corricularComponentModelList.size() );
    }

    @Override
    @Test
    @Order(4)
    public void update() {
        CorricularComponentModel corricularComponentModel = new CorricularComponentModel((byte) 60,"Análise de Algoritmos");
        boolean result = this.corricularComponentService.update(corricularComponentModel,this.uuid);
        assertTrue(result);
    }

    @Override
    @Test
    @Order(5)
    public void delete() {
        boolean result = this.corricularComponentService.delete(this.uuid);
        assertTrue(result);
    }
}
