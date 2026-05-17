package model;

public record ContactData(String firstname, String middleName, String lastname, String telephone, String email) {

    public ContactData() {
        this("", "", "", "", "");
    }

    public ContactData withFirstname(String firstname) {
        return new ContactData(
                firstname,
                this.middleName,
                this.lastname,
                this.telephone,
                this.email
        );
    }

    public ContactData withMiddleName(String middleName) {
        return new ContactData(
                this.firstname,
                middleName,
                this.lastname,
                this.telephone,
                this.email
        );
    }

    public ContactData withLastname(String lastname) {
        return new ContactData(
                this.firstname,
                this.middleName,
                lastname,
                this.telephone,
                this.email
        );
    }

    public ContactData withTelephone(String telephone) {
        return new ContactData(
                this.firstname,
                this.middleName,
                this.lastname,
                telephone,
                this.email
        );
    }

    public ContactData withEmail(String email) {
        return new ContactData(
                this.firstname,
                this.middleName,
                this.lastname,
                this.telephone,
                email
        );
    }
}