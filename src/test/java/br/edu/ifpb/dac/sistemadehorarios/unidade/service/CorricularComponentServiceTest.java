package br.edu.ifpb.dac.sistemadehorarios.unidade.service;

import br.edu.ifpb.dac.sistemadehorarios.DRO.CurricularComponentDRO;
import br.edu.ifpb.dac.sistemadehorarios.model.CurricularComponentModel;
import br.edu.ifpb.dac.sistemadehorarios.service.CurricularComponentService;
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
    private CurricularComponentService curricularComponentService;
    private String uuid = "cf7846a6-630a-4008-b961-fb67c16868fc";

    @Override
    @Test
    @Order(1)
    public void create() {
        CurricularComponentDRO DRO = new CurricularComponentDRO();
        DRO.setWorkload((byte) 120);
        DRO.setName("TÉCNICAS DE TESTES");
        DRO.setCourseUuid("e38dd5cc-a220-4c82-8648-2d24903b4877");
        DRO.setUuid(this.uuid);

        CurricularComponentModel curricularComponentModel = this.curricularComponentService.create(DRO);
        assertNotNull(curricularComponentModel);
    }
    @Test
    @Order(2)
    public void createWithoutCourse(){
        CurricularComponentDRO DRO = new CurricularComponentDRO();
        DRO.setWorkload((byte) 120);
        DRO.setName("TÉCNICAS DE TESTES");
        DRO.setUuid(this.uuid);

        CurricularComponentModel curricularComponentModel = this.curricularComponentService.create(DRO);
        assertNull(curricularComponentModel);
    }

    @Override
    @Test
    @Order(3)
    public void read() {
        List<CurricularComponentModel> corricularComponentModelList = this.curricularComponentService.read();
        assertNotEquals(0, corricularComponentModelList.size() );
    }

    @Override
    @Test
    @Order(4)
    public void update() {
        CurricularComponentModel curricularComponentModel = new CurricularComponentModel((byte) 60,"Análise de Algoritmos");
        boolean result = this.curricularComponentService.update(curricularComponentModel,this.uuid);
        assertTrue(result);
    }

    @Override
    @Test
    @Order(5)
    public void delete() {
        boolean result = this.curricularComponentService.delete(this.uuid);
        assertTrue(result);
    }
}
