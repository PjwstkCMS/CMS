package pl.edu.pjwstk.cms.dto;

import java.util.logging.Logger;

/**
 *
 * @author Macha
 */
public class PrivilegeDto {

    private final static Logger LOGGER = Logger.getLogger(PrivilegeDto.class.getName()); 
    
    private Long id;
    private String groupId, keyId;
    
    public PrivilegeDto() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

}
