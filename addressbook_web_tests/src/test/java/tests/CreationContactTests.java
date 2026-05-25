package tests;

import common.CommonFunctions;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
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
                      "",
                      firstname,
                      middleName,
                      lastname,
                      telephone,
                      email,
                      randomFile("src/test/resources/images")
              ));
            }
          }
        }
      }
    }

    for (int i = 0; i < 5; i++) {
      result.add(new ContactData(
              "", CommonFunctions.randomString(i ),
              CommonFunctions.randomString(i ),
              CommonFunctions.randomString(i ),
              CommonFunctions.randomString(i ),
              CommonFunctions.randomString(i ),
              "src/test/resources/images/photo.png"
      ));
    }

    return result;
  }
  @ParameterizedTest
  @MethodSource("contactProvider")
  public void canCreateMultipleContacts(ContactData contact) {

    var oldContacts = app.contact().getList();

    app.contact().createContact(contact);

    var newContacts = app.contact().getList();

    Comparator<ContactData> compareById = (o1, o2) -> {
      return Integer.compare(
              Integer.parseInt(o1.id()),
              Integer.parseInt(o2.id())
      );
    };

    newContacts.sort(compareById);

    var expectedList = new ArrayList<>(oldContacts);

    expectedList.add(
            new ContactData() .withId(newContacts.get(newContacts.size() - 1).id())
                    .withFirstname(contact.firstname())
                    .withLastname(contact.lastname()) );

    expectedList.sort(compareById);

    Assertions.assertEquals(newContacts, expectedList);
  }
  }



