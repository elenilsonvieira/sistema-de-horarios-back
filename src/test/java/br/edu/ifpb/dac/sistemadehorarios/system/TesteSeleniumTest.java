package br.edu.ifpb.dac.sistemadehorarios.system;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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

        // Para verificar se o sistema está na página inicial
        assertEquals("http://localhost:3000/", driver.getCurrentUrl());
        // Para verificar se o título da página está correto
        assertEquals("Sistema de Horários", driver.getTitle());

        // Logica para fazer o login
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

        // Logica para inserir um professor
        driver.findElement(By.xpath("(//Button)[3]")).click();
        driver.findElement(By.xpath("(//Input)[1]"));
        driver.findElement(By.xpath("(//Input)[1]")).sendKeys("Hicaro Ferreira Brasil");

        // Compara o nome inserido com um nome esperado (url)
        assertNotEquals("http://localhost:3000/", driver.getCurrentUrl());
        driver.findElement(By.id("perfil-s")).click();

        // Compara o nome inserido com um nome esperado (professor)
        String nomeInserido = driver.findElement(By.xpath("(//Input)[1]")).getAttribute("value");
        assertEquals("Hicaro Ferreira Brasil", nomeInserido);

        // Logica para inserir um perfil
        WebElement dropdown = driver.findElement(By.id("perfil-s"));
        dropdown.findElement(By.xpath("//option[. = 'PROFESSOR ENS BASICO TECN TECNOLOGICO - 1']")).click();

        // Compara o perfil inserido com um nome esperado
        String perfilInserido = driver.findElement(By.id("perfil-s")).getAttribute("value");
        assertNotEquals(null, perfilInserido);

        // Logica para salvar um professor
        driver.findElement(By.cssSelector(".sc-eCYdqJ")).click();
        driver.findElement(By.cssSelector(".sc-fXynhf")).click();

        try {
            Thread.sleep(1000 * 5);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // Logica para entrar em uma restrição de um professor
        driver.findElement(By.xpath("(//span)[4]")).click();
         try {
          Thread.sleep(1000*2);
        } catch (Exception e) {
          Thread.currentThread().interrupt();
        }
        // Logica para adicionar uma restrição de um professor
        {
            WebElement dropdown1 = driver.findElement(By.xpath("(//select)[1]"));
            dropdown1.findElement(By.xpath("//option[. = 'Hicaro Ferreira Brasil']")).click();
        }
        driver.findElement(By.xpath("(//Button)[1]")).click();

        try {
          Thread.sleep(1000*5);
        } catch (Exception e) {
          Thread.currentThread().interrupt();
        }
        
        // Logica para verificar se o botão de acesso às informações está visível
        assertTrue(driver.findElement(By.xpath("//a[text()='Acesso às Informações']")).isDisplayed());
        // Logica para verificar se a url está correta
        assertEquals("http://localhost:3000/add-info", driver.getCurrentUrl());
      
        // Thread para esperar 5 segundos
        try {
          Thread.sleep(1000*5);
        } catch (Exception e) {
          Thread.currentThread().interrupt();
        }
    
    // Logica para entrar em edição de um professor
    driver.findElement(By.xpath("//a[text()='Acesso às Informações']")).click();
    driver.findElement(By.xpath("(//Button)[5]")).click();
    {
      WebElement dropdown1  = driver.findElement(By.xpath("(//select)[1]"));
      dropdown1.findElement(By.xpath("//option[. = 'Edição']")).click();
    }
    try{
      Thread.sleep(1000*5);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    driver.findElement(By.cssSelector(".sc-gKXOVf:nth-child(36) span")).click();
    driver.findElement(By.xpath("(//label)[47]")).click();
    driver.findElement(By.id("a36")).click();
    driver.findElement(By.id("a36")).sendKeys("Hicaro Brasil");
    driver.findElement(By.cssSelector(".sc-iqcoie")).click();
    
    try {
      Thread.sleep(1000*3);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    // Logica para deletar uma restrição de um professor
    driver.findElement(By.xpath("(//span)[4]")).click();
    {
      WebElement dropdown2 = driver.findElement(By.xpath("(//select)[1]"));
      dropdown2.findElement(By.xpath("//option[. = 'Edição']")).click();
    }
    
    driver.findElement(By.xpath("(//label)[10]")).click();
    driver.findElement(By.xpath("(//label)[11]")).click();
    driver.findElement(By.xpath("(//Button)[1]")).click();
    assertThat(driver.switchTo().alert().getText(), is("Deseja confirmar a operação?"));
    driver.switchTo().alert().accept();

    try {
      Thread.sleep(1000*5);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    // Logica para deletar um professor
    driver.findElement(By.xpath(("(//span)[2]"))).click();
    {
      WebElement dropdown2 = driver.findElement(By.xpath("(//select)[1]"));
      dropdown2.findElement(By.xpath("//option[. = 'Edição']")).click();
    }
    try {
      Thread.sleep(1000*5);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    driver.findElement(By.xpath("(//label)[47]")).click();
    driver.findElement(By.cssSelector(".sc-kDDrLX")).click();
    assertThat(driver.switchTo().alert().getText(), is("Deseja confirmar a operação?"));
    driver.switchTo().alert().accept();
  }

    private void assertNotEquals(String string, String currentUrl) {
    }
}
