package pl.edu.pjwstk.cms.dto;

import java.io.Serializable;
import java.util.logging.Logger;

/**
 *
 * @author Macha
 */
public class TerminalDto implements Serializable{

    private final static Logger LOGGER = Logger.getLogger(EmployeeDto.class.getName());
    
    private Long id;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
