package pl.edu.pjwstk.cms.models;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.models.general.DatabaseObject;

/**
 *
 * @author Konrad
 */
public class CompanyAddress extends DatabaseObject{
    
    private final static Logger LOGGER = Logger.getLogger(CompanyAddress.class.getName());
    
    private String addressId;
    private String description; 

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    

    public CompanyAddress() {
        super();
    }

}