package model;

public record ContactData(String id,
                          String firstname,
                          String middleName,
                          String lastname,
                          String email,
                          String photo,
                          String home,
                          String mobile,
                          String work,
                          String secondary) {

    public ContactData() {
        this("", "", "", "", "", "", "", "", "","");
    }

    public ContactData withId(String id) {
        return new ContactData(
                id,
                this.firstname,
                this.middleName,
                this.lastname,
                this.email,
                this.photo,
                this.home,
                this.mobile,
                this.work,
                this.secondary);
    }

    public ContactData withFirstName(String firstname) {
        return new ContactData(
                this.id,
                firstname,
                this.middleName,
                this.lastname,
                this.email,
                this.photo,
                this.home,
                this.mobile,
                this.work,
                this.secondary);
    }

    public ContactData withMiddleName(String middleName) {
        return new ContactData(
                this.id,
                this.firstname,
                middleName,
                this.lastname,
                this.email,
                this.photo,
                this.home,
                this.mobile,
                this.work,
                this.secondary);
    }

    public ContactData withLastName(String lastname) {
        return new ContactData(
                this.id,
                this.firstname,
                this.middleName,
                lastname,
                this.email,
                this.photo,
                this.home,
                this.mobile,
                this.work,
                this.secondary);
    }
    public ContactData withTelephone(String telephone) {
        return new ContactData(
                this.id,
                this.firstname,
                this.middleName,
                this.lastname,
                this.email,
                this.photo,
                this.home,
                this.mobile,
                this.work,
                this.secondary);
    }
    public ContactData withEmail(String email) {
        return new ContactData(
                this.id,
                this.firstname,
                this.middleName,
                this.lastname,
                email,
                this.photo,
                this.home,
                this.mobile,
                this.work,
                this.secondary);
    }
    public ContactData withPhoto(String photo) {
        return new ContactData(
                this.id,
                this.firstname,
                this.middleName,
                this.lastname,
                this.email,
                photo,
                this.home,
                this.mobile,
                this.work,
                this.secondary);
    }
    public ContactData withHome(String home) {
        return new ContactData(
                this.id,
                this.firstname,
                this.middleName,
                this.lastname,
                this.email,
                this.photo,
                home,
                this.mobile,
                this.work,
                this.secondary);
    }
    public ContactData withMobile(String mobile) {
        return new ContactData(
                this.id,
                this.firstname,
                this.middleName,
                this.lastname,
                this.email,
                this.photo,
                this.home,
                mobile,
                this.work,
                this.secondary);
    }
    public ContactData withWork(String work) {
        return new ContactData(
                this.id,
                this.firstname,
                this.middleName,
                this.lastname,
                this.email,
                this.photo,
                this.home,
                this.mobile,
                work,
                this.secondary);
    }
    public ContactData withSecondary(String secondary) {
        return new ContactData(
                this.id,
                this.firstname,
                this.middleName,
                this.lastname,
                this.email,
                this.photo,
                this.home,
                this.mobile,
                this.work,
                secondary);
    }
}