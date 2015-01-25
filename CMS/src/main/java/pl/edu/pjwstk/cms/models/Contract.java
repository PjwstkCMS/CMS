
package pl.edu.pjwstk.cms.models;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.models.general.DatabaseObject;


public class Contract extends DatabaseObject{
    
    private final static Logger LOGGER = Logger.getLogger(Contract.class.getName());
    
    private String employeeId;
    private String customerId;
    private String startDate;
    private String closeDate;
    private String finalisationDate; //optional
    private String description;
    private String price;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
    }

    public String getFinalisationDate() {
        return finalisationDate;
    }

    public void setFinalisationDate(String finalisationDate) {
        this.finalisationDate = finalisationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Contract() {
        super();
    }
    
    
}
