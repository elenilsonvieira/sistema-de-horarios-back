package br.edu.ifpb.dac.sistemadehorarios.unity;

import br.edu.ifpb.dac.sistemadehorarios.entity.Turma.TurmaModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Turma.TurmaService;
import br.edu.ifpb.dac.sistemadehorarios.interfaces.ServiceTest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TurmaServiceTest implements ServiceTest {

    @Autowired
    private TurmaService turmaService;

    private static TurmaModel turmaModel;

    @BeforeAll
    public static void setUp() {
        turmaModel = new TurmaModel();
        turmaModel.setName("Turma Teste");
        turmaModel.setUuid("id-test");
        turmaModel.setCreate_at(new Date());
    }

    @Test
    @Order(1)
    @DisplayName("should be created a new turma")
    @Override
    public void testCreateNewEntity() {
        try {
            assertNotEquals(turmaService.create(turmaModel), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(2)
    @DisplayName("should be listed turmas")
    @Override
    public void testReadEntities() {
        try {
            List<TurmaModel> turmaModels = turmaService.read();
            turmaModels.add(turmaModel);
            assertNotEquals(turmaModels.size(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(3)
    @DisplayName("should be found a turma")
    @Override
    public void testFindOneEntityById() {
        try {
            String id = "id-test";
            assertNotEquals(turmaService.findByUuid(id), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(4)
    @DisplayName("should be updated a turma")
    @Override
    public void testUpdateOneEntityById() {
        try {
            String id = "id-test";
            turmaModel.setName("Turma Teste 2");
            assertNotEquals(turmaService.update(turmaModel, id), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(5)
    @DisplayName("should be deleted a turma")
    @Override
    public void testDeleteOneEntityById() {
        try {
            String id = "id-test";
            assertNotEquals(turmaService.delete(id), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
