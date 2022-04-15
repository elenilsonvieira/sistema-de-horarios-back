package br.edu.ifpb.dac.sistemadehorarios.serviceTest;

import br.edu.ifpb.dac.sistemadehorarios.model.ProfessorModel;
import br.edu.ifpb.dac.sistemadehorarios.service.ProfessorService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProfessorServiceTest {

    private ProfessorModel professorModel[] = {
            new ProfessorModel("Tiago", "Testes"),
            new ProfessorModel("Elenilson", "Projetos"),
            new ProfessorModel("Larissa", "AA"),
    };

    @Autowired
    private ProfessorService service;


    @Test
    @Order(1)
    void save() {
        for (ProfessorModel professor: this.professorModel) {
            boolean result = service.create(professor);
            assertTrue(result);
        }
    }

    @Test
//    @Order(2)
    @Disabled
    void delete() {
//        ProfessorModel professor = service.delete(professorModel[0].getUuid());
//        assertNotNull(professor);
    }

    @Test
    @Order(2)
    void read() throws InterruptedException {
        Thread.sleep(500);
        List<ProfessorModel> professor = service.read();
        assertNotEquals(0,professor.size());
    }

}



















