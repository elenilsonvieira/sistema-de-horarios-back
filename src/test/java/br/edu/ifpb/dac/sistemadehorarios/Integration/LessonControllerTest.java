package br.edu.ifpb.dac.sistemadehorarios.Integration;

import br.edu.ifpb.dac.sistemadehorarios.DRO.LessonDRO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class LessonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Should not create a lesson in database - tuple already exists")
    public void create() throws Exception  {

        String turmaUuid="5a499208-6048-44c0-8db3-fb3e1bbc8bdd";
        String curricularComponentlUuid="2d19725b-6b72-425a-bc0b-735138871598";
        String intervalUuid="64ecd039-873b-4c5c-95d9-793502134498";
        String professorUuid="4b2c37e2-be24-4336-9887-424a1b2b2f44";
        String classroomUuid="8f42ab25-bc18-4a7f-a6ac-7771c036d1e1";
        String calendarUuid="a65fed7d-38ec-4e35-b9c1-ea4830b8bb1c";

        LessonDRO dro = new LessonDRO();
        dro.setTurmaUuid(turmaUuid);
        dro.setCorricularComponentlUuid(curricularComponentlUuid);
        dro.setIntervalUuid(intervalUuid);
        dro.setProfessorUuid(professorUuid);
        dro.setClassroomUuid(classroomUuid);
        dro.setCalendarUuid(calendarUuid);
        mockMvc.perform(
                        post("/lesson/")
                                .contentType("application/json")
                                .content(this.objectMapper.writeValueAsString(dro)))
                .andExpect(status().is(400));
    }

    @Test
    @DisplayName("Should not create a lesson in database- invalid turmaUuid")
    public void createWithTurmaInvalid() throws Exception  {
        String turmaUuid="teste";
        String curricularComponentlUuid="2d19725b-6b72-425a-bc0b-735138871598";
        String intervalUuid="64ecd039-873b-4c5c-95d9-793502134498";
        String professorUuid="4b2c37e2-be24-4336-9887-424a1b2b2f44";
        String classroomUuid="8f42ab25-bc18-4a7f-a6ac-7771c036d1e1";
        String calendarUuid="a65fed7d-38ec-4e35-b9c1-ea4830b8bb1c";

        LessonDRO dro = new LessonDRO();
        dro.setTurmaUuid(turmaUuid);
        dro.setCorricularComponentlUuid(curricularComponentlUuid);
        dro.setIntervalUuid(intervalUuid);
        dro.setProfessorUuid(professorUuid);
        dro.setClassroomUuid(classroomUuid);
        dro.setCalendarUuid(calendarUuid);
        mockMvc.perform(
                        post("/lesson/")
                                .contentType("application/json")
                                .content(this.objectMapper.writeValueAsString(dro)))
                .andExpect(status().is(400));
    }
    @Test
    @DisplayName("Should not create a lesson in database- null turmaUuid")
    public void createWithTurmaNull() throws Exception  {
        String turmaUuid=null;
        String curricularComponentlUuid="2d19725b-6b72-425a-bc0b-735138871598";
        String intervalUuid="64ecd039-873b-4c5c-95d9-793502134498";
        String professorUuid="4b2c37e2-be24-4336-9887-424a1b2b2f44";
        String classroomUuid="8f42ab25-bc18-4a7f-a6ac-7771c036d1e1";
        String calendarUuid="a65fed7d-38ec-4e35-b9c1-ea4830b8bb1c";

        LessonDRO dro = new LessonDRO();
        dro.setTurmaUuid(turmaUuid);
        dro.setCorricularComponentlUuid(curricularComponentlUuid);
        dro.setIntervalUuid(intervalUuid);
        dro.setProfessorUuid(professorUuid);
        dro.setClassroomUuid(classroomUuid);
        dro.setCalendarUuid(calendarUuid);
        mockMvc.perform(
                        post("/lesson/")
                                .contentType("application/json")
                                .content(this.objectMapper.writeValueAsString(dro)))
                .andExpect(status().is(400));
    }

    @Test
    @DisplayName("Should not create a lesson in database- null fields")
    public void createWithFieldsNull() throws Exception  {
        String turmaUuid=null;
        String curricularComponentlUuid=null;
        String intervalUuid=null;
        String professorUuid=null;
        String classroomUuid=null;
        String calendarUuid=null;

        LessonDRO dro = new LessonDRO();
        dro.setTurmaUuid(turmaUuid);
        dro.setCorricularComponentlUuid(curricularComponentlUuid);
        dro.setIntervalUuid(intervalUuid);
        dro.setProfessorUuid(professorUuid);
        dro.setClassroomUuid(classroomUuid);
        dro.setCalendarUuid(calendarUuid);
        mockMvc.perform(
                        post("/lesson/")
                                .contentType("application/json")
                                .content(this.objectMapper.writeValueAsString(dro)))
                .andExpect(status().is(400));
    }

    @Test
    @DisplayName("Should not create a lesson in database- null fields")
    public void createWithoutDRO() throws Exception  {
        mockMvc.perform(
                        post("/lesson/")
                                .contentType("application/json")
                                .content(this.objectMapper.writeValueAsString(null)))
                .andExpect(status().is(400));
    }

    @Test
    @DisplayName("Should not create a lesson in database- null fields")
    public void createValid() throws Exception  {

        String turmaUuid="5a499208-6048-44c0-8db3-fb3e1bbc8bdd";
        String curricularComponentlUuid="2d19725b-6b72-425a-bc0b-735138871598";
        String intervalUuid="91deda24-9beb-4dfb-ae88-9206b7fb6986";
        String professorUuid="4b2c37e2-be24-4336-9887-424a1b2b2f44";
        String classroomUuid="8f42ab25-bc18-4a7f-a6ac-7771c036d1e1";
        String calendarUuid="a65fed7d-38ec-4e35-b9c1-ea4830b8bb1c";

        LessonDRO dro = new LessonDRO();
        dro.setTurmaUuid(turmaUuid);
        dro.setCorricularComponentlUuid(curricularComponentlUuid);
        dro.setIntervalUuid(intervalUuid);
        dro.setProfessorUuid(professorUuid);
        dro.setClassroomUuid(classroomUuid);
        dro.setCalendarUuid(calendarUuid);

        mockMvc.perform(
                        get("/lesson/")).
                andExpect(status().isOk());
        mockMvc.perform(
                        post("/lesson")
                                .contentType("application/json")
                                .content(this.objectMapper.writeValueAsString(dro)))
                .andExpect(status().is(201));
    }


}
