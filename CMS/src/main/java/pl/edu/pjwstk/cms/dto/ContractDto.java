package pl.edu.pjwstk.cms.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import pl.edu.pjwstk.cms.models.Contract;


public class ContractDto implements Serializable {
    
    private final static Logger LOGGER = Logger.getLogger(ContractDto.class.getName()); 

    private Long id, employeeId, customerId;
    private String employee, customer, startDate, closeDate, finalisationDate, description, price;
    
    public ContractDto() {
        super();
    }
    
    public ContractDto(Contract contract) {
        this.setId(contract.getId());
        this.employeeId = Long.parseLong(contract.getEmployeeId());
        this.customerId = Long.parseLong(contract.getCustomerId());
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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
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
