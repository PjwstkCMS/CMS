package pl.edu.pjwstk.cms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.dto.UserDto;
import pl.edu.pjwstk.cms.models.Employee;
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
    
    public List<UserDto> getUserDtos() {
        return getUser();
    }
    
    public List<UserDto> getUser() {
        List<UserDto> dtos = new ArrayList<>();
        
        String query = "SELECT user.id, login, password, employeeId, groupId, photoHash "
                + "FROM user;";
        
        ResultSet resultSet = this.connectionManager.select(query);
        try {
            while (resultSet.next()) {
                UserDto dto = new UserDto();
                dto.setId(resultSet.getLong("id"));
                dto.setLogin(resultSet.getString("login"));
                dto.setPassword(resultSet.getString("password"));
                dto.setEmployeeId(resultSet.getLong("employeeId"));
                dto.setGroupId(resultSet.getLong("groupId"));
                dto.setPhotoHash(resultSet.getString("photoHash"));
                
                EmployeeDao empDao = new EmployeeDao();
                List<Employee> emp = empDao.selectRecordsWithFieldValues("id", dto.getEmployeeId());
                dto.setPersondataId(emp.get(0).getPersondataId());
                
                dtos.add(dto);
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return dtos;
    }
    
}
