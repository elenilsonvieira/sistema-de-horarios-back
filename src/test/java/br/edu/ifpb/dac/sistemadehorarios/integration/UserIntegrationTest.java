package br.edu.ifpb.dac.sistemadehorarios.integration;

import br.edu.ifpb.dac.sistemadehorarios.DTO.LoginDTO;
import br.edu.ifpb.dac.sistemadehorarios.DTO.UserDTO;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Gap.GapService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Interval.IntervalDRO;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Interval.IntervalModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Interval.IntervalRepository;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Interval.IntervalService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Shift.ShiftService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.WeekDay.WeekDayService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Lesson.LessonModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.User.RoleEnum;
import br.edu.ifpb.dac.sistemadehorarios.entity.User.UserModel;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.edu.ifpb.dac.sistemadehorarios.exception.interval.IntervalInvalidException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

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

    @SpringBootTest
    @AutoConfigureMockMvc
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    public static class IntervalServiceIntegrationTest {

        @MockBean
        private IntervalRepository repository;

        @MockBean
        private IntervalModel intervalMock;

        @MockBean
        private IntervalDRO intervalDROMock;

        @MockBean
        private GapService gapService;

        @MockBean
        private ShiftService shiftService;

        @MockBean
        private WeekDayService weekDayService;

        @MockBean
        private LessonModel lessonMock;

        @Autowired
        private IntervalService intervalService;

        @Test
        @Order(1)
        @Tag("creat method")
        @DisplayName("Creat from intervalService")
        void createNewInterval() {
            try {
                IntervalModel intervalModelTeste = intervalService.create(intervalDROMock);
                assertNotEquals(intervalModelTeste, null);

            } catch (IntervalInvalidException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        @Test
        @Order(2)
        @Tag("read method")
        @DisplayName("List IntervalModel from intervalService")
        void readInterval() {
            try {
                List<IntervalModel> listIntervalModelTeste = intervalService.read();
                listIntervalModelTeste.add(intervalMock);
                assertEquals(listIntervalModelTeste.size(), 1);

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        @Test
        @Order(3)
        @Tag("findByUuid method")
        @DisplayName("Return IntervalModel from intervalService")
        void findByUuidInterval() {
            try {
                String id = "id-mock";
                IntervalModel intervalModelTeste = intervalService.findByUuid(id);
                assertNotEquals(intervalModelTeste, null);

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        @Test
        @Order(4)
        @Tag("delete method")
        @DisplayName("Delete IntervalModel from intervalService")
        void deleteRestriction() {
            try {
                String id = "id-mock";
                boolean check = intervalService.delete(id);
                assertTrue(check);

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}
