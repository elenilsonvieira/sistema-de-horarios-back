package br.edu.ifpb.dac.sistemadehorarios.integration;

import br.edu.ifpb.dac.sistemadehorarios.DTO.LoginDTO;
import br.edu.ifpb.dac.sistemadehorarios.DTO.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


public abstract class HealthCheckOfEndpointsIntegrationTest {

    public static String token;

    public void login(MockMvc mockMvc, ObjectMapper objectMapper, String enrollment, String password) throws Exception {
        LoginDTO login = new LoginDTO();
//        Setando aqui
        login.setEnrollment(enrollment);
        login.setPass(password);

        ResultActions result = mockMvc.perform(
                        post("/auth/login")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(login)))
                        .andExpect(status().is(200))
                        .andDo(print());

        MvcResult mvcResult = result.andReturn();
        String response = mvcResult.getResponse().getContentAsString();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        UserDTO userDTO = gson.fromJson(response,UserDTO.class);
        HealthCheckOfEndpointsIntegrationTest.token = userDTO.getToken();
    }

    public void testWithToken(MockMvc mockMvc, String endpoint) throws Exception {
        mockMvc.perform(
                        get(endpoint)
                        .header("Authorization", "Bearer "+HealthCheckOfEndpointsIntegrationTest.token))
                .andExpect(status().is(200)).andDo(print());;
    }

    public void testWithoutToken(MockMvc mockMvc, String endpoint) throws Exception {
        mockMvc.perform(
                        get(endpoint))
                .andExpect(status().is(403)).andDo(print());
    }
}
