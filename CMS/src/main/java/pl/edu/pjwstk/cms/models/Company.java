package pl.edu.pjwstk.cms.models;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.models.general.DatabaseObject;

/**
 *
 * @author Konrad
 */
public class Company extends DatabaseObject{
    
    private final static Logger LOGGER = Logger.getLogger(Company.class.getName());

    private String companyAddressId;
    private String companyName;
    private String companyContactName;
    private String companyContractSurname;
    private String companyContactPhone;
    private String companyContactEmail;

    public String getCompanyAddressId() {
        return companyAddressId;
    }

    public void setCompanyAddressId(String companyAddressId) {
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

    public String getCompanyContactPhone() {
        return companyContactPhone;
    }

    public void setCompanyContactPhone(String companyContactPhone) {
        this.companyContactPhone = companyContactPhone;
    }

    public String getCompanyContactEmail() {
        return companyContactEmail;
    }

    public void setCompanyContactEmail(String companyContactEmail) {
        this.companyContactEmail = companyContactEmail;
    }

    public Company() {
        super();
    }

}
