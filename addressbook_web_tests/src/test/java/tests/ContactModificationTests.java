package tests;

import model.ContactData;
import model.GroupDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTests extends TestBase {

    @Test
    void canModifyContact() {

        if (app.hbm().getContactCount() == 0) {
            app.contact().createContact(new ContactData(
                    "",
                    "first name",
                    "middle_name",
                    "last_name",
                    "telephone",
                    "e-mail",
                    randomFile("src/test/resources/images")
            ));
        }

        var oldContacts = app.hbm().getContactList();

        var rnd = new Random();

        var index = rnd.nextInt(oldContacts.size());

        var testData = new ContactData()
                .withFirstName("modified firstname")
                .withLastName("modified lastname");

        app.contact().modifyContact(
                oldContacts.get(index),
                testData
        );

        var newContacts = app.hbm().getContactList();

        var expectedList = new ArrayList<>(oldContacts);

        expectedList.set(
                index,
                testData
                        .withId(oldContacts.get(index).id())
        );

        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(
                    Integer.parseInt(o1.id()),
                    Integer.parseInt(o2.id())
            );
        };

        newContacts.sort(compareById);

        expectedList.sort(compareById);

        Assertions.assertEquals(newContacts, expectedList);
    }


    @Test
    void canAddContactToGroup() {

        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(
                    new GroupDate("", "name", "header", "footer"));
        }

        if (app.hbm().getContactCount() == 0) {
            app.contact().createContact(
                    new ContactData()
                            .withFirstName("first")
                            .withLastName("last"));
        }

        var group = app.hbm().getGroupList().get(0);

        var relatedIds = app.hbm().getContactsInGroup(group)
                .stream()
                .map(ContactData::id)
                .toList();

        var contact = app.hbm().getContactList().stream()
                .filter(c -> !relatedIds.contains(c.id()))
                .findFirst()
                .orElse(null);

        if (contact == null) {

            app.contact().createContact(
                    new ContactData()
                            .withFirstName("first")
                            .withLastName("last"));

            var updatedRelatedIds = app.hbm().getContactsInGroup(group)
                    .stream()
                    .map(ContactData::id)
                    .toList();

            contact = app.hbm().getContactList().stream()
                    .filter(c -> !updatedRelatedIds.contains(c.id()))
                    .findFirst()
                    .orElseThrow();
        }

        var oldRelated = app.hbm().getContactsInGroup(group);

        app.contact().addContactToGroup(contact, group);

        var newRelated = app.hbm().getContactsInGroup(group);

        Assertions.assertEquals(
                oldRelated.size() + 1,
                newRelated.size()
        );

        var contactId = contact.id();

        Assertions.assertTrue(
                newRelated.stream()
                        .anyMatch(c -> c.id().equals(contactId))
        );
    }

    @Test
    void canRemoveContactFromGroup() {

        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(
                    new GroupDate("", "name", "header", "footer"));
        }

        var group = app.hbm().getGroupList().get(0);

        if (app.hbm().getContactsInGroup(group).isEmpty()) {

            if (app.hbm().getContactCount() == 0) {
                app.contact().createContact(
                        new ContactData()
                                .withFirstName("first")
                                .withLastName("last"));
            }

            var contact =
                    app.hbm().getContactList().get(0);

            app.contact().addContactToGroup(contact, group);
        }

        var contact =
                app.hbm().getContactsInGroup(group).get(0);

        var oldRelated =
                app.hbm().getContactsInGroup(group);

        app.contact().removeContactFromGroup(contact, group);

        var newRelated =
                app.hbm().getContactsInGroup(group);

        Assertions.assertEquals(
                oldRelated.size() - 1,
                newRelated.size()
        );

        Assertions.assertFalse(
                newRelated.contains(contact)
        );
    }


}
