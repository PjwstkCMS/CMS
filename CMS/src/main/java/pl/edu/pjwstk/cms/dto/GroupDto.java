package pl.edu.pjwstk.cms.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.PrivilegeDao;
import pl.edu.pjwstk.cms.dao.PrivilegeKeyDao;
import pl.edu.pjwstk.cms.models.Privilege;
import pl.edu.pjwstk.cms.models.PrivilegeGroup;
import pl.edu.pjwstk.cms.models.PrivilegeKey;

/**
 *
 * @author Macha
 */
public class GroupDto implements Serializable{
    
    private final static Logger LOGGER = Logger.getLogger(GroupDto.class.getName()); 
    
    private Long id;
    private String name;
    private List<Long> privilegeKeyIds = new ArrayList<>();
    private List<String> privilegeKeyNames = new ArrayList<>();
    
    public GroupDto() {
    }

    public GroupDto(PrivilegeGroup group) {
        this.id = group.getId();
        this.name = group.getName();

        PrivilegeDao privilegeDao = new PrivilegeDao();
        List<Privilege> privList = (List<Privilege>) privilegeDao.selectRecordsWithFieldValues("groupId", this.getId().toString());
        
        Map<String, List<Object>> privileges = new HashMap();
        List<Object> privilegeList = new ArrayList();
        for (int i = 0; i < privList.size(); i++) {
            privilegeList.add(privList.get(i).getKeyId());
        }
        if(privilegeList.size() > 0) privileges.put("id", privilegeList);
        
        PrivilegeKeyDao privilegeKeyDao = new PrivilegeKeyDao();
        if (!privileges.isEmpty()) {
            for (PrivilegeKey key : (List<PrivilegeKey>) privilegeKeyDao.selectForFieldsWithMultiplePossibileValues(privileges)) {
                privilegeKeyIds.add(key.getId());
                privilegeKeyNames.add(key.getCode());
            }
        }

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

    public List<Long> getPrivilegeKeyIds() {
        return privilegeKeyIds;
    }

    public void setPrivilegeKeyIds(List<Long> privilegeKeyIds) {
        this.privilegeKeyIds = privilegeKeyIds;
    }

    public List<String> getPrivilegeKeyNames() {
        return privilegeKeyNames;
    }

    public void setPrivilegeKeyNames(List<String> privilegeKeyNames) {
        this.privilegeKeyNames = privilegeKeyNames;
    }
}
