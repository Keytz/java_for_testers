package manager;

import model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

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

    public void removeContact(ContactData contact) {
        openHome();
        selectContact(contact);
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

    public void removeAllContacts() {
        openHome();
        selectAllContacts();
        removeSelectedContact();
    }
    private void selectAllContacts() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));

        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }
    private void selectContact(ContactData contact) {

        new WebDriverWait(manager.driver(), Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector(
                                String.format(
                                        "input[value='%s']",
                                        contact.id()
                                )
                        )
                ));

        click(By.cssSelector(
                String.format(
                        "input[value='%s']",
                        contact.id()
                )
        ));
    }

    private void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public List<ContactData> getList() {

        openHome();

        var contacts = new ArrayList<ContactData>();

        var spans = manager.driver.findElements(By.name("entry"));

        for (var row : spans) {

            var cells = row.findElements(By.tagName("td"));

            var checkbox = cells.get(0)
                    .findElement(By.name("selected[]"));

            var id = checkbox.getAttribute("value");

            var lastname = cells.get(1).getText();

            var firstname = cells.get(2).getText();

            contacts.add(new ContactData()
                    .withId(id)
                    .withFirstname(firstname)
                    .withLastname(lastname));
        }

        return contacts;
    }
}