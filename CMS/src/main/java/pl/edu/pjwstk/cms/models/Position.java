package pl.edu.pjwstk.cms.models;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.models.general.DatabaseObject;

/**
 *
 * @author Macha
 */
public class Position extends DatabaseObject{
    
    private final static Logger LOGGER = Logger.getLogger(Position.class.getName());
    
    private String name;
    private String description;
    private String hierarchy;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(String hierarchy) {
        this.hierarchy = hierarchy;
    }
    
    
    public Position(){
        super();
    }
}
