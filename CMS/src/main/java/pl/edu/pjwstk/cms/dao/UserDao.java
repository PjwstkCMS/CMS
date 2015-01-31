package pl.edu.pjwstk.cms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.dto.UserDto;
import pl.edu.pjwstk.cms.models.Employee;
import pl.edu.pjwstk.cms.models.PersonData;
import pl.edu.pjwstk.cms.models.PrivilegeGroup;
import pl.edu.pjwstk.cms.models.User;


public class UserDao extends GenericDao<User> {

    private final static Logger LOGGER = Logger.getLogger(UserDao.class.getName());

    public UserDao() {
        super(User.class);
    }

    public List<UserDto> getUserDtos() {
        return getUserDtos("");
    }
    
    public List<UserDto> getUserNames() {
        List<UserDto> dtos = new ArrayList<>();

        String query = "SELECT user.id, login ";
        query += "FROM user;";

        ResultSet resultSet = this.connectionManager.select(query);
        PrivilegeGroupDao groupDao = new PrivilegeGroupDao();
        List<PrivilegeGroup> groups = groupDao.selectAll();

        try {
            while (resultSet.next()) {
                UserDto dto = new UserDto();
                dto.setId(resultSet.getLong("id"));
                dto.setLogin(resultSet.getString("login"));
                dtos.add(dto);
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return dtos;
    }
    
    public List<UserDto> getSendUsers(Long senderId) {
        List<UserDto> dtos = new ArrayList<>();

        String query = "SELECT id, login ";
        query += "FROM user ";
        query += "WHERE id!="+senderId+";";

        ResultSet resultSet = this.connectionManager.select(query);
        PrivilegeGroupDao groupDao = new PrivilegeGroupDao();
        List<PrivilegeGroup> groups = groupDao.selectAll();

        try {
            while (resultSet.next()) {
                UserDto dto = new UserDto();
                dto.setId(resultSet.getLong("id"));
                dto.setLogin(resultSet.getString("login"));
                dtos.add(dto);
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return dtos;
    }
    
    public List<UserDto> getUserDtos(String where) {
        List<UserDto> dtos = new ArrayList<>();

        String query = "SELECT user.id, login, password, employeeId, groupId, photoHash ";
        if(!where.isEmpty()) {
            query+=where;
        }
        query += "FROM user;";

        ResultSet resultSet = this.connectionManager.select(query);

        EmployeeDao empDao = new EmployeeDao();
        List<Employee> employees = empDao.selectAll();
        PersonDataDao personDao = new PersonDataDao();
        List<PersonData> persons = personDao.selectAll();

        PrivilegeGroupDao groupDao = new PrivilegeGroupDao();
        List<PrivilegeGroup> groups = groupDao.selectAll();

        try {
            while (resultSet.next()) {
                UserDto dto = new UserDto();
                dto.setId(resultSet.getLong("id"));
                dto.setLogin(resultSet.getString("login"));
                dto.setPassword(resultSet.getString("password"));
                dto.setEmployeeId(resultSet.getLong("employeeId"));

                dto.setPhotoHash(resultSet.getString("photoHash"));

                Employee e = getEmployee(employees, resultSet.getString("employeeId"));
                dto.setPersondataId(Long.parseLong(e.getPersondataId()));
                PersonData person = getPersonData(e.getPersondataId(), persons);
                dto.setEmployee(person.getForename() + " " + person.getSurname());

                PrivilegeGroup group = getPrivilegeGroup(groups, resultSet.getString("groupId"));
                dto.setGroupId(group.getId());
                dto.setGroup(group.getName());

                dtos.add(dto);
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return dtos;
    }

    private Employee getEmployee(List<Employee> employee, String id) {
        for (Employee c : employee) {
            if (c.getId() == Long.parseLong(id)) {
                return c;
            }
        }
        return null;
    }

    private PersonData getPersonData(String personDataId, List<PersonData> persons) {
        for (PersonData p : persons) {
            if (p.getId() == Long.parseLong(personDataId)) {
                return p;
            }
        }
        return null;
    }

    private PrivilegeGroup getPrivilegeGroup(List<PrivilegeGroup> privilege, String id) {
        for (PrivilegeGroup p : privilege) {
            if (p.getId() == Long.parseLong(id)) {
                return p;
            }
        }
        return null;
    }
}
