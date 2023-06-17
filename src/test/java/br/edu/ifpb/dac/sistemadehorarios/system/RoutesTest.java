package br.edu.ifpb.dac.sistemadehorarios.system;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class RoutesTest extends SeleniumClassesTest {

    @Test
    @Order(1)
    public void viewUrl() {
        changeUrl("http://localhost:3000/");
        clickButton(getElementByXpath("//*[@id=\"root\"]/div[2]/div/button"));
        assertEquals("http://localhost:3000/view", browser.getCurrentUrl());
    }

    @Test
    @Order(2)
    public void noPermissionInAccessInfo() {
        clickButton(getElementByXpath("//*[@id=\"root\"]/div[1]/nav/ul/li[2]/a"));
        changeUrl("http://localhost:3000/");
        clickButton(getElementByXpath("//*[@id=\"root\"]/div[1]/nav/ul/li[2]/a"));
        assertEquals("Você não tem permissão de acesso.", getMessage());
    }

    @Test
    @Order(3)
    public void noPermissionInAddInfo() {
        changeUrl("http://localhost:3000/add-info");
        assertEquals("Você não tem permissão de acesso.", getMessage());
    }

    @Test
    @Order(4)
    public void noPermissionInSchedules() {
        changeUrl("http://localhost:3000/set-schedules");
        assertEquals("Você não tem permissão de acesso.", getMessage());
    }

    @Test
    @Order(5)
    public void noPermissionInEditInfo() {
        changeUrl("http://localhost:3000/edit-info");
        assertEquals("Você não tem permissão de acesso.", getMessage());
    }

    @Test
    @Order(6)
    public void logoutSystem(){
        login();
        clickButton(getElementByXpath("//*[@id=\"root\"]/div[2]/div/div[1]/div[2]/button[2]"));
        assertEquals("http://localhost:3000/", browser.getCurrentUrl());
    }

    private void changeUrl(String url) {
        browser.get(url);
        sleep(2000);
    }

}
