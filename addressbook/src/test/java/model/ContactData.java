package model;

public record ContactData(String firstName, String middleName, String lastName) {

    public ContactData() {
        this(" ", " ", " ");
    }

    public ContactData withMiddleName (String middleName) {
        return new ContactData(this.firstName, middleName, this.lastName);
    }

    public ContactData withLastName (String lastName) {
        return new ContactData(this.firstName, this.middleName, lastName);
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData(firstName, this.middleName, this.lastName);
    }

}
