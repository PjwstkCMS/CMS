/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.pjwstk.cms.dto;

import java.io.Serializable;
import java.util.logging.Logger;

/**
 *
 * @author sergio
 */
public class LogDto implements Serializable {
    
     private final static Logger LOGGER = Logger.getLogger(AddressDto.class.getName()); 
     
     private Long id, employeeId, terminalId;
     private String empName, empSurname, date, terminalDesc;
     
     public LogDto() {
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

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpSurname() {
        return empSurname;
    }

    public void setEmpSurname(String empSurname) {
        this.empSurname = empSurname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(Long terminalId) {
        this.terminalId = terminalId;
    }

    public String getTerminalDesc() {
        return terminalDesc;
    }

    public void setTerminalDesc(String terminalDesc) {
        this.terminalDesc = terminalDesc;
    }
    
}
