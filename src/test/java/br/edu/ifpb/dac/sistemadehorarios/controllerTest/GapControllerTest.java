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

import br.edu.ifpb.dac.sistemadehorarios.DTO.GapDTO;
import br.edu.ifpb.dac.sistemadehorarios.ENUM.DayOfWeekEnum;
import br.edu.ifpb.dac.sistemadehorarios.model.GapModel;
import jdk.jfr.Description;

@SpringBootTest
@AutoConfigureMockMvc
public class GapControllerTest {
	
	GapModel gapModel = new GapModel(1, DayOfWeekEnum.THURSDAY);
	
	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Description("Should be create a gap in database")
    @Order(1)
    public void postGap() throws Exception {
        mockMvc.perform(
                        post("/gap")
                                .contentType("application/json")
                                .content(this.objectMapper.writeValueAsString(gapModel)))
                .andExpect(status().is(201));
    }

    @Test
    @Description("Should get all gaps in database")
    @Order(2)
    public void getAllGap() throws Exception {

       mockMvc.perform(
                        get("/gap")
                                .contentType("application/json"))
                .andExpect(status().is(200));

    }

    @Test
    @Description("Should get by uuid gaps in database")
    @Order(3)
    public void getGapByUuid() throws Exception {

        mockMvc.perform(
                        get("/gap/get-by-uuid/eaf68bb2-2c0f-4c6a-b7ee-914f3f86a204")
                                .contentType("application/json"))
                .andExpect(status().is(200));
    }

    @Test
    @Description("Should update gap in database")
    @Order(4)
    public void updateGap() throws Exception {

        mockMvc.perform(
                        put("/gap/b079b545-97df-488d-b380-e8e03483b950")
                                .contentType("application/json")
                                .content(this.objectMapper.writeValueAsString(new GapDTO(gapModel))))
                .andExpect(status().is(400));
    }

    @Test
    @Description("Should delete gap in database")
    @Order(5)
    public void deleteGap() throws Exception {
        mockMvc.perform(
                        delete("/gap/b748440b-f435-4708-b9dc-dd63c31ad1be")
                                .contentType("application/json"))
                .andExpect(status().is(200));
    }


}
