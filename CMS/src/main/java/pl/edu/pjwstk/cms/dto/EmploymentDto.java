package pl.edu.pjwstk.cms.dto;

import java.io.Serializable;
import java.util.logging.Logger;

/**
 *
 * @author Macha
 */
public class EmploymentDto implements Serializable {
    
    private final static Logger LOGGER = Logger.getLogger(EmployeeDto.class.getName()); 

    private Long id, employeeId, dictId;
    private String dateFrom, dateTo;
    
    public EmploymentDto() {
        super();
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

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }
    
    
}
