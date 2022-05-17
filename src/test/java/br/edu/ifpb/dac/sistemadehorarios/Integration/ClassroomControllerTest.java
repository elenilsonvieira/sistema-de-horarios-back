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
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.edu.ifpb.dac.sistemadehorarios.DTO.ClassroomDTO;
import br.edu.ifpb.dac.sistemadehorarios.model.ClassroomModel;
import jdk.jfr.Description;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ClassroomControllerTest {
	
	
    private ClassroomModel classroomModel = new ClassroomModel("Sala 2", "Bloco A", 40);
    private ClassroomModel classroomModelUpdate = new ClassroomModel("Sala 3", "Bloco C", 32);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Description("Should be create a classroom in database")
    @Order(1)
    public void postClassroom() throws Exception {
    	// nova sala
        mockMvc.perform(
                        post("/classroom")
                                .contentType("application/json")
                                .content(this.objectMapper.writeValueAsString(classroomModel)))
                .andExpect(status().is(201));
        
        // salas duplicadas
        mockMvc.perform(
                post("/classroom")
                        .contentType("application/json")
                        .content(this.objectMapper.writeValueAsString(classroomModel)))
        .andExpect(status().is(400));
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
    	//id existente
        mockMvc.perform(
                        get("/classroom/get-by-uuid/8f42ab25-bc18-4a7f-a6ac-7771c036d1e1")
                                .contentType("application/json"))
                .andExpect(status().is(200));
        
        // id inexistente
        mockMvc.perform(
                get("/classroom/get-by-uuid/8f42ab25-bc18-4a7f-a6ac-7771c036d1e7")
                        .contentType("application/json"))
        .andExpect(status().is(404));
    }

    @Test
    @Description("Should update classroom in database")
    @Order(4)
    public void updateClassroom() throws Exception {
        mockMvc.perform(
                        put("/classroom/8f42ab25-bc18-4a7f-a6ac-7771c036d1e1")
                                .contentType("application/json")
                                .content(this.objectMapper.writeValueAsString(new ClassroomDTO(classroomModelUpdate))))
                .andExpect(status().is(200));
    }

    @Test
    @Description("Should delete classroom in database")
    @Order(5)
    public void deleteClassroom() throws Exception {
        mockMvc.perform(
                        delete("/classroom/bd37436b-2f32-496f-bf48-87384728df6b")
                                .contentType("application/json"))
                .andExpect(status().is(200));
        
        // id inexistente
        mockMvc.perform(
                delete("/classroom/8f42ab25-bc18-4a7f-a6ac-7771c036d185")
                        .contentType("application/json"))
        .andExpect(status().is(404));
    }

}