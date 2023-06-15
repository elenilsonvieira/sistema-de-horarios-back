package br.edu.ifpb.dac.sistemadehorarios.system;

import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTest {

    @Value("${enrollment.arthur}")
    private String enrollment;

    @Value("${password.arthur}")
    private String password;

    private static WebDriver driver;

    @BeforeAll
    public static void setUp(){
        driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "src/drive/chromedriver.exe");

        //Se a página não responder em 10 segundos, lance exceção
        new ChromeDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterEach
    void beforeEach() throws InterruptedException {
        Thread.sleep(1000);
    }

    @AfterAll
    public static void tearDown() throws Exception {
        Thread.sleep(1000);
        driver.quit();
    }

    @Test
    @DisplayName("Testar se os campos estão vazios")
    @Order(1)
    public void emptyAllFields() {
        driver.get("http://localhost:3000/");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/span")).click();
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/form/button")).click();
        assertEquals("×\n" +
                "Erro\n" +
                "A matrícula é obrigatória", driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div")).getText());

        assertEquals("×\n" +
                "Erro\n" +
                "A senha é obrigatória", driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div[2]")).getText());
    }

    private void fillInTheFields(String enrollment, String password) {
        WebElement element;

    }

    @Test
    @Order(2)
    public void emptyPass() {
        driver.get("http://localhost:3000/");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/span")).click();
        driver.findElement(By.xpath("//*[@id=\"enrollment\"]")).sendKeys("1092381238");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/form/button")).click();

        assertEquals("×\n" +
                "Erro\n" +
                "A senha é obrigatória", driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div")).getText());
    }

    @Test
    @Order(3)
    public void emptyEnrollment(){
        driver.get("http://localhost:3000/");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/span")).click();
        driver.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys("1092381238");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/form/button")).click();
        assertEquals("×\n" +
                "Erro\n" +
                "A matrícula é obrigatória", driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div")).getText());
    }

    @Test
    @Order(4)
    public void invalidFields() throws Exception{
        driver.get("http://localhost:3000/");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/span")).click();
        driver.findElement(By.xpath("//*[@id=\"enrollment\"]")).sendKeys("1092381238");
        driver.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys("1092381238");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/form/button")).click();

        Thread.sleep(3000);

        assertEquals("×\n" +
                "Erro\n" +
                "Verifique os dados e tente novamente.", driver.findElement(By.xpath("//*[@id=\"toast-container\"]")).getText());
    }

    @Test
    @Order(5)
    public void validFields() throws Exception{
        driver.get("http://localhost:3000/");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/span")).click();
        driver.findElement(By.xpath("//*[@id=\"enrollment\"]")).sendKeys(enrollment);
        driver.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys(password);
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/form/button")).click();

        Thread.sleep(3000);
        assertEquals("http://localhost:3000/acess-info", driver.getCurrentUrl());
    }

}
