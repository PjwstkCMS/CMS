package pl.edu.pjwstk.cms.models;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.models.general.DatabaseObject;

/**
 *
 * @author Sergio
 */
public class User extends DatabaseObject{
    
    private final static Logger LOGGER = Logger.getLogger(User.class.getName()); 

    private String login;
    private String password;
    private String email;
    private String employeeId;
    
    public User() {

    }
}
