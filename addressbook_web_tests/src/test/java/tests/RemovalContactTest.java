package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class RemovalContactTest extends TestBase {

    @Test
    public void canRemoveContact() {

        if (app.hbm().getContactCount() == 0) {
            app.contact().createContact(new ContactData(
                    "",
                    "first name",
                    "middle_name",
                    "last_name",
                    "e-mail",
                    randomFile("src/test/resources/images"),
                    "",
                    "",
                    "",
                    ""));
        }

        var oldContacts = app.hbm().getContactList();

        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());

        var contactToRemove = oldContacts.get(index);

        app.contact().removeContact(contactToRemove);

        var newContacts = app.hbm().getContactList();

        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);

        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    void canRemoveAllContactsAtOnce() {

        if (app.contact().getCount() == 0) {
            app.contact().createContact(new ContactData(
                    "",
                    "first name",
                    "middle_name",
                    "last_name",
                    "e-mail",
                    randomFile("src/test/resources/images"
                    ),"",
                    "",
                    "",
                    ""));
        }

        app.contact().removeAllContacts();

        Assertions.assertEquals(0, app.hbm().getContactCount());
    }


}
