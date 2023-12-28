package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public record ContactData(
        @JsonIgnore
        String id,
        String firstName,
        String middleName,
        String lastName,
        String photo,
        String address,
        String mobile,
        String home,
        String work,
        String secondary) {

    public ContactData() {
        this(" ", " ", " ", " ", "", "", "", "", "", "");
    }

    public ContactData withMiddleName (String middleName) {
        return new ContactData(this.id, this.firstName, middleName, this.lastName, this.photo, this.address, this.home, this.mobile, this.work, this.secondary);
    }

    public ContactData withLastName (String lastName) {
        return new ContactData(this.id, this.firstName, this.middleName, lastName, this.photo, this.address, this.home, this.mobile, this.work, this.secondary);
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData(this.id, firstName, this.middleName, this.lastName, this.photo, this.address, this.home, this.mobile, this.work, this.secondary);
    }
    public ContactData withId(String id) {
        return new ContactData(id, this.firstName, this.middleName, this.lastName, this.photo, this.address, this.home, this.mobile, this.work, this.secondary);
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, photo, this.address, this.home, this.mobile, this.work, this.secondary);
    }
    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.photo, address, this.home, this.mobile, this.work, this.secondary);
    }
    public ContactData withHome(String home) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.photo, this.address, home, this.mobile, this.work, this.secondary);
    }
    public ContactData withMobile(String mobile) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.photo, this.address, this.home, mobile, this.work, this.secondary);
    }
    public ContactData withWork(String work) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.photo, this.address, this.home, this.mobile, work, this.secondary);
    }
    public ContactData withSecondary(String secondary) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.photo, this.address, this.home, this.mobile, this.work, secondary);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, middleName, lastName, photo);
    }
}
