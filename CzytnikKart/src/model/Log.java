package model;



import java.util.logging.Logger;

/**
 *
 * @author Macha
 */
public class Log extends DatabaseObject{
    
    private final static Logger LOGGER = Logger.getLogger(Log.class.getName());
    
    private String timestamp;
    private String terminalId;
    private String employeeId;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    
    
    public Log(){
        super();
    }
}
