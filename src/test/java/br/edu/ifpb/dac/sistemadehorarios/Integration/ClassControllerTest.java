package br.edu.ifpb.dac.sistemadehorarios.Integration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.edu.ifpb.dac.sistemadehorarios.DTO.TurmaDTO;
import br.edu.ifpb.dac.sistemadehorarios.model.TurmaModel;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import jdk.jfr.Description;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ClassControllerTest {

    private TurmaModel turmaModel = new TurmaModel("2020.1");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Description("Should be create a class in database")
    @Order(1)
    public void postClass() throws Exception {
        mockMvc.perform(
                        post("/turma")
                                .contentType("application/json")
                                .content(this.objectMapper.writeValueAsString(new TurmaDTO(turmaModel))))
                .andExpect(status().is(201));
    }

    @Test
    @Description("Should get all turmaes in database")
    @Order(2)
    public void getAllClass() throws Exception {

        mockMvc.perform(
                        get("/turma")
                                .contentType("application/json"))
                .andExpect(status().is(200));

    }

    @Test
    @Description("Should get by uuid turmaes in database")
    @Order(3)
    public void getClassByUuid() throws Exception {

        mockMvc.perform(
                        get("/turma/get-by-uuid/5a499208-6048-44c0-8db3-fb3e1bbc8bdd")
                                .contentType("application/json"))
                .andExpect(status().is(200));
    }

    @Test
    @Description("Should update turma in database")
    @Order(4)
    public void updateClass() throws Exception {

        mockMvc.perform(
                        put("/turma/5a499208-6048-44c0-8db3-fb3e1bbc8bdd")
                                .contentType("application/json")
                                .content(this.objectMapper.writeValueAsString(new TurmaDTO(turmaModel))))
                .andExpect(status().is(200));
    }

    @Test
    @Description("Should delete turma in database")
    @Order(5)
    public void deleteClass() throws Exception {
        mockMvc.perform(
                        delete("/turma/5a499208-6048-44c0-8db3-fb3e1bbc8bdd")
                                .contentType("application/json"))
                .andExpect(status().is(400));
    }


}