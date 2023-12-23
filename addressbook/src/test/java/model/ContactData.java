package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public record ContactData(
        @JsonIgnore
        String id,
        String firstName,
        String middleName,
        String lastName,
        String photo, String address) {

    public ContactData() {
        this(" ", " ", " ", " ", "", "");
    }

    public ContactData withMiddleName (String middleName) {
        return new ContactData(this.id, this.firstName, middleName, this.lastName, "", this.address);
    }

    public ContactData withLastName (String lastName) {
        return new ContactData(this.id, this.firstName, this.middleName, lastName, this.photo, this.address);
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData(this.id, firstName, this.middleName, this.lastName, this.photo, this.address);
    }
    public ContactData withId(String id) {
        return new ContactData(id, this.firstName, this.middleName, this.lastName, this.photo, this.address);
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, photo, this.address);
    }
    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, photo, address);
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
