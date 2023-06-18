package br.edu.ifpb.dac.sistemadehorarios.system;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

public class CreateInfoTest extends SeleniumClassesTest {

    @Test
    @Order(1)
    public void addInvalidProfessorProfile(){
        login();
        clickButton(getElementByXpath("//*[@id=\"root\"]/div[2]/div/div[2]/div/div[1]/div[1]/button"));

        clickButton(getElementByXpath("//*[@id=\"root\"]/div[2]/div[1]/div[2]/div/div[2]/label/span"));
        clickButton(getElementByXpath("//*[@id=\"root\"]/div[2]/div[1]/div[1]/div[2]/button"));
        assertNotEquals("Profile adicionado ao banco.", getMessage());
    }

    @Test
    @Order(2)
    public void addProfessorProfile(){
        WebElement input = getElementByXpath("/html/body/div/div[2]/div[1]/div[1]/div[2]/form/div[1]/input");
        fillInput(input, "Ciencia de dados");

        input = getElementByXpath("/html/body/div/div[2]/div[1]/div[1]/div[2]/form/div[2]/input");
        fillInput(input, "1");

        clickButton(getElementByXpath("//*[@id=\"root\"]/div[2]/div[1]/div[1]/div[2]/button"));

        assertEquals("Profile adicionado ao banco.", getMessage());
    }

    @Test
    @Order(3)
    public void addInvalidProfessor(){
        clickButton(getElementByXpath("/html/body/div/div[2]/div[1]/div[2]/div/div[1]/label/span"));
        clickButton(getElementByXpath("//*[@id=\"root\"]/div[2]/div[1]/div[1]/div[2]/button"));
        assertNotEquals("Professor(a) adicionado(a) ao banco.", getMessage());
    }

    @Test
    @Order(4)
    public void addProfessor(){
        WebElement input = getElementByXpath("/html/body/div/div[2]/div[1]/div[1]/div[2]/form/div[1]/input");
        fillInput(input, "Tiago Brasileiro");

        clickButton(getElementByXpath("//*[@id=\"root\"]/div[2]/div[1]/div[1]/div[2]/button"));

        assertEquals("Professor(a) adicionado(a) ao banco.", getMessage());
    }

    @Test
    @Order(5)
    public void addRestriction(){
        clickButton(getElementByXpath("/html/body/div/div[2]/div[1]/div[2]/div/div[3]/label/span"));
        clickButton(getElementByXpath("/html/body/div/div[2]/div[1]/div[1]/div[2]/button"));

        assertEquals("Restrição adicionada ao banco.", getMessage());
    }

    @Test
    @Order(6)
    public void addInvalidCalendar(){
        clickButton(getElementByXpath("//*[@id=\"root\"]/div[2]/div[1]/div[2]/div/div[5]/label/span"));
        clickButton(getElementByXpath("//*[@id=\"root\"]/div[2]/div[1]/div[1]/div[2]/button"));
        assertNotEquals("Calendário adicionado ao banco.", getMessage());
    }

    @Test
    @Order(7)
    public void addCalendar(){
        clickButton(getElementByXpath("//*[@id=\"root\"]/div[2]/div[1]/div[2]/div/div[5]/label/span"));

        WebElement input = getElementByXpath("//*[@id=\"semestre\"]");
        fillInput(input, "2023.1");

        clickButton(getElementByXpath("//*[@id=\"root\"]/div[2]/div[1]/div[1]/div[2]/button"));

        assertEquals("Calendário adicionado ao banco.", getMessage());
    }

    @Test
    @Order(8)
    public void addInvalidCourse(){
        clickButton(getElementByXpath("//*[@id=\"root\"]/div[2]/div[1]/div[2]/div/div[7]/label/span"));
        clickButton(getElementByXpath("//*[@id=\"root\"]/div[2]/div[1]/div[1]/div[2]/button"));
        assertNotEquals("Curso adicionado ao banco.", getMessage());
    }

    @Test
    @Order(9)
    public void addCourse(){
        clickButton(getElementByXpath("//*[@id=\"root\"]/div[2]/div[1]/div[2]/div/div[7]/label/span"));

        WebElement input = getElementByXpath("//*[@id=\"nome\"]");
        fillInput(input, "Analise e Desenvolvimento de Sistemas");

        clickButton(getElementByXpath("//*[@id=\"root\"]/div[2]/div[1]/div[1]/div[2]/button"));

        assertEquals("Curso adicionado ao banco.", getMessage());
    }

    @Test
    @Order(10)
    public void addInvalidTurma(){
        clickButton(getElementByXpath("//*[@id=\"root\"]/div[2]/div[1]/div[2]/div/div[6]/label/span"));
        clickButton(getElementByXpath("//*[@id=\"root\"]/div[2]/div[1]/div[1]/div[2]/button"));
        assertNotEquals("Turma adicionada ao banco.", getMessage());
    }

    @Test
    @Order(11)
    public void addTurma(){
        clickButton(getElementByXpath("//*[@id=\"root\"]/div[2]/div[1]/div[2]/div/div[6]/label/span"));

        WebElement input = getElementByXpath("//*[@id=\"turma\"]");
        fillInput(input, "1 Periodo");

        clickButton(getElementByXpath("//*[@id=\"root\"]/div[2]/div[1]/div[1]/div[2]/button"));

        assertEquals("Turma adicionada ao banco.", getMessage());
    }

    @Test
    @Order(12)
    public void addInvalidClassroom(){
        clickButton(getElementByXpath("//*[@id=\"root\"]/div[2]/div[1]/div[2]/div/div[8]/label/span"));
        clickButton(getElementByXpath("//*[@id=\"root\"]/div[2]/div[1]/div[1]/div[2]/button"));
        assertNotEquals("Sala de aula adicionada ao banco.", getMessage());
    }

    @Test
    @Order(13)
    public void addClassroom(){
        clickButton(getElementByXpath("//*[@id=\"root\"]/div[2]/div[1]/div[2]/div/div[8]/label/span"));

        WebElement input = getElementByXpath("//*[@id=\"nome\"]");
        fillInput(input, "2023.1");

        input = getElementByXpath("//*[@id=\"capacidade\"]");
        fillInput(input, "40");

        clickButton(getElementByXpath("//*[@id=\"root\"]/div[2]/div[1]/div[1]/div[2]/button"));

        assertEquals("Sala de aula adicionada ao banco.", getMessage());
    }

    @Test
    @Order(14)
    public void addInvalidCurricularComponent(){
        clickButton(getElementByXpath("//*[@id=\"root\"]/div[2]/div[1]/div[2]/div/div[4]/label/span"));
        clickButton(getElementByXpath("//*[@id=\"root\"]/div[2]/div[1]/div[1]/div[2]/button"));
        assertNotEquals("Disciplina adicionada ao banco.", getMessage());
    }

    @Test
    @Order(15)
    public void addCurricularComponent(){
        clickButton(getElementByXpath("//*[@id=\"root\"]/div[2]/div[1]/div[2]/div/div[4]/label/span"));

        WebElement input = getElementByXpath("//*[@id=\"nome\"]");
        fillInput(input, "Testes");

        input = getElementByXpath("//*[@id=\"carga-horária\"]");
        fillInput(input, "33");

        clickButton(getElementByXpath("//*[@id=\"root\"]/div[2]/div[1]/div[1]/div[2]/button"));

        assertEquals("Disciplina adicionada ao banco.", getMessage());
    }

    @Test
    @Order(16)
    public void addLesson(){
        clickButton(getElementByXpath("//*[@id=\"root\"]/div[2]/div[1]/div[2]/div/div[9]/label/span"));
        clickButton(getElementByXpath("//*[@id=\"root\"]/div[2]/div[1]/div[1]/div[2]/button"));
        assertEquals("Aula adicionada ao banco.", getMessage());
    }

}
