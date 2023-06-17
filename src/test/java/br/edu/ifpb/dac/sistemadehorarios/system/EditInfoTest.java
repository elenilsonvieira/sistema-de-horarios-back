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

    static WebDriver navegador;

    @Value("${enrollment.arthur}")
    private String enrollment;

    @Value("${password.arthur}")
    private String password;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "src\\drive\\chromedriver.exe");
    }

    @AfterAll
    static void setDown() {navegador.quit();}

    @AfterEach
    void tearUp() {
        sleep(5000);
    }

    public void login(){
        navegador = new ChromeDriver();
        navegador.get("http://localhost:3000/");
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/span")).click();
        navegador.findElement(By.xpath("//*[@id=\"enrollment\"]")).sendKeys(enrollment);
        navegador.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys(password);
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/form/button")).click();
        try {
            Thread.sleep(3000);
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
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[2]/div/div[2]/div/button")).click();
        sleep(1000);
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div/label")).click();

        String name = navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div[2]/div[1]/span[2]")).getText();
        String field = navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div[2]/div[2]/span[2]")).getText();

        assertAll("view infos in teacher",
            () -> assertEquals("Tiago Brasileiro", name),
            () -> assertEquals("Ciencia de dados - 1", field)
        );
    }

    @Test
    @Order(2)
    public void viewInfosProfile(){
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div[2]/label")).click();
        sleep(1000);
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div/label")).click();

        String fieldProfile = navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div[2]/div[1]/span[2]")).getText();
        String pattern = navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div[2]/div[2]/span[2]")).getText();

        assertAll("view infos in profile",
                () -> assertEquals("Ciencia de dados", fieldProfile),
                () -> assertEquals("1", pattern)
        );
    }

    @Test
    @Order(3)
    public void viewInfosRestriction(){
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div[3]/label")).click();
        sleep(1000);
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div/label")).click();

        String teacher = navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div[2]/div[1]/span[2]")).getText();
        String dayOfWeek = navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div[2]/div[2]/span[2]")).getText();
        String shift = navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div[2]/div[3]/span[2]")).getText();

        assertAll("view infos in restriction",
                () -> assertEquals("Tiago Brasileiro", teacher),
                () -> assertEquals("segunda-feira", dayOfWeek),
                () -> assertEquals("Manhã", shift)
        );
    }

    @Test
    @Order(4)
    public void viewInfosClassroom(){
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div[4]/label")).click();
        sleep(1000);
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div/label")).click();

        String classroom = navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div[1]/div[2]/div[1]/span[2]")).getText();
        String block = navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div[1]/div[2]/div[2]/span")).getText();
        String capacity = navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div[1]/div[2]/div[3]/span[2]")).getText();

        assertAll("view infos in classroom",
                () -> assertEquals("Lab 3", classroom),
                () -> assertEquals("Bloco 1", block),
                () -> assertEquals("40", capacity)
        );
    }

    @Test
    @Order(5)
    public void viewInfosCurricularComponent(){
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div[5]/label")).click();
        sleep(3000);
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div/label")).click();

        String cComponent = navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div[1]/div[2]/div[1]/span[2]")).getText();
        String ch = navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div[1]/div[2]/div[2]/span[2]")).getText();
        String course = navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div[1]/div[2]/div[3]/span[2]")).getText();

        assertAll("view infos in curricular component",
                () -> assertEquals("Fundamentos de Eletricidade", cComponent),
                () -> assertEquals("67", ch),
                () -> assertEquals("Técnico em Manutenção e Suporte em Informática Subsequente - Monteiro", course)
        );
    }
    @Test
    @Order(6)
    public void viewInfosCalendar(){
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div[6]/label")).click();
        sleep(1000);
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div/label")).click();

        String semester = navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div[2]/div[1]/span[2]")).getText();
        assertEquals("2023.1", semester);
    }

    @Test
    @Order(7)
    public void viewInfosClass(){
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div[7]/label")).click();
        sleep(1000);
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div/label")).click();

        String className = navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div[2]/div[1]/span[2]")).getText();
        String courseClass = navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div[2]/div[2]/span[2]")).getText();

        assertAll("view infos in class",
                () -> assertEquals("1 Periodo", className),
                () -> assertEquals("Tecnologia em Análise e Desenvolvimento de Sistemas (2021/1) - Monteiro", courseClass)
        );
    }

    @Test
    @Order(8)
    public void viewInfosCourse(){
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div[8]/label")).click();
        sleep(1000);
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div/label")).click();

        String courseName = navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div[1]/div[2]/div[1]/span[2]")).getText();

        assertEquals("Técnico em Manutenção e Suporte em Informática Subsequente - Monteiro", courseName);
    }

    @Test
    @Order(9)
    public void viewInfosLesson(){
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div[9]/label")).click();
        sleep(1000);
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div/label")).click();

        String calendarLesson = navegador.findElement(By.xpath("/html/body/div/div[2]/div[3]/div/div/div[2]/div[1]/span[2]")).getText();
        String classroomLesson = navegador.findElement(By.xpath("/html/body/div/div[2]/div[3]/div/div/div[2]/div[2]/span[2]")).getText();
        String curricularCLesson = navegador.findElement(By.xpath("/html/body/div/div[2]/div[3]/div/div/div[2]/div[3]/span[2]")).getText();
        String teacherLesson = navegador.findElement(By.xpath("/html/body/div/div[2]/div[3]/div/div/div[2]/div[4]/span[2]")).getText();
        String classLesson = navegador.findElement(By.xpath("/html/body/div/div[2]/div[3]/div/div/div[2]/div[5]/span[2]")).getText();
        String courseLesson = navegador.findElement(By.xpath("/html/body/div/div[2]/div[3]/div/div/div[2]/div[6]/span[2]")).getText();


        assertAll("view infos in classroom",
                () -> assertEquals("2023.1", calendarLesson),
                () -> assertEquals("Lab 3 - Bloco 1", classroomLesson),
                () -> assertEquals("Fundamentos de Eletricidade", curricularCLesson),
                () -> assertEquals("Tiago Brasileiro", teacherLesson),
                () -> assertEquals("Não definida", classLesson),
                () -> assertEquals("Tecnologia em Análise e Desenvolvimento de Sistemas (2021/1) - Monteiro", courseLesson)
        );
    }

    // EDIT

    @Test
    @Order(10)
    public void editInfosTeacher(){
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div[1]/label")).click();
        sleep(1000);
        navegador.findElement(By.xpath("//*[@id=\"mode\"]")).click();
        navegador.findElement(By.xpath("//*[@id=\"mode\"]/option[2]")).click();
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div/label")).click();
        sleep(500);
        navegador.findElement(By.xpath("//*[@id=\"a0\"]")).sendKeys("Tiagooo Brasileirooo");
        navegador.findElement(By.xpath("/html/body/div/div[2]/div[3]/div/div/div[2]/div[3]/div/button[2]")).click();
        sleep(1000);
        String message = navegador.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div[3]")).getText();
        assertEquals("Professor(a) atualizado(a) ao banco.", message);
    }

    @Test
    @Order(11)
    public void editInfosProfile(){
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div[2]/label")).click();
        sleep(1000);
        navegador.findElement(By.xpath("//*[@id=\"mode\"]")).click();
        navegador.findElement(By.xpath("//*[@id=\"mode\"]/option[2]")).click();
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div/label")).click();

        navegador.findElement(By.xpath("//*[@id=\"a0\"]")).sendKeys("Ciência da computação");
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div[2]/div[3]/div/button[2]")).click();
        sleep(1000);

        String message = navegador.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div[3]")).getText();
        assertEquals("Profile atualizado ao banco.", message);
    }

    @Test
    @Order(12)
    public void editInfosRestriction(){
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div[3]/label")).click();
        sleep(1000);
        navegador.findElement(By.xpath("/html/body/div/div[2]/div[3]/div/div/div[1]/label")).click();
        sleep(500);
        navegador.findElement(By.xpath("//*[@id=\"c0\"]")).click();
        navegador.findElement(By.xpath("//*[@id=\"c0\"]/option[2]")).click();

        navegador.findElement(By.xpath("/html/body/div/div[2]/div[3]/div/div/div[2]/div[4]/div/button[2]")).click();
        sleep(1000);

        String message = navegador.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div[3]")).getText();
        assertEquals("Restrição atualizada ao banco.", message);
    }

    @Test
    @Order(13)
    public void editInfosClassroom(){
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div[4]/label")).click();
        sleep(1000);
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div/label")).click();
        sleep(500);
        navegador.findElement(By.xpath("//*[@id=\"a0\"]")).sendKeys("Lab 4");
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div[2]/div[4]/div/button[2]")).click();
        sleep(1000);

        String message = navegador.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div[3]")).getText();
        assertEquals("Sala de aula atualizada ao banco.", message);
    }

    @Test
    @Order(14)
    public void editInfosCurricularComponent(){
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div[5]/label")).click();
        sleep(1000);
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div/label")).click();
        sleep(500);
        navegador.findElement(By.xpath("//*[@id=\"a0\"]")).sendKeys("Fundamentos da Estatística");
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div[1]/div[2]/div[4]/div/button[2]")).click();
        sleep(1000);

        String message = navegador.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div[3]")).getText();
        assertEquals("Disciplina atualizada ao banco.", message);
    }
    @Test
    @Order(15)
    public void editInfosCalendar(){
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div[6]/label")).click();
        sleep(1000);
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div/label")).click();
        sleep(500);
        navegador.findElement(By.xpath("//*[@id=\"a0\"]")).sendKeys("2023.2");
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div[2]/div[2]/div/button[2]")).click();
        sleep(1000);

        String message = navegador.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div[3]")).getText();
        assertEquals("Calendário atualizado ao banco.", message);
    }

    @Test
    @Order(16)
    public void editInfosClass(){
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div[7]/label")).click();
        sleep(1000);
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div/label")).click();
        sleep(500);
        navegador.findElement(By.xpath("//*[@id=\"a0\"]")).sendKeys("2 Periodo");
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div[2]/div[3]/div/button[2]")).click();
        sleep(1000);

        String message = navegador.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div[3]")).getText();
        assertEquals("Turma ataulizada ao banco.", message);
    }

    @Test
    @Order(17)
    public void editInfosCourse(){
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div[8]/label")).click();
        sleep(1000);
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div/label")).click();
        sleep(500);
        navegador.findElement(By.xpath("//*[@id=\"a0\"]")).sendKeys("Técnico em Informática - Monteiro");
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div[1]/div[2]/div[2]/div/button[2]")).click();
        sleep(1000);

        String message = navegador.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div[3]")).getText();
        assertEquals("Curso atualizado ao banco.", message);
    }

    @Test
    @Order(18)
    public void editInfosLesson(){
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div[9]/label")).click();
        sleep(1000);
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div/label")).click();
        sleep(500);
        navegador.findElement(By.xpath("//*[@id=\"c0\"]")).click();
        navegador.findElement(By.xpath("//*[@id=\"c0\"]/option[3]")).click();
        navegador.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div[2]/div[7]/div/button[2]")).click();

        sleep(1000);

        String message = navegador.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div[3]")).getText();
        assertEquals("Aula atualizada no banco.", message);
    }

}
