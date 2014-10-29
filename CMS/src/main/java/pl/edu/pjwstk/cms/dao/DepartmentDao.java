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
import pl.edu.pjwstk.cms.models.Address;
import pl.edu.pjwstk.cms.models.Department;
import pl.edu.pjwstk.cms.models.Employee;
import pl.edu.pjwstk.cms.models.PersonData;


public class DepartmentDao extends GenericDao<Department>{
    
    private final static Logger LOGGER = Logger.getLogger(DepartmentDao.class.getName()); 

    public DepartmentDao() {
        super(Department.class);
    }
    public List<DepartmentDto> getDepartmentDtoList() {
        return getDepartmentDtoList(new HashMap<String, List<String>>());
    }
    
    public List<DepartmentDto> getDepartmentDtoList(Map<String, List<String>> params) {
        String query = "SELECT dep.name as name, dep.managerId as managerId, dep.addressId as addressId, dep.id as id ";
        query += "FROM department as dep ";
        if(!params.isEmpty()) {
            query += "WHERE";
            query = this.addParamConditions(query, params);
        }
        ResultSet set = this.connectionManager.select(query);
        List<DepartmentDto> depDtos = new ArrayList<>();
        
        EmployeeDao empDao = new EmployeeDao();
        List<Employee> emps = empDao.selectAll();
        
        PersonDataDao perDao = new PersonDataDao();
        List<PersonData> persons = perDao.selectAll();
        try {
            while(set.next()) {
                DepartmentDto dto = new DepartmentDto();
                dto.setId(set.getLong("id"));
                dto.setName(set.getString("name"));
                dto.setAddressId(set.getLong("addressId"));
                
                AddressDao addDao = new AddressDao();
                List<Address> adds = addDao.selectRecordsWithFieldValues("id", dto.getAddressId());
                dto.setAddress(adds.get(0));
                
                Employee e = getDepEmployee(emps, set.getString("managerId"));
                dto.setManagerId(set.getLong("managerId"));
                PersonData person = getPersonData(persons, e.getPersondataId());
                dto.setManager(person.getForename() + " " + person.getSurname());
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
    
    private PersonData getPersonData(List<PersonData> persons, String id) {
        for (PersonData c : persons) {
            if(c.getId() == Long.parseLong(id)) {
                return c;
            }
        }
        return null;
    }
}

