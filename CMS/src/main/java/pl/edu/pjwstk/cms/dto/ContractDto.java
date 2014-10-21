package pl.edu.pjwstk.cms.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.CompanyDao;
import pl.edu.pjwstk.cms.models.Address;
import pl.edu.pjwstk.cms.models.Company;
import pl.edu.pjwstk.cms.models.Contract;

/**
 *
 * @author Konrad
 */
public class ContractDto implements Serializable {
    
    private final static Logger LOGGER = Logger.getLogger(ContractDto.class.getName()); 

    private Long id;
    private String employeeId, customerId, startDate, closeDate, finalisationDate, description, price;
    
    public ContractDto() {
        super();
    }
    
    public ContractDto(Contract contract) {
        this.setId(contract.getId());
        this.employeeId = contract.getEmployeeId();
        this.customerId = contract.getCustomerId();
        this.startDate = contract.getStartDate();
        this.closeDate = contract.getCloseDate();
        this.finalisationDate = contract.getFinalisationDate();
        this.description = contract.getDescription();
        this.price = contract.getPrice();
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
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
    

    

    
    public List<String> getPrivilegeKeyCodes() {
        return privilegeKeyCodes;
    }

    public void setPrivilegeKeyCodes(List<String> privilegeKeyCodes) {
        this.privilegeKeyCodes = privilegeKeyCodes;
    }
    private List<String> privilegeKeyCodes = new ArrayList<>();
    
}
