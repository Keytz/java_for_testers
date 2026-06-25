package tests;

import common.CommonFunctions;
import model.ContactData;
import model.GroupDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CreationContactTests extends TestBase {

  public static List<ContactData> contactProvider() throws IOException {
    var mapper = new com.fasterxml.jackson.dataformat.xml.XmlMapper();

    return mapper.readValue(
            new File("contact.xml"),
            new com.fasterxml.jackson.core.type.TypeReference<List<ContactData>>() {}
    );
  }

  @ParameterizedTest
  @MethodSource("contactProvider")
  void canCreateMultipleContacts(ContactData contact) {

    var oldContacts = app.hbm().getContactList();

    app.contact().createContact(contact);

    var newContacts = app.hbm().getContactList();

    // 1. проверяем, что контакт добавился
    Assertions.assertEquals(oldContacts.size() + 1, newContacts.size());

    // 2. проверяем, что последний контакт имеет нужное имя
    var created = newContacts.stream()
            .max((c1, c2) -> Integer.compare(
                    Integer.parseInt(c1.id()),
                    Integer.parseInt(c2.id())
            ))
            .orElseThrow();

    Assertions.assertEquals(contact.firstname(), created.firstname());
    Assertions.assertEquals(contact.lastname(), created.lastname());
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