package pl.edu.pjwstk.cms.models;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.models.general.DatabaseObject;

/**
 *
 * @author Sergio
 */
public class Customer extends DatabaseObject{
    
    private final static Logger LOGGER = Logger.getLogger(Customer.class.getName()); 
    
    private int id;
    private String name;
    private String surname;
    private String email;
    private long phone;
    private int companyId;

    public Customer() {
        
        

    }
}
