/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
    private String employeeId, customerId;
    
    public ContractDto() {
        super();
    }
    
    public ContractDto(Contract contract) {
        this.setId(contract.getId());
        this.employeeId = contract.getEmployeeId();
        this.customerId = contract.getCustomerId();
        
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
    

    

    
    public List<String> getPrivilegeKeyCodes() {
        return privilegeKeyCodes;
    }

    public void setPrivilegeKeyCodes(List<String> privilegeKeyCodes) {
        this.privilegeKeyCodes = privilegeKeyCodes;
    }
    private List<String> privilegeKeyCodes = new ArrayList<>();
    
}
