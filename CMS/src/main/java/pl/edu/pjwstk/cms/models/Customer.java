package pl.edu.pjwstk.cms.models;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.models.general.DatabaseObject;


public class Customer extends DatabaseObject {

    private final static Logger LOGGER = Logger.getLogger(Customer.class.getName());

    private String companyId;
    private String persondataId;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getPersondataId() {
        return persondataId;
    }

    public void setPersondataId(String persondataId) {
        this.persondataId = persondataId;
    }
    
    public Customer() {
        super();

    }
}
