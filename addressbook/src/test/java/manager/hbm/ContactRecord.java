package manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;
import java.util.Random;

@Entity
@Table(name = "addressbook")
public class ContactRecord {

    @Id
    public int id;
    public String firstname;
    public String middlename;
    public String lastname;
    public String address;
    public String mobile;
    public String home;
    public String work;
    public String phone2;
//    public String nickname = "";
//    public String company = "";
//    public String title = "";
//    public String fax = "";
    public String email;
    public String email2;
    public String email3;
//    public String im = "";
//    public String im2 = "";
//    public String im3 = "";
//    public String homepage = "";
//    public String bday = "";
//    public String bmonth = "";
//    public String byear = "";
//    public Integer aday = new Random().nextInt();
//    public String amonth = "";
//    public String ayear = "";
//    public String address2 = "";
//    public String notes = "";
//    public Date deprecated = new Date();


    public ContactRecord(){

    }

    public ContactRecord (int id, String firstname, String middlename, String lastname, String address
                            ) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
    }
//    String mobile, String home, String work, String secondary
}
