package br.edu.ifpb.dac.sistemadehorarios.system;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EditInfoTest {

    static WebDriver browser;

    @Value("${enrollment.arthur}")
    protected String enrollment;

    @Value("${password.arthur}")
    protected String password;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "src\\drive\\chromedriver.exe");
    }

    @AfterAll
    static void setDown() {browser.quit();}

    @AfterEach
    void tearUp() {
        sleep(5000);
    }

    public void login(){
        browser = new ChromeDriver();
        browser.get("http://localhost:3000/");
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/span")).click();
        browser.findElement(By.xpath("//*[@id=\"enrollment\"]")).sendKeys(enrollment);
        browser.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys(password);
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/form/button")).click();
        try {
            sleep(3000);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Order(1)
    public void viewInfosTeacher(){
        login();
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[2]/div/div[2]/div/button")).click();
        sleep(1000);
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div/label")).click();

        String name = browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div[2]/div[1]/span[2]")).getText();
        String field = browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div[2]/div[2]/span[2]")).getText();

        assertAll("view infos in teacher",
            () -> assertEquals("Whelson Oliveira de Brito", name),
            () -> assertEquals("PROFESSOR ENS BASICO TECN TECNOLOGICO - 1", field)
        );
    }

    @Test
    @Order(2)
    public void viewInfosProfile(){
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div[2]/label")).click();
        sleep(1000);
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div/label")).click();

        String fieldProfile = browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div[2]/div[1]/span[2]")).getText();
        String pattern = browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div[2]/div[2]/span[2]")).getText();

        assertAll("view infos in profile",
                () -> assertEquals("PROFESSOR ENS BASICO TECN TECNOLOGICO", fieldProfile),
                () -> assertEquals("1", pattern)
        );
    }

    @Test
    @Order(3)
    public void viewInfosRestriction(){
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div[3]/label")).click();
        sleep(1000);
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div/label")).click();

        String teacher = browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div[2]/div[1]/span[2]")).getText();
        String dayOfWeek = browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div[2]/div[2]/span[2]")).getText();
        String shift = browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div[2]/div[3]/span[2]")).getText();

        assertAll("view infos in restriction",
                () -> assertEquals("Whelson Oliveira de Brito", teacher),
                () -> assertEquals("segunda-feira", dayOfWeek),
                () -> assertEquals("Manhã", shift)
        );
    }

    @Test
    @Order(4)
    public void viewInfosClassroom(){
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div[4]/label")).click();
        sleep(1000);
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div/label")).click();

        String classroom = browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div[1]/div[2]/div[1]/span[2]")).getText();
        String block = browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div[1]/div[2]/div[2]/span")).getText();
        String capacity = browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div[1]/div[2]/div[3]/span[2]")).getText();

        assertAll("view infos in classroom",
                () -> assertEquals("Lab 3", classroom),
                () -> assertEquals("Bloco 1", block),
                () -> assertEquals("40", capacity)
        );
    }

    @Test
    @Order(5)
    public void viewInfosCurricularComponent(){
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div[5]/label")).click();
        sleep(3000);
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div/label")).click();

        String cComponent = browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div[1]/div[2]/div[1]/span[2]")).getText();
        String ch = browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div[1]/div[2]/div[2]/span[2]")).getText();
        String course = browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div[1]/div[2]/div[3]/span[2]")).getText();

        assertAll("view infos in curricular component",
                () -> assertEquals("Fundamentos de Eletricidade", cComponent),
                () -> assertEquals("67", ch),
                () -> assertEquals("Técnico em Manutenção e Suporte em Informática Subsequente - Monteiro", course)
        );
    }
    @Test
    @Order(6)
    public void viewInfosCalendar(){
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div[6]/label")).click();
        sleep(1000);
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div/label")).click();

        String semester = browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div[2]/div[1]/span[2]")).getText();
        assertEquals("2023.1", semester);
    }

    @Test
    @Order(7)
    public void viewInfosClass(){
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div[7]/label")).click();
        sleep(1000);
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div/label")).click();

        String className = browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div[2]/div[1]/span[2]")).getText();
        String courseClass = browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div[2]/div[2]/span[2]")).getText();

        assertAll("view infos in class",
                () -> assertEquals("1 Periodo", className),
                () -> assertEquals("Técnico em Manutenção e Suporte em Informática Subsequente - Monteiro", courseClass)
        );
    }

    @Test
    @Order(8)
    public void viewInfosCourse(){
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div[8]/label")).click();
        sleep(1000);
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div/label")).click();

        String courseName = browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div[1]/div[2]/div[1]/span[2]")).getText();

        assertEquals("Técnico em Manutenção e Suporte em Informática Subsequente - Monteiro", courseName);
    }

    @Test
    @Order(9)
    public void viewInfosLesson(){
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div[9]/label")).click();
        sleep(1000);
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div/label")).click();

        String calendarLesson = browser.findElement(By.xpath("/html/body/div/div[2]/div[3]/div/div/div[2]/div[1]/span[2]")).getText();
        String classroomLesson = browser.findElement(By.xpath("/html/body/div/div[2]/div[3]/div/div/div[2]/div[2]/span[2]")).getText();
        String curricularCLesson = browser.findElement(By.xpath("/html/body/div/div[2]/div[3]/div/div/div[2]/div[3]/span[2]")).getText();
        String teacherLesson = browser.findElement(By.xpath("/html/body/div/div[2]/div[3]/div/div/div[2]/div[4]/span[2]")).getText();
        String classLesson = browser.findElement(By.xpath("/html/body/div/div[2]/div[3]/div/div/div[2]/div[5]/span[2]")).getText();
        String courseLesson = browser.findElement(By.xpath("/html/body/div/div[2]/div[3]/div/div/div[2]/div[6]/span[2]")).getText();


        assertAll("view infos in classroom",
                () -> assertEquals("2023.1", calendarLesson),
                () -> assertEquals("Lab 3 - Bloco 1", classroomLesson),
                () -> assertEquals("Fundamentos de Eletricidade", curricularCLesson),
                () -> assertEquals("Whelson Oliveira de Brito", teacherLesson),
                () -> assertEquals("Não definida", classLesson),
                () -> assertEquals("Técnico em Manutenção e Suporte em Informática Subsequente - Monteiro", courseLesson)
        );
    }

    // EDIT

    @Test
    @Order(10)
    public void editInfosTeacher(){
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div[1]/label")).click();
        sleep(1000);
        browser.findElement(By.xpath("//*[@id=\"mode\"]")).click();
        browser.findElement(By.xpath("//*[@id=\"mode\"]/option[2]")).click();
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div/label")).click();
        sleep(500);
        browser.findElement(By.xpath("//*[@id=\"a0\"]")).sendKeys("Tiagooo Brasileirooo");
        browser.findElement(By.xpath("/html/body/div/div[2]/div[3]/div/div/div[2]/div[3]/div/button[2]")).click();
        sleep(1000);
        String message = browser.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div[3]")).getText();
        assertEquals("Professor(a) atualizado(a) ao banco.", message);
    }

    @Test
    @Order(11)
    public void editInfosProfile(){
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div[2]/label")).click();
        sleep(1000);
        browser.findElement(By.xpath("//*[@id=\"mode\"]")).click();
        browser.findElement(By.xpath("//*[@id=\"mode\"]/option[2]")).click();
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div/label")).click();

        browser.findElement(By.xpath("//*[@id=\"a0\"]")).sendKeys("Ciência da computação");
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div[2]/div[3]/div/button[2]")).click();
        sleep(1000);

        String message = browser.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div[3]")).getText();
        assertEquals("Profile atualizado ao banco.", message);
    }

    @Test
    @Order(12)
    public void editInfosRestriction(){
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div[3]/label")).click();
        sleep(1000);
        browser.findElement(By.xpath("/html/body/div/div[2]/div[3]/div/div/div[1]/label")).click();
        sleep(500);
        browser.findElement(By.xpath("//*[@id=\"c0\"]")).click();
        browser.findElement(By.xpath("//*[@id=\"c0\"]/option[2]")).click();

        browser.findElement(By.xpath("/html/body/div/div[2]/div[3]/div/div/div[2]/div[4]/div/button[2]")).click();
        sleep(1000);

        String message = browser.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div[3]")).getText();
        assertEquals("Restrição atualizada ao banco.", message);
    }

    @Test
    @Order(13)
    public void editInfosClassroom(){
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div[4]/label")).click();
        sleep(1000);
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div/label")).click();
        sleep(500);
        browser.findElement(By.xpath("//*[@id=\"a0\"]")).sendKeys("Lab 4");
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div[2]/div[4]/div/button[2]")).click();
        sleep(1000);

        String message = browser.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div[3]")).getText();
        assertEquals("Sala de aula atualizada ao banco.", message);
    }

    @Test
    @Order(14)
    public void editInfosCurricularComponent(){
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div[5]/label")).click();
        sleep(1000);
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div/label")).click();
        sleep(500);
        browser.findElement(By.xpath("//*[@id=\"a0\"]")).sendKeys("Fundamentos da Estatística");
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div[1]/div[2]/div[4]/div/button[2]")).click();
        sleep(1000);

        String message = browser.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div[3]")).getText();
        assertEquals("Disciplina atualizada ao banco.", message);
    }
    @Test
    @Order(15)
    public void editInfosCalendar(){
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div[6]/label")).click();
        sleep(1000);
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div/label")).click();
        sleep(500);
        browser.findElement(By.xpath("//*[@id=\"a0\"]")).sendKeys("2023.2");
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div[2]/div[2]/div/button[2]")).click();
        sleep(1000);

        String message = browser.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div[3]")).getText();
        assertEquals("Calendário atualizado ao banco.", message);
    }

    @Test
    @Order(16)
    public void editInfosClass(){
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div[7]/label")).click();
        sleep(1000);
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div/label")).click();
        sleep(500);
        browser.findElement(By.xpath("//*[@id=\"a0\"]")).sendKeys("2 Periodo");
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div[2]/div[3]/div/button[2]")).click();
        sleep(1000);

        String message = browser.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div[3]")).getText();
        assertEquals("Turma ataulizada ao banco.", message);
    }

    @Test
    @Order(17)
    public void editInfosCourse(){
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div[8]/label")).click();
        sleep(1000);
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div/label")).click();
        sleep(500);
        browser.findElement(By.xpath("//*[@id=\"a0\"]")).sendKeys("Técnico em Informática - Monteiro");
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div[1]/div[2]/div[2]/div/button[2]")).click();
        sleep(1000);

        String message = browser.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div[3]")).getText();
        assertEquals("Curso atualizado ao banco.", message);
    }

    @Test
    @Order(18)
    public void editInfosLesson(){
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div[9]/label")).click();
        sleep(1000);
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div/label")).click();
        sleep(500);
        browser.findElement(By.xpath("//*[@id=\"c0\"]")).click();
        browser.findElement(By.xpath("//*[@id=\"c0\"]/option[3]")).click();
        browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div[2]/div[7]/div/button[2]")).click();

        sleep(1000);

        String message = browser.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div[3]")).getText();
        assertEquals("Aula atualizada no banco.", message);
    }

}
