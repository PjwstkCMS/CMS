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
import pl.edu.pjwstk.cms.models.Address;

/**
 *
 * @author Konrad
 */
public class EmployeeDto implements Serializable {
    
    private final static Logger LOGGER = Logger.getLogger(EmployeeDto.class.getName()); 

    private Long id, persondataId, positionId, cardId;
    private String name, surname, email, phone, departmentId, salary;
    private Address adresZameldowania;
    
    public EmployeeDto() {
        super();
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
    
    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }
    
    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }
    
    public Long getPersondataId() {
        return persondataId;
    }

    public void setPersondataId(Long persondataId) {
        this.persondataId = persondataId;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAdresZameldowania() {
        return adresZameldowania;
    }

    public void setAdresZameldowania(Address adresZameldowania) {
        this.adresZameldowania = adresZameldowania;
    }
    
    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
    
    

    
    public List<String> getPrivilegeKeyCodes() {
        return privilegeKeyCodes;
    }

    public void setPrivilegeKeyCodes(List<String> privilegeKeyCodes) {
        this.privilegeKeyCodes = privilegeKeyCodes;
    }
    private List<String> privilegeKeyCodes = new ArrayList<>();
    
}
