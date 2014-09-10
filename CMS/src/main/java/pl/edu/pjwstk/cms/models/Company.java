package pl.edu.pjwstk.cms.models;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.models.general.DatabaseObject;

/**
 *
 * @author Konrad
 */
public class Company extends DatabaseObject{
    
    private final static Logger LOGGER = Logger.getLogger(Company.class.getName());

    private String name;
    private String contactName;
    private String contractSurname;
    private String contactPhone;
    private String contactEmail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContractSurname() {
        return contractSurname;
    }

    public void setContractSurname(String contractSurname) {
        this.contractSurname = contractSurname;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }


    
    public Company() {
        super();
    }

}
