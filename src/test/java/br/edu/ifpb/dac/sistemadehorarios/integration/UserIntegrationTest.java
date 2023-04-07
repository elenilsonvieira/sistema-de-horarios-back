package br.edu.ifpb.dac.sistemadehorarios.integration;

import br.edu.ifpb.dac.sistemadehorarios.DTO.LoginDTO;
import br.edu.ifpb.dac.sistemadehorarios.DTO.UserDTO;
import br.edu.ifpb.dac.sistemadehorarios.entity.User.RoleEnum;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @Value("${password.victor}")
    private String passwordVictor;

    public static String token;
    public static String userUuid;


    @Test
    @Order(1)
    public void postUserWithoutSecretKeyAndWithoutToken() throws Exception {
        UserModel userModel = new UserModel();
        userModel.setEnrollment("312231");
        userModel.setName("João Victor Lacerda");
        userModel.setRoles(String.format("%s,%s", RoleEnum.READ, RoleEnum.EDIT));


        mockMvc.perform(
                post("/user")
                        .contentType("application/json")
                        .content(this.gson.toJson(userModel)))
                .andExpect(status().is(403));
    }

    @Test
    @Order(3)
    public void login() throws Exception {
        LoginDTO login = new LoginDTO();
        login.setEnrollment("21313231");
        login.setPass(this.passwordVictor);

        ResultActions result = mockMvc.perform(
                        post("/user/login")
                                .contentType("application/json")
                                .content(this.gson.toJson(login)))
                .andExpect(status().is(200));

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
        userModel.setEnrollment("213123123");
        userModel.setName("Fulano");
        userModel.setRoles(String.format("%s,%s", RoleEnum.READ, RoleEnum.EDIT));
        userModel.setCreate_at(null);

        UserIntegrationTest.userUuid = userModel.getUuid();

        mockMvc.perform(
                        post("/user")
                                .contentType("application/json")
                                .header("Authorization", "Bearer "+UserIntegrationTest.token)
                                .content(this.gson.toJson(userModel)))
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
