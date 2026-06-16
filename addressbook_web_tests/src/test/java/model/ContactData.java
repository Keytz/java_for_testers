package model;

public record ContactData(String id,
                          String firstname,
                          String middleName,
                          String lastname,
                          String photo,
                          String home,
                          String mobile,
                          String work,
                          String email,
                          String email2,
                          String email3,
                          String address) {

    public ContactData() {
        this("", "", "", "", "", "", "", "", "","","","");
    }

    public ContactData withId(String id) {
        return new ContactData(
                id,
                this.firstname,
                this.middleName,
                this.lastname,
                this.photo,
                this.home,
                this.mobile,
                this.work,
                this.email,
                this.email2,
                this.email3,
                this.address);
    }

    public ContactData withFirstName(String firstname) {
        return new ContactData(
                this.id,
                firstname,
                this.middleName,
                this.lastname,
                this.photo,
                this.home,
                this.mobile,
                this.work,
                this.email,
                this.email2,
                this.email3,
                this.address);
    }

    public ContactData withMiddleName(String middleName) {
        return new ContactData(
                this.id,
                this.firstname,
                middleName,
                this.lastname,
                this.photo,
                this.home,
                this.mobile,
                this.work,
                this.email,
                this.email2,
                this.email3,
                this.address);
    }

    public ContactData withLastName(String lastname) {
        return new ContactData(
                this.id,
                this.firstname,
                this.middleName,
                lastname,
                this.photo,
                this.home,
                this.mobile,
                this.work,
                this.email,
                this.email2,
                this.email3,
                this.address);
    }
    public ContactData withTelephone(String telephone) {
        return new ContactData(
                this.id,
                this.firstname,
                this.middleName,
                this.lastname,
                this.photo,
                this.home,
                this.mobile,
                this.work,
                this.email,
                this.email2,
                this.email3,
                this.address);
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(
                this.id,
                this.firstname,
                this.middleName,
                this.lastname,
                photo,
                this.home,
                this.mobile,
                this.work,
                this.email,
                this.email2,
                this.email3,
                this.address);
    }
    public ContactData withHome(String home) {
        return new ContactData(
                this.id,
                this.firstname,
                this.middleName,
                this.lastname,
                this.photo,
                home,
                this.mobile,
                this.work,
                this.email,
                this.email2,
                this.email3,
                this.address);
    }
    public ContactData withMobile(String mobile) {
        return new ContactData(
                this.id,
                this.firstname,
                this.middleName,
                this.lastname,
                this.photo,
                this.home,
                mobile,
                this.work,
                this.email,
                this.email2,
                this.email3,
                this.address);
    }
    public ContactData withWork(String work) {
        return new ContactData(
                this.id,
                this.firstname,
                this.middleName,
                this.lastname,
                this.photo,
                this.home,
                this.mobile,
                work,
                this.email,
                this.email2,
                this.email3,
                this.address);
    }
    public ContactData withEmail(String email) {
        return new ContactData(
                this.id,
                this.firstname,
                this.middleName,
                this.lastname,
                this.photo,
                this.home,
                this.mobile,
                this.work,
                email,
                this.email2,
                this.email3,
                this.address);
    }
    public ContactData withEmail2(String email2) {
        return new ContactData(
                this.id,
                this.firstname,
                this.middleName,
                this.lastname,
                this.photo,
                this.home,
                this.mobile,
                this.work,
                this.email,
                email2,
                this.email3,
                this.address);
    }
    public ContactData withEmail3(String email3) {
        return new ContactData(
                this.id,
                this.firstname,
                this.middleName,
                this.lastname,
                this.photo,
                this.home,
                this.mobile,
                this.work,
                this.email,
                this.email2,
                email3,
                this.address);
    }
    public ContactData withAddress(String address) {
        return new ContactData(
                this.id,
                this.firstname,
                this.middleName,
                this.lastname,
                this.photo,
                this.home,
                this.mobile,
                this.work,
                this.email,
                this.email2,
                this.email3,
                address);
    }


}