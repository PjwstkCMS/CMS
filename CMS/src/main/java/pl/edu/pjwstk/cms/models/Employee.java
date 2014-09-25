/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.edu.pjwstk.cms.models;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.models.general.DatabaseObject;

/**
 *
 * @author Konrad
 */
public class Employee extends DatabaseObject{

    private final static Logger LOGGER = Logger.getLogger(Employee.class.getName());
    
    private String positionId;
    private String departmentId;
    private String persondataId;

    public String getPersondataId() {
        return persondataId;
    }

    public void setPersondataId(String persondataId) {
        this.persondataId = persondataId;
    }
    
    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public Employee() {
        super();
    }

    
    
    
}