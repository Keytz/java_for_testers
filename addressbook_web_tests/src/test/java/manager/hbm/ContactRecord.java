package manager.hbm;

import jakarta.persistence.*;

@Entity
@Table(name = "addressbook")
public class ContactRecord {

    @Id
    @Column(name = "id")
    public int id;

    @Column(name = "firstname")
    public String firstname;

    @Column(name = "middlename")
    public String middleName;

    @Column(name = "lastname")
    public String lastname;

    public ContactRecord() {
    }

    public ContactRecord(int id, String firstname, String middleName, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.middleName = middleName;
        this.lastname = lastname;
    }
}