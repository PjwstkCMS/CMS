
package pl.edu.pjwstk.cms.models;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.models.general.DatabaseObject;

/**
 *
 * @author Konrad
 */
public class Address extends DatabaseObject{
    
    private final static Logger LOGGER = Logger.getLogger(Address.class.getName());
    
    private int id;
    private String country;
    private String city;
    private String cityName;
    private int streetNumb;
    private int apartmentNumb;
    private String zipCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getStreetNumb() {
        return streetNumb;
    }

    public void setStreetNumb(int streetNumb) {
        this.streetNumb = streetNumb;
    }

    public int getApartmentNumb() {
        return apartmentNumb;
    }

    public void setApartmentNumb(int apartmentNumb) {
        this.apartmentNumb = apartmentNumb;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    
    public Address(){
        
    }
}
