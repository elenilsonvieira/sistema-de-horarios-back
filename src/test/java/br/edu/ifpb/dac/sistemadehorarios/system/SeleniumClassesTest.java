package br.edu.ifpb.dac.sistemadehorarios.system;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SeleniumClassesTest {

    static WebDriver browser;

    @Value("${enrollment.arthur}")
    protected String enrollment;

    @Value("${password.arthur}")
    protected String password;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "src\\drive\\chromedriver.exe");
        browser = new ChromeDriver();
    }

    @AfterAll
    static void setDown() {
        browser.quit();
    }

    @AfterEach
    void tearUp() {
        sleep(4000);
    }

    protected void login(){
        browser.get("http://localhost:3000/");

        clickButton(getElementByXpath("//*[@id=\"root\"]/div[2]/div/span"));

        fillInput(getElementByXpath("//*[@id=\"enrollment\"]"), enrollment);
        fillInput(getElementByXpath("//*[@id=\"pass\"]"), password);

        clickButton(getElementByXpath("//*[@id=\"root\"]/div[2]/div/form/button"));

        sleep(3000);
    }

    protected void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    protected void clickButton(WebElement button) {
        button.click();
        sleep(1000);
    }

    protected void fillInput(WebElement input, String text) {
        input.sendKeys(text);
        sleep(1000);
    }

    protected String getMessage() {
        return getElementByClass("toast-message").getText();
    }

    protected WebElement getElementByClass(String text) {
        return browser.findElement(By.className(text));
    }

    protected WebElement getElementByXpath(String element) {
        return browser.findElement(By.xpath(element));
    }
}
