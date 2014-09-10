/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.pjwstk.cms.models;
import pl.edu.pjwstk.cms.models.general.DatabaseObject;

/**
 *
 * @author Sergio
 */
public class Privilege extends DatabaseObject{
    
    private String groupId, keyId;
    
    public Privilege() {
        super();
    }

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
    
}
