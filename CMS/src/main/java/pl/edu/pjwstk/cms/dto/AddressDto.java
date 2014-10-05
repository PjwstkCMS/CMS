package pl.edu.pjwstk.cms.dto;

import java.io.Serializable;
import java.util.logging.Logger;

/**
 *
 * @author Macha
 */
public class AddressDto implements Serializable{
    
    private final static Logger LOGGER = Logger.getLogger(AddressDto.class.getName()); 

    private Long id, companyId, dictId, persondataId;
    private String country, city, streetName, streetNumber, apartmentNumber, postalCode;
    
    public AddressDto() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getDictId() {
        return dictId;
    }

    public void setDictId(Long dictId) {
        this.dictId = dictId;
    }

    public Long getPersondataId() {
        return persondataId;
    }

    public void setPersondataId(Long persondataId) {
        this.persondataId = persondataId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    
}
