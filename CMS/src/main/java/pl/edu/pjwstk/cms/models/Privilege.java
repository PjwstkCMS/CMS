package pl.edu.pjwstk.cms.models;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.models.general.DatabaseObject;


public class Privilege extends DatabaseObject{
    
    private final static Logger LOGGER = Logger.getLogger(Privilege.class.getName());
    
    private String groupId;
    private String keyId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }
    
    
    public Privilege(){
        super();
    }
}
