
package pl.edu.pjwstk.cms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.dto.EmployeeDto;
import pl.edu.pjwstk.cms.models.Department;
import pl.edu.pjwstk.cms.models.Employee;
/**
 *
 * @author Konrad
 */
public class EmployeeDao extends GenericDao<Employee>{
     
    private final static Logger LOGGER = Logger.getLogger(EmployeeDao.class.getName()); 

    public EmployeeDao() {
        super(Employee.class);
    }
public List<EmployeeDto> getEmployeeDtoList() {
        return getEmployeeDtoList(new HashMap<String, List<String>>());
    }
    
    public List<EmployeeDto> getEmployeeDtoList(Map<String, List<String>> params) {
        String query = "SELECT emp.name as name, emp.surname as surname, emp.email as email, emp.departmentId as departmentId, emp.phone as phone, emp.id as id ";
        query += "FROM employee as emp ";
        if(!params.isEmpty()) {
            query += "WHERE";
            query = this.addParamConditions(query, params);
        }
        ResultSet set = this.connectionManager.select(query);
        List<EmployeeDto> empDtos = new ArrayList<>();
        try {
            while(set.next()) {
                EmployeeDto dto = new EmployeeDto();
                dto.setId(set.getLong("id"));
                dto.setName(set.getString("name"));
                dto.setSurname(set.getString("surname"));
                dto.setEmail(set.getString("email"));
                dto.setPhone(set.getString("phone"));
                DepartmentDao depDao = new DepartmentDao();
                List<Department> deps = depDao.selectAll();
                Department d = getEmpDepartment(deps, set.getString("departmentId"));
                dto.setDepartmentId(d.getName());
                empDtos.add(dto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return empDtos;
    }
    private Department getEmpDepartment(List<Department> departments, String id) {
        for (Department c : departments) {
            if(c.getId() == Long.parseLong(id)) {
                return c;
            }
        }
        return null;
    }
}
