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
import pl.edu.pjwstk.cms.models.Customer;

/**
 *
 * @author Konrad
 */
public class CustomerDto implements Serializable {
    
    private final static Logger LOGGER = Logger.getLogger(CustomerDto.class.getName()); 

    private Long id;
    private String name, surname, email, companyName, phone;
    
    public CustomerDto() {
        super();
    }
    
    public CustomerDto(Customer customer) {
        this.setId(customer.getId());
        this.name = customer.getName();
        this.surname = customer.getSurname();
        this.phone = customer.getPhone();
        this.email = customer.getEmail();
        Company company = new Company();
        CompanyDao companyDao = new CompanyDao();
        company = companyDao.selectRecordsWithFieldValues("id", customer.getCompanyId()).get(0);
        this.companyName = company.getName();
        
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

    public List<String> getPrivilegeKeyCodes() {
        return privilegeKeyCodes;
    }

    public void setPrivilegeKeyCodes(List<String> privilegeKeyCodes) {
        this.privilegeKeyCodes = privilegeKeyCodes;
    }
    private List<String> privilegeKeyCodes = new ArrayList<>();
    
}
