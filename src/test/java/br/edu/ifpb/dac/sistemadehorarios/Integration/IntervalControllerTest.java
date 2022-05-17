package br.edu.ifpb.dac.sistemadehorarios.Integration;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.edu.ifpb.dac.sistemadehorarios.ENUM.ShiftEnum;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.edu.ifpb.dac.sistemadehorarios.DTO.IntervalDTO;
import br.edu.ifpb.dac.sistemadehorarios.ENUM.DayOfWeekEnum;
import br.edu.ifpb.dac.sistemadehorarios.model.IntervalModel;
import jdk.jfr.Description;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class IntervalControllerTest {

    IntervalModel intervalModel = new IntervalModel(1, DayOfWeekEnum.THURSDAY, ShiftEnum.NIGHT);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Description("Should be create a interval in database")
    @Order(1)
    public void postGap() throws Exception {
        mockMvc.perform(
                        post("/interval")
                                .contentType("application/json")
                                .content(this.objectMapper.writeValueAsString(intervalModel)))
                .andExpect(status().is(201));
    }

    @Test
    @Description("Should get all intervals in database")
    @Order(2)
    public void getAllGap() throws Exception {

        mockMvc.perform(
                        get("/interval")
                                .contentType("application/json"))
                .andExpect(status().is(200));

    }

    @Test
    @Description("Should get by uuid intervals in database")
    @Order(3)
    public void getGapByUuid() throws Exception {

        mockMvc.perform(
                        get("/interval/get-by-uuid/eaf68bb2-2c0f-4c6a-b7ee-914f3f86a204")
                                .contentType("application/json"))
                .andExpect(status().is(200));
    }

    @Test
    @Description("Should update interval in database")
    @Order(4)
    public void updateGap() throws Exception {

        mockMvc.perform(
                        put("/interval/b079b545-97df-488d-b380-e8e03483b950")
                                .contentType("application/json")
                                .content(this.objectMapper.writeValueAsString(new IntervalDTO(intervalModel))))
                .andExpect(status().is(400));
    }

    @Test
    @Description("Should delete interval in database")
    @Order(5)
    public void deleteGap() throws Exception {
        mockMvc.perform(
                        delete("/interval/b748440b-f435-4708-b9dc-dd63c31ad1be")
                                .contentType("application/json"))
                .andExpect(status().is(200));
    }


}
