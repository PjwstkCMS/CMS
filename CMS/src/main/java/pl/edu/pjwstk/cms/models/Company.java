/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.edu.pjwstk.cms.models;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.models.general.DatabaseObject;

/**
 *
 * @author Konrad
 */
public class Company extends DatabaseObject{
    
    private final static Logger LOGGER = Logger.getLogger(Company.class.getName());
    
    private int id;
    private int companyAddressId;
    private String companyName;
    private String companyContactName;
    private String companyContractSurname;
    private long companyContactPhone;
    private String companyContactEmail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompanyAddressId() {
        return companyAddressId;
    }

    public void setCompanyAddressId(int companyAddressId) {
        this.companyAddressId = companyAddressId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyContactName() {
        return companyContactName;
    }

    public void setCompanyContactName(String companyContactName) {
        this.companyContactName = companyContactName;
    }

    public String getCompanyContractSurname() {
        return companyContractSurname;
    }

    public void setCompanyContractSurname(String companyContractSurname) {
        this.companyContractSurname = companyContractSurname;
    }

    public long getCompanyContactPhone() {
        return companyContactPhone;
    }

    public void setCompanyContactPhone(long companyContactPhone) {
        this.companyContactPhone = companyContactPhone;
    }

    public String getCompanyContactEmail() {
        return companyContactEmail;
    }

    public void setCompanyContactEmail(String companyContactEmail) {
        this.companyContactEmail = companyContactEmail;
    }
    
    
    
}
