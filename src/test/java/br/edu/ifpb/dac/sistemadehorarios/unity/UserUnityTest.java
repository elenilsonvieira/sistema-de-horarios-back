package br.edu.ifpb.dac.sistemadehorarios.unity;

import br.edu.ifpb.dac.sistemadehorarios.entity.User.UserModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.User.UserService;
import br.edu.ifpb.dac.sistemadehorarios.exception.UserInvalidException;
import jdk.jfr.Description;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserUnityTest {

    @Autowired
    private UserService userService;

    @Test
    @Order(1)
    @Description("Shoud be create and delete user")
    public void create() {
        try {
            UserModel userModel = new UserModel();
            userModel.setName("Fulano");
            userModel.setEmail("f772ac7e-3987-49cb-850a-217c764dbced");
            userModel.setPass("123456");
            UserModel userResult = this.userService.create(userModel);
            assertNotEquals("123456",userResult.getPass());

            boolean resultDelete = this.userService.delete(userModel.getUuid());

            assertTrue(resultDelete);

        } catch (UserInvalidException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(2)
    @Description("Sould be create, do login, incorrect login and delete user")
    public void login(){
        try {
            String email = "f772ac7e-3987-49cb-850a-217c764dbced";
            UserModel userModel = new UserModel();
            userModel.setName("Fulano");
            userModel.setEmail(email);
            userModel.setPass("123456");
            this.userService.create(userModel);
            UserModel userDTO = this.userService.login(email, "123456");
            assertNotNull(userDTO);

            userDTO = this.userService.login(email, "1234567");
            assertNull(userDTO);
            userDTO = this.userService.login(email+"email", "123456");
            assertNull(userDTO);

            boolean resultDelete = this.userService.delete(userModel.getUuid());

            assertTrue(resultDelete);
        } catch (UserInvalidException e) {
            e.printStackTrace();
        }
    }
    @Test
    @Order(3)
    @Description("Sould be throw error")
    public void equalEmail(){
        try {
            UserModel userModel = new UserModel();
            userModel.setName("Fulano");
            userModel.setEmail("f772ac7e-3987-49cb-850a-217c764dbced");
            userModel.setPass("123456");

            UserModel userModel2 = new UserModel();
            userModel2.setName("Fulano");
            userModel2.setEmail("f772ac7e-3987-49cb-850a-217c764dbced");
            userModel2.setPass("123456");
            this.userService.create(userModel);
            assertThrows(Exception.class, () ->{
                this.userService.create(userModel2);
            });
            boolean resultDelete = this.userService.delete(userModel.getUuid());
            assertTrue(resultDelete);
        } catch (UserInvalidException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(4)
    @Description("Should be diferent of zero")
    public void find(){
        try {
            UserModel userModel = new UserModel();
            userModel.setName("Fulano");
            userModel.setEmail("f772ac7e-3987-49cb-850a-217c764dbced");
            userModel.setPass("123456");
            this.userService.create(userModel);

            UserModel userModel1 = this.userService.findByEmail(userModel.getEmail());

            assertNotNull(userModel1);

            boolean resultDelete = this.userService.delete(userModel.getUuid());
            assertTrue(resultDelete);
        } catch (UserInvalidException e) {
            e.printStackTrace();
        }
    }
}