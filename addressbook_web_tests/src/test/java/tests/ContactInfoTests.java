package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    void testPhones() {

        var contacts = app.hbm().getContactList();

        Assertions.assertFalse(contacts.isEmpty(), "No contacts in DB");

        var contact = contacts.get(0);

        var phonesFromUI = app.contact().getPhones();

        var expected = Stream.of(
                        contact.home(),
                        contact.mobile(),
                        contact.work()
                ).filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.joining("\n"));

        Assertions.assertEquals(expected, phonesFromUI.get(contact.id()));
    }

    @Test
    void testAddress() {

        var contacts = app.hbm().getContactList();
        Assertions.assertFalse(contacts.isEmpty(), "No contacts in DB");

        var contact = contacts.get(0);

        var uiAddress = app.contact().getAddress(contact);

        Assertions.assertEquals(contact.address(), uiAddress);
    }

//    @Test
//    void testEmails() {
//
//        var contacts = app.hbm().getContactList();
//
//        Assertions.assertFalse(contacts.isEmpty(), "No contacts in DB");
//
//        var contact = contacts.get(0);
//
//        var uiInfo = app.contact().getInfo(contact);
//
//        var expected = Stream.of(
//                        contact.email(),
//                        contact.email2(),
//                        contact.email3()
//                ).filter(s -> s != null && !s.isEmpty())
//                .collect(Collectors.joining("\n"));
//
//        Assertions.assertEquals(expected,
//                app.contact().getEmails(contact));
//    }
}