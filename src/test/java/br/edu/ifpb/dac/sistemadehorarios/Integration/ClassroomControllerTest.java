package br.edu.ifpb.dac.sistemadehorarios.Integration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.edu.ifpb.dac.sistemadehorarios.DTO.ClassroomDTO;
import br.edu.ifpb.dac.sistemadehorarios.model.ClassroomModel;
import jdk.jfr.Description;

@SpringBootTest
@AutoConfigureMockMvc
public class ClassroomControllerTest {

    private ClassroomModel classroomModel = new ClassroomModel("Sala 1", "Bloco A", 40);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Description("Should be create a classroom in database")
    @Order(1)
    public void postClassroom() throws Exception {
        mockMvc.perform(
                        post("/classroom")
                                .contentType("application/json")
                                .content(this.objectMapper.writeValueAsString(classroomModel)))
                .andExpect(status().is(201));
    }

    @Test
    @Description("Should get all classrooms in database")
    @Order(2)
    public void getAllClassroom() throws Exception {

        mockMvc.perform(
                        get("/classroom")
                                .contentType("application/json"))
                .andExpect(status().is(200));

    }

    @Test
    @Description("Should get by uuid classrooms in database")
    @Order(3)
    public void getClasroomByUuid() throws Exception {

        mockMvc.perform(
                        get("/classroom/get-by-uuid/04db65c6-addd-4117-a9c9-d4025a638fb3")
                                .contentType("application/json"))
                .andExpect(status().is(200));
    }

    @Test
    @Description("Should update classroom in database")
    @Order(4)
    public void updateClassroom() throws Exception {

        mockMvc.perform(
                        put("/classroom/04db65c6-addd-4117-a9c9-d4025a638fb3")
                                .contentType("application/json")
                                .content(this.objectMapper.writeValueAsString(new ClassroomDTO(classroomModel))))
                .andExpect(status().is(200));
    }

    @Test
    @Description("Should delete classroom in database")
    @Order(5)
    public void deleteClassroom() throws Exception {
        mockMvc.perform(
                        delete("/classroom/04db65c6-addd-4117-a9c9-d4025a638fb3")
                                .contentType("application/json"))
                .andExpect(status().is(200));
    }

}