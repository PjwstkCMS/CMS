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

public class DepartmentDao extends GenericDao<Department> {

    private final static Logger LOGGER = Logger.getLogger(DepartmentDao.class.getName());

    public DepartmentDao() {
        super(Department.class);
    }

    public List<DepartmentDto> getDepartmentDtoList() {
        return getDepartmentDtoList(new HashMap<String, List<String>>());
    }

    public List<DepartmentDto> getDepartmentDtoList(Map<String, List<String>> params) {
        String query = "SELECT dep.name as name, p.forename as managerName, dep.addressId as addressId, "
                + "p.surname as managerSurname, dep.id as id, emp.id as managerId  "
                + "FROM department as dep, persondata as p, employee as emp "
                + "WHERE emp.id = dep.managerId AND p.id = emp.persondataId ";
        if (!params.isEmpty()) {
            query += "AND";
            query = this.addParamConditions(query, params);
        }
        ResultSet set = this.connectionManager.select(query);
        List<DepartmentDto> depDtos = new ArrayList<>();
        AddressDao aDao = new AddressDao();
        List<Address> allAdds = aDao.selectAll();
        try {
            while (set.next()) {
                DepartmentDto dto = new DepartmentDto();
                dto.setId(set.getLong("id"));
                dto.setName(set.getString("name"));
                dto.setAddressId(set.getLong("addressId"));

                List<Address> adds = new ArrayList();
                
                for (Address a : allAdds) {
                    if(a.getId()==dto.getAddressId()){
                        adds.add(a);
                    }
                }                
                dto.setAddress(adds.get(0));
                dto.setManagerId(set.getLong("managerId"));
                dto.setManager(set.getString("managerName")+ " " + set.getString("managerSurname"));
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
            if (c.getId() == Long.parseLong(id)) {
                return c;
            }
        }
        return null;
    }

    private PersonData getPersonData(List<PersonData> persons, String id) {
        for (PersonData c : persons) {
            if (c.getId() == Long.parseLong(id)) {
                return c;
            }
        }
        return null;
    }
}
