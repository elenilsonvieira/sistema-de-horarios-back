package br.edu.ifpb.dac.sistemadehorarios.system;

import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTest {

    @Value("${enrollment.arthur}")
    private String enrollment;

    @Value("${password.arthur}")
    private String password;

    static WebDriver navegador;
    @BeforeAll
    public static void setUp(){
        System.setProperty("webdriver.chrome.driver", "src\\drive\\chromedriver.exe");
        navegador = new ChromeDriver();
    }

    @BeforeEach
    void tearDown(){
        navegador = new ChromeDriver();
    }

    @AfterEach
    void tearUp() throws Exception {
        Thread.sleep(1000);
        navegador.quit();
    }

    @AfterAll
    public static void setDown() throws Exception {
        Thread.sleep(10000);
        navegador.quit();
    }

    @Test
    @Order(1)
    public void emptyAllFileds() {
        navegador.get("http://localhost:3000/");
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/span")).click();
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/form/button")).click();
        assertEquals("×\n" +
                "Erro\n" +
                "A matrícula é obrigatória", navegador.findElement(By.xpath("//*[@id=\"toast-container\"]/div")).getText());

        assertEquals("×\n" +
                "Erro\n" +
                "A senha é obrigatória", navegador.findElement(By.xpath("//*[@id=\"toast-container\"]/div[2]")).getText());
    }

    @Test
    @Order(2)
    public void emptyPass() {
        navegador.get("http://localhost:3000/");
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/span")).click();
        navegador.findElement(By.xpath("//*[@id=\"enrollment\"]")).sendKeys("1092381238");
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/form/button")).click();

        assertEquals("×\n" +
                "Erro\n" +
                "A senha é obrigatória", navegador.findElement(By.xpath("//*[@id=\"toast-container\"]/div")).getText());
    }

    @Test
    @Order(3)
    public void emptyEnrollment(){
        navegador.get("http://localhost:3000/");
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/span")).click();
        navegador.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys("1092381238");
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/form/button")).click();
        assertEquals("×\n" +
                "Erro\n" +
                "A matrícula é obrigatória", navegador.findElement(By.xpath("//*[@id=\"toast-container\"]/div")).getText());
    }

    @Test
    @Order(4)
    public void invalidFields() throws Exception{
        navegador.get("http://localhost:3000/");
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/span")).click();
        navegador.findElement(By.xpath("//*[@id=\"enrollment\"]")).sendKeys("1092381238");
        navegador.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys("1092381238");
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/form/button")).click();

        Thread.sleep(3000);

        assertEquals("×\n" +
                "Erro\n" +
                "Verifique os dados e tente novamente.", navegador.findElement(By.xpath("//*[@id=\"toast-container\"]")).getText());
    }

    @Test
    @Order(5)
    public void validFields() throws Exception{
        navegador.get("http://localhost:3000/");
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/span")).click();
        navegador.findElement(By.xpath("//*[@id=\"enrollment\"]")).sendKeys(enrollment);
        navegador.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys(password);
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/form/button")).click();

        Thread.sleep(3000);
        assertEquals("http://localhost:3000/acess-info", navegador.getCurrentUrl());
    }

}
