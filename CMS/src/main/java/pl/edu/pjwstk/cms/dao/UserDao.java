package pl.edu.pjwstk.cms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.dto.UserDto;
import pl.edu.pjwstk.cms.models.PrivilegeGroup;
import pl.edu.pjwstk.cms.models.User;

/**
 *
 * @author Sergio
 */
public class UserDao extends GenericDao<User>{
    
    private final static Logger LOGGER = Logger.getLogger(UserDao.class.getName()); 

    public UserDao() {
        super(User.class);
    }
    
    public List<UserDto> getUserWithConfig() {
        return getUserWithConfig("");
    }
    public List<UserDto> getUserWithConfig(String conditions) {
        List<UserDto> dtos = new ArrayList<>();
        if(conditions.length()>0) {
            conditions+=" AND "; 
        }
        String query = "SELECT user.id as id, emp.persondataId as persondataId, pers.name as name, pers.surname as surname, "
                + "user.login as login, user.password as password, user.employeeId as employeeId, pers.email as email, "
                + "user.groupId as groupId, user.photoHash as photoHash FROM user, employee as emp, persondata as pers "
                + "WHERE " + conditions + "user.employeeId = emp.id AND emp.persondataId = pers.id";
        ResultSet resultSet = this.connectionManager.select(query);
        PrivilegeGroupDao privGroupDao = new PrivilegeGroupDao();
        List<PrivilegeGroup> groups = privGroupDao.selectAll();
        try {
            while (resultSet.next()) {
                UserDto dto = new UserDto();
                dto.setId(resultSet.getLong("id"));
                dto.setName(resultSet.getString("name"));
                dto.setSurname(resultSet.getString("surname"));
                dto.setLogin(resultSet.getString("login"));
                dto.setPassword(resultSet.getString("password"));
                dto.setPersondataId(resultSet.getString("persondataId"));
                dto.setGroupId(resultSet.getString("groupId"));
                dto.setEmployeeId(resultSet.getString("employeeId"));
                dto.setEmail(resultSet.getString("email"));
                dto.setPhotoHash(resultSet.getString("photoHash"));
                if(dto.getGroupId()!=null){
                    for (PrivilegeGroup g : groups) {
                        if(g.getId() == Long.parseLong(dto.getGroupId())) {
                            dto.setGroupName(g.getName());
                        }
                    }
                }
                dtos.add(dto);
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return dtos;
    }
    
}
