package br.edu.ifpb.dac.sistemadehorarios.integration;

import br.edu.ifpb.dac.sistemadehorarios.DTO.UserDTO;
import br.edu.ifpb.dac.sistemadehorarios.entity.User.utils.LoginDRO;
import br.edu.ifpb.dac.sistemadehorarios.entity.User.UserModel;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Value("${password.victor}")
    private String passwordVictor;

    public static String token;
    public static String userUuid;

    @Test
    @Order(1)
    public void postUserWithoutSecretKeyAndWithoutToken() throws Exception {
        UserModel userModel = new UserModel();
        userModel.setEmail("4e0a8fdc-22cd-4fe6-8129-559b1236c86c");
        userModel.setName("João Victor Lacerda");
        userModel.setPass("123456");

        mockMvc.perform(
                post("/user")
                        .contentType("application/json")
                        .content(this.objectMapper.writeValueAsString(userModel)))
                .andExpect(status().is(403));
    }

    @Test
    @Order(2)
    public void postUserWithSecretKeyAndWithoutToken() throws Exception {
        UserModel userModel = new UserModel();
        userModel.setEmail("4e0a8fdc-22cd-4fe6-8129-559b1236c86c");
        userModel.setName("João Victor Lacerda");
        userModel.setPass("123456");

        ResultActions result = mockMvc.perform(
                        post("/user")
                                .contentType("application/json")
                                .content(this.objectMapper.writeValueAsString(userModel)))
                .andExpect(status().is(403));
    }

    @Test
    @Order(3)
    public void login() throws Exception {
        LoginDRO login = new LoginDRO();
        login.setEmail("victor.queiroz@academico.ifpb.edu.br");
        login.setPass(this.passwordVictor);

        ResultActions result = mockMvc.perform(
                        post("/user/login")
                                .contentType("application/json")
                                .content(this.objectMapper.writeValueAsString(login)))
                .andExpect(status().is(202));

        MvcResult mvcResult = result.andReturn();
        String response = mvcResult.getResponse().getContentAsString();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        UserDTO userDTO = gson.fromJson(response,UserDTO.class);
        UserIntegrationTest.token = userDTO.getToken();
    }
    @Test
    @Order(4)
    public void postUserWithSecretKeyAndToken() throws Exception {

        UserModel userModel = new UserModel();
        userModel.setEmail("4e0a8fdc-22cd-4fe6-8129-559b1236c86c");
        userModel.setName("Fulano");
        userModel.setPass("123456");
        UserIntegrationTest.userUuid = userModel.getUuid();

        mockMvc.perform(
                        post("/user")
                                .contentType("application/json")
                                .header("Authorization", "Bearer "+UserIntegrationTest.token)
                                .content(this.objectMapper.writeValueAsString(userModel)))
                .andExpect(status().is(201));
    }

    @Test
    @Order(4)
    public void deleteUser() throws Exception {
        mockMvc.perform(
                        delete("/user/"+UserIntegrationTest.userUuid)
                                .header("Authorization", "Bearer "+UserIntegrationTest.token))
                .andExpect(status().is(200));
    }
}
