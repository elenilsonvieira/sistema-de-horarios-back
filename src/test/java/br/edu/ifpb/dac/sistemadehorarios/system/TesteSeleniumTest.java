package br.edu.ifpb.dac.sistemadehorarios.system;
import org.junit.Test;
import org.junit.Before;
import org.hibernate.dialect.Dialect;
import org.hibernate.sql.Select;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class TesteSeleniumTest {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/drive/chromedriver.exe");
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<>();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testeSelenium() {
        driver.get("http://localhost:3000/");
        driver.manage().window().setSize(new Dimension(1552, 832));

        // Assertivas - Personalize conforme necessário
        assertEquals("http://localhost:3000/", driver.getCurrentUrl());
        assertEquals("Sistema de Horários", driver.getTitle());

        driver.findElement(By.cssSelector(".sc-fEOsli")).click();
        driver.findElement(By.id("enrollment")).click();
        driver.findElement(By.id("enrollment")).sendKeys("202115020040");
        driver.findElement(By.id("pass")).click();
        driver.findElement(By.id("pass")).sendKeys("020902@Ecrms");
        driver.findElement(By.cssSelector(".sc-eCYdqJ")).click();

        try {
            Thread.sleep(1000 * 3);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }

        driver.findElement(By.xpath("(//Button)[3]")).click();
        driver.findElement(By.xpath("(//Input)[1]"));
        driver.findElement(By.xpath("(//Input)[1]")).sendKeys("Hicaro Ferreira Brasil");
        driver.findElement(By.id("perfil-s")).click();

        WebElement dropdown = driver.findElement(By.id("perfil-s"));
        dropdown.findElement(By.xpath("//option[. = 'PROFESSOR ENS BASICO TECN TECNOLOGICO - 1']")).click();

        driver.findElement(By.cssSelector(".sc-eCYdqJ")).click();
        driver.findElement(By.cssSelector(".sc-fXynhf")).click();

        try {
            Thread.sleep(1000 * 5);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Adicione mais assertivas conforme necessário
        assertTrue(driver.findElement(By.xpath("//a[text()='Acesso às Informações']")).isDisplayed());

        assertEquals("http://localhost:3000/add-info", driver.getCurrentUrl());

    driver.findElement(By.xpath("//a[text()='Acesso às Informações']")).click();
    driver.findElement(By.xpath("(//Button)[5]")).click();
    {
      WebElement dropdown1  = driver.findElement(By.xpath("(//select)[1]"));
      dropdown1.findElement(By.xpath("//option[. = 'Edição']")).click();
    }
    driver.findElement(By.cssSelector(".sc-gKXOVf:nth-child(38) span")).click();
    driver.findElement(By.cssSelector(".sc-gKXOVf:nth-child(38) img")).click();
    driver.findElement(By.id("a37")).click();
    driver.findElement(By.id("a37")).sendKeys("Hicaro Brasil");
    driver.findElement(By.cssSelector(".sc-iqcoie")).click();
    try {
      Thread.sleep(1000*5);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    {
      WebElement dropdown2 = driver.findElement(By.xpath("(//select)[1]"));
      dropdown2.findElement(By.xpath("//option[. = 'Edição']")).click();
    }
    driver.findElement(By.cssSelector(".sc-gKXOVf:nth-child(38)")).click();
    driver.findElement(By.cssSelector(".sc-gKXOVf:nth-child(38) img")).click();
    driver.findElement(By.cssSelector(".sc-kDDrLX")).click();
    assertThat(driver.switchTo().alert().getText(), is("Deseja confirmar a operação?"));
    driver.switchTo().alert().accept();
  }
}
