package br.edu.ifpb.dac.sistemadehorarios.controllerTest;

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

import br.edu.ifpb.dac.sistemadehorarios.DTO.CorricularComponentDTO;
import br.edu.ifpb.dac.sistemadehorarios.model.CorricularComponentModel;
import jdk.jfr.Description;

@SpringBootTest
@AutoConfigureMockMvc
public class CorricularComponentControllerTest {
	
	private CorricularComponentModel ccModel = new CorricularComponentModel((byte) 120, "SO");
	
	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Description("Should be create a corricular component in database")
    @Order(1)
    public void postCorricularComponent() throws Exception {
        mockMvc.perform(
                        post("/corricularComponent/d1cb149c-ccb9-49be-b871-a18e006d4648")
                                .contentType("application/json")
                                .content(this.objectMapper.writeValueAsString(ccModel)))
                .andExpect(status().is(201));
    }

    @Test
    @Description("Should get all corricular component in database")
    @Order(2)
    public void getAllCorricularComponent() throws Exception {

       mockMvc.perform(
                        get("/corricularComponent")
                                .contentType("application/json"))
                .andExpect(status().is(200));

    }

    @Test
    @Description("Should get by uuid corricular component in database")
    @Order(3)
    public void getCorricularComponentByUuid() throws Exception {

        mockMvc.perform(
                        get("/corricularComponent/get-by-uuid/69d36ded-88b6-4c93-8b2c-bac9b947088c")
                                .contentType("application/json"))
                .andExpect(status().is(200));
    }

    @Test
    @Description("Should update corricular omponent in database")
    @Order(4)
    public void updateCorricularComponent() throws Exception {

        mockMvc.perform(
                        put("/corricularComponent/69d36ded-88b6-4c93-8b2c-bac9b947088c")
                                .contentType("application/json")
                                .content(this.objectMapper.writeValueAsString(new CorricularComponentDTO(ccModel))))
                .andExpect(status().is(200));
    }

    @Test
    @Description("Should delete corricular component in database")
    @Order(5)
    public void deleteCorricularComponent() throws Exception {
        mockMvc.perform(
                        delete("/corricularComponent/69d36ded-88b6-4c93-8b2c-bac9b947088c")
                                .contentType("application/json"))
                .andExpect(status().is(200));
    }
	

}
