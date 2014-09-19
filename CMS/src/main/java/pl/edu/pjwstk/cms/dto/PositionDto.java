/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.edu.pjwstk.cms.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.PositionDao;
import pl.edu.pjwstk.cms.models.Address;
import pl.edu.pjwstk.cms.models.Position;
import pl.edu.pjwstk.cms.models.Contract;

/**
 *
 * @author Konrad
 */
public class PositionDto implements Serializable {
    
    private final static Logger LOGGER = Logger.getLogger(PositionDto.class.getName()); 

    private Long id;
    private String name, description;
    
    public PositionDto() {
        super();
    }
    
    public PositionDto(Position position) {
        this.setId(position.getId());
        this.name = position.getName();
        this.description = position.getDescription();
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    
    
    public List<String> getPrivilegeKeyCodes() {
        return privilegeKeyCodes;
    }

    public void setPrivilegeKeyCodes(List<String> privilegeKeyCodes) {
        this.privilegeKeyCodes = privilegeKeyCodes;
    }
    private List<String> privilegeKeyCodes = new ArrayList<>();
    
}
