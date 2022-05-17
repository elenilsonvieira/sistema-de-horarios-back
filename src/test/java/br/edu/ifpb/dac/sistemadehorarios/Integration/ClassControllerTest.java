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

    private TurmaModel classModel = new TurmaModel("2020.1");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Description("Should be create a class in database")
    @Order(1)
    public void postClass() throws Exception {
        mockMvc.perform(
                        post("/class")
                                .contentType("application/json")
                                .content(this.objectMapper.writeValueAsString(new TurmaDTO(classModel))))
                .andExpect(status().is(201));
    }

    @Test
    @Description("Should get all classes in database")
    @Order(2)
    public void getAllClass() throws Exception {

        mockMvc.perform(
                        get("/class")
                                .contentType("application/json"))
                .andExpect(status().is(200));

    }

    @Test
    @Description("Should get by uuid classes in database")
    @Order(3)
    public void getClassByUuid() throws Exception {

        mockMvc.perform(
                        get("/class/get-by-uuid/04db65c6-addd-4117-a9c9-d4025a638fb3")
                                .contentType("application/json"))
                .andExpect(status().is(200));
    }

    @Test
    @Description("Should update class in database")
    @Order(4)
    public void updateClass() throws Exception {

        mockMvc.perform(
                        put("/class/04db65c6-addd-4117-a9c9-d4025a638fb3")
                                .contentType("application/json")
                                .content(this.objectMapper.writeValueAsString(new TurmaDTO(classModel))))
                .andExpect(status().is(200));
    }

    @Test
    @Description("Should delete class in database")
    @Order(5)
    public void deleteClass() throws Exception {
        mockMvc.perform(
                        delete("/class/04db65c6-addd-4117-a9c9-d4025a638fb3")
                                .contentType("application/json"))
                .andExpect(status().is(200));
    }


}