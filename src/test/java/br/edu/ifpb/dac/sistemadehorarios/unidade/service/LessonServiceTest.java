package br.edu.ifpb.dac.sistemadehorarios.unidade.service;

import br.edu.ifpb.dac.sistemadehorarios.DRO.LessonDRO;
import br.edu.ifpb.dac.sistemadehorarios.exception.LessonInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.middleware.LessonMiddleware;
import br.edu.ifpb.dac.sistemadehorarios.service.LessonService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LessonServiceTest implements ServiceTest {

    @Autowired
    private LessonService service;
    @Autowired
    private LessonMiddleware middlaware;

    @Override
    @Test
    @DisplayName("create lesson that already exists")
    public void create() {
        LessonDRO dro = new LessonDRO();
        dro.setCalendarUuid("a65fed7d-38ec-4e35-b9c1-ea4830b8bb1c");
        dro.setClassroomUuid("8f42ab25-bc18-4a7f-a6ac-7771c036d1e1");
        dro.setProfessorUuid("4b2c37e2-be24-4336-9887-424a1b2b2f44");
        dro.setCurricularComponentUuid("2d19725b-6b72-425a-bc0b-735138871598");
        dro.setTurmaUuid("5a499208-6048-44c0-8db3-fb3e1bbc8bdd");

        assertThrows(LessonInvalidException.class, ()->middlaware.lessonEqualsValidation(dro));

        //assertThrows(LessonInvalidException.class, ()->this.service.create(dro) );
    }

    @Test
    public void read() {

    }

    @Test
    public void update() {

    }

    @Test
    public void delete() {

    }
}
