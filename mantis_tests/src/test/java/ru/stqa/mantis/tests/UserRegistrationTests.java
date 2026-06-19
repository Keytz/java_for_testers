package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;

import java.time.Duration;
import java.util.regex.Pattern;

public class UserRegistrationTests extends TestBase {

    @Test
    void canRegisterUser() {


        var username = CommonFunctions.randomString(8);
        var email = username + "@localhost";
        var password = "password";


        System.out.println("USERNAME = " + username);
        System.out.println("EMAIL = " + email);
        app.jamesApi().addUser(username + "@localhost", password);


        app.mail().drain(email, password);


        app.user().createUser(username , email);



        var messages = app.mail().receive(
                email,
                password,
                Duration.ofSeconds(60)
        );

        Assertions.assertEquals(1, messages.size());


        var text = messages.get(0).content();

        var pattern = Pattern.compile("http://\\S+");
        var matcher = pattern.matcher(text);

        String url;
        if (matcher.find()) {
            url = text.substring(matcher.start(), matcher.end());
        } else {
            throw new RuntimeException("No activation link found");
        }


       app.user().finishRegistration(url, password);


        app.http().login(username, password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }}