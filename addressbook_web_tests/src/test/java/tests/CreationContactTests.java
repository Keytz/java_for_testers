package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import common.CommonFunctions;
import model.ContactData;
import model.GroupDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CreationContactTests extends TestBase {

//  public static List<ContactData> contactProvider() {
//    var result = new ArrayList<ContactData>();
//
//    for (var firstname : List.of("", "first name")) {
//      for (var middleName : List.of("", "middle_name")) {
//        for (var lastname : List.of("", "last_name")) {
//          for (var telephone : List.of("", "telephone")) {
//            for (var email : List.of("", "e-mail")) {
//
//              result.add(new ContactData(
//                      "",
//                      firstname,
//                      middleName,
//                      lastname,
//                      telephone,
//                      email,
//                      randomFile("src/test/resources/images")
//              ));
//            }
//          }
//        }
//      }
//    }
//
//
//
//    for (int i = 0; i < 5; i++) {
//      result.add(new ContactData(
//              "", CommonFunctions.randomString(i ),
//              CommonFunctions.randomString(i ),
//              CommonFunctions.randomString(i ),
//              CommonFunctions.randomString(i ),
//              CommonFunctions.randomString(i ),
//              "src/test/resources/images/photo.png"
//      ));
//    }
//
//    return result;
//  }

  public static List<ContactData> contactProvider() throws IOException {

    var mapper = new XmlMapper();

    return mapper.readValue(
            new File("contact.xml"),
            new TypeReference<List<ContactData>>() {}
    );
  }


  @ParameterizedTest
  @MethodSource("contactProvider")
  public void canCreateMultipleContacts(ContactData contact) {

    var oldContacts = app.hbm().getContactList();

    app.contact().createContact(contact);

    var newContacts = app.hbm().getContactList();

    Comparator<ContactData> compareById = (o1, o2) -> {
      return Integer.compare(
              Integer.parseInt(o1.id()),
              Integer.parseInt(o2.id())
      );
    };

    newContacts.sort(compareById);

    var expectedList = new ArrayList<>(oldContacts);

    expectedList.add(
            new ContactData()
                    .withId(newContacts.get(newContacts.size() - 1).id())
                    .withFirstName(contact.firstname())
                    .withMiddleName(contact.middleName())
                    .withLastName(contact.lastname()));

    expectedList.sort(compareById);

    Assertions.assertEquals(newContacts, expectedList);
  }
  @Test
  void canCreatedContactInGroup() {
    var contact = new ContactData()
            .withFirstName(CommonFunctions.randomString(10))
            .withLastName(CommonFunctions.randomString(10))
            .withPhoto(randomFile("src/test/resources/images"));
    if (app.hbm().getGroupCount() == 0) {
      app.hbm().createGroup(new GroupDate("", "name", "header", "footer"));
    }
    var group = app.hbm().getGroupList().get(0);
    var oldRelated = app.hbm().getContactsInGroup(group);
    app.contact().createContact(contact, group);
    var newRelated = app.hbm().getContactsInGroup(group);
    Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
  }


  }



