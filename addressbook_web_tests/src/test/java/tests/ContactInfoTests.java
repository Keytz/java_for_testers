package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {
    @Test
    void testPhones() {
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                Stream.of(contact.home(), contact.mobile(), contact.work())
                        .filter(s -> s != null && !"".equals(s))
                        .collect(Collectors.joining("\n"))
        ));
        var phones = app.contact().getPhones();
        Assertions.assertEquals(expected, phones);
    }

    @Test
    void testAddress() {
        var contact = app.contact().getList().get(1);

        var info = app.contact().getInfo(contact);

        Assertions.assertEquals(
                info.address(),
                app.contact().getAddress(contact)
        );
    }
    @Test
    void testEmails() {
        var contact = app.contact().getList().get(1);

        var info = app.contact().getInfo(contact);

        var expected = Stream.of(
                        info.email(),
                        info.email2(),
                        info.email3())
                .filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.joining("\n"));

        Assertions.assertEquals(
                expected,
                app.contact().getEmails(contact)
        );
    }
}