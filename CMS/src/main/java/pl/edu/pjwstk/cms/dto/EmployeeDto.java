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
import pl.edu.pjwstk.cms.models.Employee;

/**
 *
 * @author Konrad
 */
public class EmployeeDto implements Serializable {
    
    private final static Logger LOGGER = Logger.getLogger(EmployeeDto.class.getName()); 

    private Long id;
    private String name, surname, email;
    
    public EmployeeDto() {
        super();
    }
    
    public EmployeeDto(Employee employee) {
        this.setId(employee.getId());
        this.name = employee.getName();
        this.surname = employee.getSurname();
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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


    
    public List<String> getPrivilegeKeyCodes() {
        return privilegeKeyCodes;
    }

    public void setPrivilegeKeyCodes(List<String> privilegeKeyCodes) {
        this.privilegeKeyCodes = privilegeKeyCodes;
    }
    private List<String> privilegeKeyCodes = new ArrayList<>();
    
}
