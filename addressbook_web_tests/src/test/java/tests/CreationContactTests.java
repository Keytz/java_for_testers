package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class CreationContactTests extends TestBase {

  public static List<ContactData> contactProvider() {
    var result = new ArrayList<ContactData>();

    for (var firstname : List.of("", "first name")) {
      for (var middleName : List.of("", "middle_name")) {
        for (var lastname : List.of("", "last_name")) {
          for (var telephone : List.of("", "telephone")) {
            for (var email : List.of("", "e-mail")) {

              result.add(new ContactData(
                      "", firstname,
                      middleName,
                      lastname,
                      telephone,
                      email
              ));
            }
          }
        }
      }
    }

    for (int i = 0; i < 5; i++) {
      result.add(new ContactData(
              "", randomString(i * 10),
              randomString(i * 10),
              randomString(i * 10),
              randomString(i * 10),
              randomString(i * 10)
      ));
    }

    return result;
  }
  @ParameterizedTest
  @MethodSource("contactProvider")
  public void canCreateMultipleContacts(ContactData contact) {

    int contactCount = app.contact().getCount();

    app.contact().createContact(contact);

    int newContactCount = app.contact().getCount();

    Assertions.assertEquals(contactCount + 1, newContactCount);
  }
  }



