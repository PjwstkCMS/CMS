package pl.edu.pjwstk.cms.models;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.models.general.DatabaseObject;

/**
 *
 * @author Sergio
 */
public class PersonData extends DatabaseObject{
    
    private final static Logger LOGGER = Logger.getLogger(Customer.class.getName()); 
    
    private String forename;
    private String surname;
    private String email;
    private String phone;
    private String pesel;

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }    

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }
    
    public PersonData() {
        super();       

    }
}
