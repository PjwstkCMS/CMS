package pl.edu.pjwstk.cms.dto;

import java.io.Serializable;
import java.util.logging.Logger;


public class TerminalDto implements Serializable{

    private final static Logger LOGGER = Logger.getLogger(TerminalDto.class.getName());
    
    private Long id;
    private String description;
    private String mac;

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

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
    
    
}
