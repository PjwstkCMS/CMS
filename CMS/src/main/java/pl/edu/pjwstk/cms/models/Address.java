
package pl.edu.pjwstk.cms.models;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.models.general.DatabaseObject;

/**
 *
 * @author Konrad
 */
public class Address extends DatabaseObject{
    
    private final static Logger LOGGER = Logger.getLogger(Address.class.getName());
    
    private String country;
    private String city;
    private String cityName;
    private String streetNumb;
    private String apartmentNumb;
    private String zipCode;

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

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStreetNumb() {
        return streetNumb;
    }

    public void setStreetNumb(String streetNumb) {
        this.streetNumb = streetNumb;
    }

    public String getApartmentNumb() {
        return apartmentNumb;
    }

    public void setApartmentNumb(String apartmentNumb) {
        this.apartmentNumb = apartmentNumb;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    
    
    public Address(){
        super();
    }
}
