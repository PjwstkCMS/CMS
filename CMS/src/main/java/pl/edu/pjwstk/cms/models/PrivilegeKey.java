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
public class PrivilegeKey extends DatabaseObject{
    
    private String code, description;
    
    public PrivilegeKey() {
        super();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
