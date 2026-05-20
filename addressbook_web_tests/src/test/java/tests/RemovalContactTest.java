package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class RemovalContactTest extends TestBase{

  @Test
  public void CanRemovalContact()  {
    if (!app.contact().isContactPresent()) {
      app.contact().createContact(new ContactData(
              "first name",
              "middle_name",
              "last_name",
              "telephone",
              "e-mail"
      ));
    }
    app.contact().removeContact();

  }
}
