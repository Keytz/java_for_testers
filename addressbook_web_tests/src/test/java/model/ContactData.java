package model;

public record ContactData(String id, String firstname, String middleName, String lastname, String telephone, String email) {

    public ContactData() {
        this("", "", "", "", "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(
                id,
                this.firstname,
                this.middleName,
                this.lastname,
                this.telephone,
                this.email
        );
    }

    public ContactData withFirstname(String firstname) {
        return new ContactData(
                this.id,
                firstname,
                this.middleName,
                this.lastname,
                this.telephone,
                this.email
        );
    }

    public ContactData withMiddleName(String middleName) {
        return new ContactData(
                this.id,
                this.firstname,
                middleName,
                this.lastname,
                this.telephone,
                this.email
        );
    }

    public ContactData withLastname(String lastname) {
        return new ContactData(
                this.id,
                this.firstname,
                this.middleName,
                lastname,
                this.telephone,
                this.email
        );
    }
    public ContactData withTelephone(String telephone) {
        return new ContactData(
                this.id,
                this.firstname,
                this.middleName,
                this.lastname,
                telephone,
                this.email
        );
    }
    public ContactData withEmail(String email) {
        return new ContactData(
                this.id,
                this.firstname,
                this.middleName,
                this.lastname,
                this.telephone,
                email
        );
    }
}