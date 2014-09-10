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
public class PrivilegeGroup extends DatabaseObject{
    
    private String name;
    
    public PrivilegeGroup() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
