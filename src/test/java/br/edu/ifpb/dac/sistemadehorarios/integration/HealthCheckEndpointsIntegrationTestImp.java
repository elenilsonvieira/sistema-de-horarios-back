package br.edu.ifpb.dac.sistemadehorarios.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HealthCheckEndpointsIntegrationTestImp extends HealthCheckOfEndpointsIntegrationTest{

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Value("${enrollment.arthur}")
    private String enrollment;

    @Value("${password.arthur}")
    private String password;

    @Test
    @Order(1)
    public void login() throws Exception {
//        Adicionei minhas informações para testes
        super.login(mockMvc, objectMapper, enrollment, password);
    }

    @Test
    @Order(2)
    public void gap() throws Exception {
        super.testWithToken(mockMvc,"/gap");
        super.testWithoutToken(mockMvc,"/gap");
    }
    @Test
    @Order(3)
    public void shift() throws Exception {
        super.testWithToken(mockMvc,"/shift");
        super.testWithoutToken(mockMvc,"/shift");
    }
    @Test
    @Order(4)
    public void weekDay() throws Exception {
        super.testWithToken(mockMvc,"/weekDay");
        super.testWithoutToken(mockMvc,"/weekDay");
    }
    @Test
    @Order(5)
    public void interval() throws Exception {
        super.testWithToken(mockMvc,"/interval");
        super.testWithoutToken(mockMvc,"/interval");
    }
    @Test
    @Order(6)
    public void classBlock() throws Exception {
//        Ajustei o endpoint aqui
        super.testWithToken(mockMvc,"/classBlock");
        super.testWithoutToken(mockMvc,"/classBlock");
    }
    @Test
    @Order(7)
    public void classroom() throws Exception {
        super.testWithToken(mockMvc,"/classroom");
        super.testWithoutToken(mockMvc,"/classroom");
    }
    @Test
    @Order(8)
    public void calendar() throws Exception {
        super.testWithToken(mockMvc,"/calendar");
        super.testWithoutToken(mockMvc,"/calendar");
    }
    @Test
    @Order(9)
    public void curricularComponent() throws Exception {
        super.testWithToken(mockMvc,"/curricularComponent");
        super.testWithoutToken(mockMvc,"/curricularComponent");
    }
    @Test
    @Order(10)
    public void turma() throws Exception {
        super.testWithToken(mockMvc,"/turma");
        super.testWithoutToken(mockMvc,"/turma");
    }

    @Test
    @Order(11)
    public void lesson() throws Exception {
        super.testWithToken(mockMvc,"/lesson");
        super.testWithoutToken(mockMvc,"/lesson");
    }
}
