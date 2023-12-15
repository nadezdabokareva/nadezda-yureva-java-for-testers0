package model;

import java.util.Objects;

public record ContactData(String id, String firstName, String middleName, String lastName) {

    public ContactData() {
        this(" ", " ", " ", " ");
    }

    public ContactData withMiddleName (String middleName) {
        return new ContactData(this.id, this.firstName, middleName, this.lastName);
    }

    public ContactData withLastName (String lastName) {
        return new ContactData(this.id, this.firstName, this.middleName, lastName);
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData(this.id, firstName, this.middleName, this.lastName);
    }
    public ContactData withId(String id) {
        return new ContactData(id, this.firstName, this.middleName, this.lastName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(middleName, that.middleName) && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, middleName, lastName);
    }
}
