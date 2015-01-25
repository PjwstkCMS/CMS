package pl.edu.pjwstk.cms.models.general;

import java.util.logging.Logger;


public class DatabaseObject {
    
    private final static Logger LOGGER = Logger.getLogger(DatabaseObject.class.getName()); 

    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public DatabaseObject() {

    }
}
