package br.edu.ifpb.dac.sistemadehorarios.Integration;

import br.edu.ifpb.dac.sistemadehorarios.DTO.ProfessorDTO;
import br.edu.ifpb.dac.sistemadehorarios.model.ProfessorModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jfr.Description;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProfessorControllerTest {

    private ProfessorModel professorModel = new ProfessorModel("teste abc", "abc");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Description("Should be create a professor in database")
    @Order(1)
    public void postProfessor() throws Exception {
        mockMvc.perform(
                        post("/professor")
                                .contentType("application/json")
                                .content(this.objectMapper.writeValueAsString(new ProfessorDTO(professorModel))))
                .andExpect(status().is(201));
    }

    @Test
    @Description("Should get all professors in database")
    @Order(2)
    public void getAllProfessor() throws Exception {

        mockMvc.perform(
                        get("/professor")
                                .contentType("application/json"))
                .andExpect(status().is(200));

    }

    @Test
    @Description("Should get by uuid professors in database")
    @Order(3)
    public void getProfessorByUuid() throws Exception {

        mockMvc.perform(
                        get("/professor/get-by-uuid/2a164b86-df8c-4a7b-9307-f87409b4da16")
                                .contentType("application/json"))
                .andExpect(status().is(200));
    }

    @Test
    @Description("Should update professor in database")
    @Order(4)
    public void updateProfessor() throws Exception {

        mockMvc.perform(
                        put("/professor/2a164b86-df8c-4a7b-9307-f87409b4da16")
                                .contentType("application/json")
                                .content(this.objectMapper.writeValueAsString(professorModel)))
                .andExpect(status().is(200));
    }

    @Test
    @Description("Should delete professor in database")
    @Order(5)
    public void deleteProfessor() throws Exception {
        mockMvc.perform(
                        delete("/professor/8753bc02-0248-4ac9-9e5c-cc8a95b3304b")
                                .contentType("application/json"))
                .andExpect(status().is(200));
    }

}