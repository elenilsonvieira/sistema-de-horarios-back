package br.edu.ifpb.dac.sistemadehorarios.system;

import br.edu.ifpb.dac.sistemadehorarios.SistemaDeHorariosApplication;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTest {

    @Value("${enrollment.arthur}")
    protected String enrollment;

    @Value("${password.arthur}")
    protected String password;

    static WebDriver browser;
    @BeforeAll
    public static void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "src\\drive\\chromedriver.exe");
    }

    @BeforeEach
    void tearDown(){
        browser = new ChromeDriver();
    }

    @AfterEach
    void tearUp() throws Exception {
        Thread.sleep(1000);
        //browser.quit();
    }

    @AfterAll
    public static void setDown() throws Exception {
        Thread.sleep(10000);
        browser.close();
    }

    @Test
    @Order(1)
    public void emptyAllFileds() {
        browser.get("http://localhost:3000/");
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/span")).click();
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/form/button")).click();
        assertEquals("×\n" +
                "Erro\n" +
                "A matrícula é obrigatória", browser.findElement(By.xpath("//*[@id=\"toast-container\"]/div")).getText());

        assertEquals("×\n" +
                "Erro\n" +
                "A senha é obrigatória", browser.findElement(By.xpath("//*[@id=\"toast-container\"]/div[2]")).getText());
    }

    @Test
    @Order(2)
    public void emptyPass() {
        browser.get("http://localhost:3000/");
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/span")).click();
        browser.findElement(By.xpath("//*[@id=\"enrollment\"]")).sendKeys("1092381238");
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/form/button")).click();

        assertEquals("×\n" +
                "Erro\n" +
                "A senha é obrigatória", browser.findElement(By.xpath("//*[@id=\"toast-container\"]/div")).getText());
    }

    @Test
    @Order(3)
    public void emptyEnrollment(){
        browser.get("http://localhost:3000/");
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/span")).click();
        browser.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys("1092381238");
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/form/button")).click();
        assertEquals("×\n" +
                "Erro\n" +
                "A matrícula é obrigatória", browser.findElement(By.xpath("//*[@id=\"toast-container\"]/div")).getText());
    }

    @Test
    @Order(4)
    public void invalidFields() throws Exception{
        browser.get("http://localhost:3000/");
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/span")).click();
        browser.findElement(By.xpath("//*[@id=\"enrollment\"]")).sendKeys("1092381238");
        browser.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys("1092381238");
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/form/button")).click();

        Thread.sleep(3000);

        assertEquals("×\n" +
                "Erro\n" +
                "Verifique os dados e tente novamente.", browser.findElement(By.xpath("//*[@id=\"toast-container\"]")).getText());
    }

    @Test
    @Order(5)
    public void validFields() throws Exception{
        browser.get("http://localhost:3000/");
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/span")).click();
        browser.findElement(By.xpath("//*[@id=\"enrollment\"]")).sendKeys(enrollment);
        browser.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys(password);
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/form/button")).click();

        Thread.sleep(3000);
        assertEquals("http://localhost:3000/access-info", browser.getCurrentUrl());
    }


}
