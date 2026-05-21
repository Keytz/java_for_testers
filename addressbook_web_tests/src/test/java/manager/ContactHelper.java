package manager;

import model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContactHelper extends HelperBase {



    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createContact(ContactData contact) {
        openAddNewPage();
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }

    public void removeContact() {
        openHome();
        selectContact();
        removeSelectedContact();
        returnToHomePage();
    }

    private void openAddNewPage() {
        click(By.linkText("add new"));
    }

    private void openHome() {
        if (!manager.isElementPresent(By.id("maintable"))) {
            click(By.linkText("home"));}
    }
    public boolean isContactPresent() {
        openHome();
        return manager.isElementPresent(By.name("selected[]"));
    }

    public int getCount() {
        openHome();
        return manager.driver.findElements(By.name("selected[]")).size();
    }


    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstname());
        type(By.name("middlename"), contact.middleName());
        type(By.name("lastname"), contact.lastname());
        type(By.name("home"), contact.telephone());
        type(By.name("email"), contact.email());
    }

    private void submitContactCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }
    private void removeSelectedContact() {
        click(By.name("delete"));

    }
    private void selectContact() {
        new WebDriverWait(manager.driver(), Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(By.name("selected[]")));
        click(By.name("selected[]"));
    }

    private void returnToHomePage() {
        click(By.linkText("home page"));
    }
}