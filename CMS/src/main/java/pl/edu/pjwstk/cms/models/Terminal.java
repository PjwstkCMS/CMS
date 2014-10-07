package pl.edu.pjwstk.cms.models;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.models.general.DatabaseObject;

/**
 *
 * @author Macha
 */
public class Terminal extends DatabaseObject{
    
    private final static Logger LOGGER = Logger.getLogger(Terminal.class.getName());
    
    private String description;
    private String mac;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
        
    public Terminal(){
        super();
    }
}
