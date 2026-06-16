package manager;

import model.ContactData;
import model.GroupDate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    public void createContact(ContactData contact, GroupDate group) {
        openAddNewPage();
        fillContactForm(contact);
        selectGroup(group);
        submitContactCreation();
        returnToHomePage();
    }

    private void selectGroup(GroupDate group) {
       new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
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
        type(By.name("home"), contact.home());
        type(By.name("email"), contact.email());
        if (contact.photo() != null && !contact.photo().isEmpty()) {
            attach(By.name("photo"), contact.photo());
        }
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
    public void modifyContact(
            ContactData contact,
            ContactData modifiedContact
    ) {
        openHome();
        selectContact(contact);
        initContactModification(contact);
        fillContactForm(modifiedContact);
        submitContactModification();
        returnToHomePage();
    }
    private void initContactModification(ContactData contact) {
        click(By.xpath(String.format(
                "//a[@href='edit.php?id=%s']",
                contact.id()
        )));
    }
    private void submitContactModification() {
        click(By.name("update"));
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
                    .withFirstName(firstname)
                    .withLastName(lastname));
        }

        return contacts;
    }


    public void addContactToGroup(ContactData contact,
                                  GroupDate group) {

        openHome();

        selectContact(contact);

        new Select(
                manager.driver.findElement(
                        By.name("to_group"))
        ).selectByValue(group.id());

        click(By.name("add"));


    }

    public void removeContactFromGroup(ContactData contact,
                                       GroupDate group) {

        openHome();

        new WebDriverWait(manager.driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.name("group")
                ));

        new Select(
                manager.driver.findElement(By.name("group"))
        ).selectByValue(group.id());

        selectContact(contact);

        click(By.name("remove"));
    }

    public String getPhones(ContactData contact) {
        return manager.driver.findElement(
                By.xpath(
                        String.format("//input[@id='%s']/../../td[6]", contact.id())
                )
        ).getText();
    }

    public Map<String, String> getPhones() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows){
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var phones = row.findElements(By.tagName("td")).get(5).getText();
            result.put(id, phones);
        }
        return result;
    }

    public ContactData getInfo(ContactData contact) {
        openHome();
        initContactModification(contact);

        var firstname = manager.driver.findElement(By.name("firstname")).getAttribute("value");
        var lastname = manager.driver.findElement(By.name("lastname")).getAttribute("value");

        var address = manager.driver.findElement(By.name("address")).getAttribute("value");

        var email = manager.driver.findElement(By.name("email")).getAttribute("value");
        var email2 = manager.driver.findElement(By.name("email2")).getAttribute("value");
        var email3 = manager.driver.findElement(By.name("email3")).getAttribute("value");

        return new ContactData()
                .withId(contact.id())
                .withFirstName(firstname)
                .withLastName(lastname)
                .withAddress(address)
                .withEmail(email)
                .withEmail2(email2)
                .withEmail3(email3);
    }

    public String getAddress(ContactData contact) {

        var rows = manager.driver.findElements(By.cssSelector("tr"));


        for (var row : rows) {

            var inputs = row.findElements(By.cssSelector("input[name='selected[]']"));

            if (!inputs.isEmpty() &&
                    inputs.get(0).getAttribute("value").equals(contact.id())) {

                var cells = row.findElements(By.tagName("td"));
                return cells.get(3).getText();
            }
        }
        return "";
    }

    public String getEmails(ContactData contact) {
        var rows = manager.driver.findElements(By.name("entry"));

        for (var row : rows) {
            var id = row.findElement(By.tagName("input"))
                    .getAttribute("value");

            if (id.equals(contact.id())) {
                return row.findElements(By.tagName("td"))
                        .get(4)   // Emails колонка
                        .getText();
            }
        }
        return "";
    }
}