package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class RemovalContactTest extends TestBase{

  @Test
  public void canRemoveContact() {

    if (app.contact().getCount() == 0) {
      app.contact().createContact(new ContactData(
              "",
              "first name",
              "middle_name",
              "last_name",
              "telephone",
              "e-mail"
      ));
    }

    var oldContacts = app.contact().getList();

    var rnd = new Random();
    var index = rnd.nextInt(oldContacts.size());

    var contactToRemove = oldContacts.get(index);

    app.contact().removeContact(contactToRemove);

    var newContacts = app.contact().getList();

    var expectedList = new ArrayList<>(oldContacts);
    expectedList.remove(index);

    Assertions.assertEquals(newContacts, expectedList);
  }
}
