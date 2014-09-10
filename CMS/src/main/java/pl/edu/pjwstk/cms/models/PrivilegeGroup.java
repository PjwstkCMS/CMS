package pl.edu.pjwstk.cms.models;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.models.general.DatabaseObject;

/**
 *
 * @author Macha
 */
public class PrivilegeGroup extends DatabaseObject{
    
    private final static Logger LOGGER = Logger.getLogger(PrivilegeGroup.class.getName());
    
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    public PrivilegeGroup(){
        super();
    }
}
