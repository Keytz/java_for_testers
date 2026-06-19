package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

public class UserHelper extends HelperBase {

    public UserHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createUser(String username, String email) {

        manager.driver().get(manager.property("web.baseUrl") + "/login_page.php");


        click(By.linkText("Signup for a new account"));


        type(By.id("username"), username);
        type(By.id("email-field"), email);


        click(By.xpath("//input[@value='Signup']"));
    }

    public void finishRegistration(String link, String password) {
        manager.driver().get(link);

        type(By.name("password"), password);
        type(By.name("password_confirm"), password);

        click(By.cssSelector("button[type='submit'], input[type='submit']"));
    }
}