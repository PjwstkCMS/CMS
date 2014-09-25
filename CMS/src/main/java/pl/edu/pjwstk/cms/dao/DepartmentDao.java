package pl.edu.pjwstk.cms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.dto.DepartmentDto;
import pl.edu.pjwstk.cms.models.Department;
import pl.edu.pjwstk.cms.models.Employee;
/**
 *
 * @author Macha
 */
public class DepartmentDao extends GenericDao<Department>{
    
    private final static Logger LOGGER = Logger.getLogger(DepartmentDao.class.getName()); 

    public DepartmentDao() {
        super(Department.class);
    }
    public List<DepartmentDto> getDepartmentDtoList() {
        return getDepartmentDtoList(new HashMap<String, List<String>>());
    }
    
    public List<DepartmentDto> getDepartmentDtoList(Map<String, List<String>> params) {
        String query = "SELECT dep.name as name, dep.managerId as managerId, dep.id as id ";
        query += "FROM department as dep ";
        if(!params.isEmpty()) {
            query += "WHERE";
            query = this.addParamConditions(query, params);
        }
        ResultSet set = this.connectionManager.select(query);
        List<DepartmentDto> depDtos = new ArrayList<>();
        try {
            while(set.next()) {
                DepartmentDto dto = new DepartmentDto();
                dto.setId(set.getLong("id"));
                dto.setName(set.getString("name"));
                EmployeeDao empDao = new EmployeeDao();
                List<Employee> emps = empDao.selectAll();
                Employee e = getDepEmployee(emps, set.getString("managerId"));
                dto.setManagerId(set.getString("managerId"));
                depDtos.add(dto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        int a = 2;
        return depDtos;
    }
    private Employee getDepEmployee(List<Employee> employees, String id) {
        for (Employee c : employees) {
            if(c.getId() == Long.parseLong(id)) {
                return c;
            }
        }
        return null;
    }
}

