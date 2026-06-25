package manager;

import io.qameta.allure.Step;
import manager.hbm.ContactRecord;
import manager.hbm.GroupRecord;
import model.ContactData;
import model.GroupDate;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HibernateHelper extends HelperBase {
    private SessionFactory sessionFactory;

    public HibernateHelper(ApplicationManager manager) {
        super(manager);
        sessionFactory = new Configuration()
//                        .addAnotatedClass(Book.class)
                .addAnnotatedClass(GroupRecord.class)
                .addAnnotatedClass(ContactRecord.class)
                .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=convertToNull")
                .setProperty(AvailableSettings.USER, "root")
                .setProperty(AvailableSettings.PASS, "")
                .buildSessionFactory();
    }

    static List<GroupDate> convertList(List<GroupRecord> records) {
        return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());
    }

    private static GroupDate convert(GroupRecord record) {
        return new GroupDate("" + record.id, record.name, record.header, record.footer);
    }

    private static GroupRecord convert(GroupDate date) {
        var id = date.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new GroupRecord(Integer.parseInt(id), date.name(), date.header(), date.footer());
    }
@Step
    public List<GroupDate> getGroupList() {
        return convertList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord", GroupRecord.class).list();
        }));
    }

    public long getGroupCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from GroupRecord", Long.class).getSingleResult();
        });
    }
@Step
    public void createGroup(GroupDate groupDate) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(groupDate));
            session.getTransaction().commit();
        });
    }
    public List<ContactData> getContactList() {
        return convertContactList(
                sessionFactory.fromSession(session ->
                        session.createQuery("from ContactRecord", ContactRecord.class)
                                .list()
                )
        );
    }


    public long getContactCount() {
        return sessionFactory.fromSession(session ->
                session.createQuery(
                        "select count(*) from ContactRecord",
                        Long.class
                ).getSingleResult()
        );
    }

    private static ContactData convert(ContactRecord record) {
        return new ContactData()
                .withId(String.valueOf(record.id))
                .withFirstName(record.firstname)
                .withMiddleName(record.middleName)
                .withLastName(record.lastname)
        .withHome(record.home)
                .withMobile(record.mobile)
                .withWork(record.work);
    }
    private static List<ContactData> convertContactList(List<ContactRecord> records) {
        return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());
    }

    public List<ContactData> getContactsInGroup(GroupDate group) {
        return sessionFactory.fromSession(session -> {
            return convertContactList(session.get(GroupRecord.class, group.id()).contacts);
        });
    }

    public List<GroupDate> getGroupsForContact(ContactData contact) {
        return sessionFactory.fromSession(session -> {
            return convertList(
                    session.get(
                            ContactRecord.class,
                            Integer.parseInt(contact.id())
                    ).groups
            );
        });
    }
}
