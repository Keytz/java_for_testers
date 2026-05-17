package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class CreationContactTests extends TestBase {

  @Test
  public void canCreateContact() {
    app.contact().createContact(new ContactData(
            "first name",
            "middle_name",
            "last_name",
            "telephone",
            "e-mail"
    ));
  }}



