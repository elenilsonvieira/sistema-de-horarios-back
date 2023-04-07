package br.edu.ifpb.dac.sistemadehorarios.integration;

import br.edu.ifpb.dac.sistemadehorarios.DTO.LoginDTO;
import br.edu.ifpb.dac.sistemadehorarios.DTO.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public abstract class HealthCheckOfEndpointsIntegrationTest {

    public static String token;

    public void login(MockMvc mockMvc, ObjectMapper objectMapper, String passwordVictor) throws Exception {
        LoginDTO login = new LoginDTO();
        login.setPass(passwordVictor);

        ResultActions result = mockMvc.perform(
                        post("/user/login")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(login)))
                .andExpect(status().is(200));

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
                .andExpect(status().is(200));
    }

    public void testWithoutToken(MockMvc mockMvc, String endpoint) throws Exception {
        mockMvc.perform(
                        get(endpoint))
                .andExpect(status().is(403));
    }
}
