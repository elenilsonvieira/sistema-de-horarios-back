package br.edu.ifpb.dac.sistemadehorarios.unidade.service;

import br.edu.ifpb.dac.sistemadehorarios.DRO.ProfessorDRO;
import br.edu.ifpb.dac.sistemadehorarios.exception.ProfessorInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.model.ProfessorModel;
import br.edu.ifpb.dac.sistemadehorarios.service.ProfessorService;
import jdk.jfr.Description;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class ProfessorServiceTest implements ServiceTest{

    @Autowired
    private ProfessorService professorService;
    private String uuid = "cf7846a6-630a-4008-b961-fb67c16868fc";

    @Test
    @Order(1)
    public void create() throws ProfessorInvalidException {
        ProfessorDRO professorDRO = new ProfessorDRO();
        professorDRO.setName(UUID.randomUUID().toString());
        professorDRO.setArea("programação");
        professorDRO.setUuid(this.uuid);
        professorDRO.setCourseUuid("e38dd5cc-a220-4c82-8648-2d24903b4877");
        ProfessorModel result = this.professorService.create(professorDRO);
        assertNotNull(result);
    }

    @Test
    @Order(2)
    @Description("Should be try create a invalid professor(its ivalible because the name is unique in the context)")
    public void createInvalidProfessor() throws ProfessorInvalidException {
        ProfessorDRO professorDRO = new ProfessorDRO();
        professorDRO.setName(UUID.randomUUID().toString());
        professorDRO.setArea("programação");
        ProfessorModel result = this.professorService.create(professorDRO);
        assertNull(result);
    }

    @Test
    @Order(3)
    public void read() {
        List<ProfessorModel> professorModelList = this.professorService.read();
        assertNotEquals(0, professorModelList.size() );
    }

    @Test
    @Order(4)
    public void update() {
        ProfessorModel professorModel = new ProfessorModel("Teste update", "Programação");
        boolean result = this.professorService.update(professorModel,this.uuid);
        assertTrue(result);
    }

    @Test
    @Order(5)
    public void delete() {
        boolean result = this.professorService.delete(this.uuid);
        assertTrue(result);
    }

}
