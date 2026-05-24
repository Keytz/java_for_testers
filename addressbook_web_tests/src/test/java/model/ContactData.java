package model;

public record ContactData(String id, String firstname, String middleName, String lastname, String telephone, String email, String photo) {

    public ContactData() {
        this("", "", "", "", "", "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(
                id,
                this.firstname,
                this.middleName,
                this.lastname,
                this.telephone,
                this.email,
                this.photo
        );
    }

    public ContactData withFirstname(String firstname) {
        return new ContactData(
                this.id,
                firstname,
                this.middleName,
                this.lastname,
                this.telephone,
                this.email,
                this.photo
        );
    }

    public ContactData withMiddleName(String middleName) {
        return new ContactData(
                this.id,
                this.firstname,
                middleName,
                this.lastname,
                this.telephone,
                this.email,
                this.photo
        );
    }

    public ContactData withLastname(String lastname) {
        return new ContactData(
                this.id,
                this.firstname,
                this.middleName,
                lastname,
                this.telephone,
                this.email,
                this.photo
        );
    }
    public ContactData withTelephone(String telephone) {
        return new ContactData(
                this.id,
                this.firstname,
                this.middleName,
                this.lastname,
                telephone,
                this.email,
                this.photo
        );
    }
    public ContactData withEmail(String email) {
        return new ContactData(
                this.id,
                this.firstname,
                this.middleName,
                this.lastname,
                this.telephone,
                email,
                this.photo
        );
    }
    public ContactData withPhoto(String photo) {
        return new ContactData(
                this.id,
                this.firstname,
                this.middleName,
                this.lastname,
                this.telephone,
                this.email,
                photo
        );
    }
}