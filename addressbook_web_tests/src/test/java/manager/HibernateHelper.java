package manager;

import manager.hbm.GroupRecord;
import model.GroupDate;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.jspecify.annotations.NonNull;
import org.hibernate.cfg.Configuration;


import java.util.ArrayList;
import java.util.List;

public class HibernateHelper extends HelperBase {
    private SessionFactory sessionFactory;

    public HibernateHelper(ApplicationManager manager) {
        super(manager);
        sessionFactory = new Configuration()
//                        .addAnotatedClass(Book.class)
                .addAnnotatedClass(GroupRecord.class)
                .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook")
                .setProperty(AvailableSettings.USER, "root")
                .setProperty(AvailableSettings.PASS, "")
                .buildSessionFactory();
    }

    static List<GroupDate> convertList(List<GroupRecord> records) {
        List<GroupDate> result = new ArrayList<>();
        for (var record : records) {
            result.add(convert(record));
        }
        return result;
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

    public void createGroup(GroupDate groupDate) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(groupDate));
            session.getTransaction().commit();
        });
    }
}
