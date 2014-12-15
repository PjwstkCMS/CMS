package pl.edu.pjwstk.cms.dto;

import java.io.Serializable;
import java.util.logging.Logger;
import pl.edu.pjwstk.cms.models.Task;

/**
 *
 * @author Macha
 */
public class TaskDto implements Serializable {
    
    private final static Logger LOGGER = Logger.getLogger(TaskDto.class.getName()); 
    
    private Long id, employeeId, managerId, dictId;
    private String employee, startDate, closeDate, finalisationDate, description, dict;
    
    public TaskDto() {
        super();
    }
    
    public TaskDto(Task task) {
        this.setId(task.getId());
        this.employeeId = Long.parseLong(task.getEmployeeId());
        this.dictId = Long.parseLong(task.getDictId());
        
        this.startDate = task.getStartDate();
        this.closeDate = task.getCloseDate();
        this.finalisationDate = task.getFinalisationDate();
        this.description = task.getDescription();
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getDictId() {
        return dictId;
    }

    public void setDictId(Long dictId) {
        this.dictId = dictId;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
    }

    public String getFinalisationDate() {
        return finalisationDate;
    }

    public void setFinalisationDate(String finalisationDate) {
        this.finalisationDate = finalisationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDict() {
        return dict;
    }

    public void setDict(String dict) {
        this.dict = dict;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

}
