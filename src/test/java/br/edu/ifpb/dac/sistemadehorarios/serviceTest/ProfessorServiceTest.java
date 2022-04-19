package br.edu.ifpb.dac.sistemadehorarios.serviceTest;

import br.edu.ifpb.dac.sistemadehorarios.model.ProfessorModel;
import br.edu.ifpb.dac.sistemadehorarios.service.ProfessorService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProfessorServiceTest {

    private ProfessorModel professorModel[] = {
            new ProfessorModel(String.valueOf(UUID.randomUUID()), "Testes"),
            new ProfessorModel(String.valueOf(UUID.randomUUID()), "Projetos"),
            new ProfessorModel(String.valueOf(UUID.randomUUID()), "AA"),
    };

    @Autowired
    private ProfessorService service;


    @Test
    @Order(1)
    void create() {
        for (ProfessorModel professor: this.professorModel) {
            boolean result = service.create(professor);
            assertTrue(result);
        }
    }

    @Test
    @Order(2)
    void read() {
        List<ProfessorModel> professor = service.read();
        assertNotEquals(0,professor.size());
    }

    @Test
    @Order(3)
    void update() {
        ProfessorModel professorModel = new ProfessorModel(this.professorModel[0].getName(), "Tec");
        boolean result = this.service.update(professorModel, this.professorModel[0].getUuid());
        assertTrue(result);
    }

    @Test
    @Order(4)
    void delete() {
        boolean result = service.delete(professorModel[0].getUuid());
        assertTrue(result);
    }

    @Test
    @Order(5)
    void equalsName(){
        ProfessorModel professorModel1 = new ProfessorModel("Fuleco","AA");
        ProfessorModel professorModel2 = new ProfessorModel("Fuleco","AA");

        boolean resultProfessor1 = this.service.create(professorModel1);
        assertTrue(resultProfessor1);
        boolean resultProfessor2 = this.service.create(professorModel2);
        assertFalse(resultProfessor2);
    }

}



















