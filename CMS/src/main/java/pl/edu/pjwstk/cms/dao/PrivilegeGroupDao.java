package pl.edu.pjwstk.cms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.dto.PrivilegeGroupDto;
import pl.edu.pjwstk.cms.models.Privilege;
import pl.edu.pjwstk.cms.models.PrivilegeGroup;


public class PrivilegeGroupDao extends GenericDao<PrivilegeGroup>{
    
    private final static Logger LOGGER = Logger.getLogger(PrivilegeGroupDao.class.getName()); 

    public PrivilegeGroupDao() {
        super(PrivilegeGroup.class);
    }
    
    public List<PrivilegeGroupDto> getDtoList() {
        return getDtoList(new HashMap<String, List<String>>());
    }

    public List<PrivilegeGroupDto> getDtoList(Map<String, List<String>> params) {
        String query = "SELECT name, id ";
        query += "FROM privilegegroup";
        if (!params.isEmpty()) {
            query += "WHERE";
            query = this.addParamConditions(query, params);
        }
        ResultSet set = this.connectionManager.select(query);
        List<PrivilegeGroupDto> dtos = new ArrayList<>();
        try {
            while (set.next()) {
                PrivilegeGroupDto dto = new PrivilegeGroupDto();
                dto.setId(set.getLong("id"));
                dto.setName(set.getString("name"));
                
                PrivilegeDao privDao = new PrivilegeDao();
                List<Privilege> privs = privDao.selectRecordsWithFieldValues("groupId", dto.getId());
                dto.setPrivileges(privs);
                dtos.add(dto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return dtos;
    }
}
