package pl.edu.pjwstk.cms.models;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.models.general.DatabaseObject;


public class Employee extends DatabaseObject{

    private final static Logger LOGGER = Logger.getLogger(Employee.class.getName());
    
    private String positionId;
    private String departmentId;
    private String persondataId;
    private String salary;

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

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
    
    

    public Employee() {
        super();
    }

    
    
    
}