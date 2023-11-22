package model;

public record ContactData(String firstName, String middleName, String lastName) {

    public ContactData() {
        this(" ",  " ", " ");
    }

}
