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
import pl.edu.pjwstk.cms.models.Address;
import pl.edu.pjwstk.cms.models.Card;
import pl.edu.pjwstk.cms.models.Department;
import pl.edu.pjwstk.cms.models.Employee;
import pl.edu.pjwstk.cms.models.PersonData;
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
        String query = "SELECT emp.departmentId as departmentId, emp.id as id, emp.persondataId as persondataId, "
                + "emp.positionId as positionId, emp.salary as salary ";
        query += "FROM employee as emp ";
        if(!params.isEmpty()) {
            query += "WHERE";
            query = this.addParamConditions(query, params);
        }
        ResultSet set = this.connectionManager.select(query);
        PersonDataDao personDao = new PersonDataDao();
        CardDao carDao = new CardDao();
        AddressDao addDao = new AddressDao();
        List<EmployeeDto> empDtos = new ArrayList<>();
        List<PersonData> persons = personDao.selectAll();
        
        List<Card> cards = carDao.selectAll();
        try {
            while(set.next()) {
                EmployeeDto dto = new EmployeeDto();
                PersonData person = getPersonData(set.getString("persondataId"), persons);
                dto.setPersondataId(Long.parseLong(set.getString("persondataId")));
                dto.setId(set.getLong("id"));
                dto.setForename(person.getForename());
                dto.setSurname(person.getSurname());
                dto.setEmail(person.getEmail());
                dto.setPhone(person.getPhone());
                dto.setPesel(person.getPesel());
                dto.setPositionId(Long.parseLong(set.getString("positionId")));
                dto.setSalary(set.getString("salary"));
                List<Address> adds = addDao.selectRecordsWithFieldValues("persondataId", dto.getPersondataId());
                dto.setAddresses(adds);

                DepartmentDao depDao = new DepartmentDao();
                List<Department> deps = depDao.selectAll();
                Department d = getEmpDepartment(deps, set.getString("departmentId"));
                if(d == null){
                    dto.setDepartmentId(Long.parseLong("-1"));
                }else{
                    dto.setDepartmentId(d.getId());
                }
                Card card = getCard(cards, dto.getId()+"");
                if(card != null) {
                    dto.setCardId(card.getId());
                } else {
                    dto.setCardId(-1L);
                }                
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
    
    private PersonData getPersonData(String personDataId, List<PersonData> persons) {
        for (PersonData p : persons) {
            if(p.getId()==Long.parseLong(personDataId)) {
                return p;
            }
        }
        return null;
    }
    
    private Card getCard(List<Card> cards, String empId) {
        for (Card c : cards) {
            if(c.getEmployeeId().equals(empId)) {
                return c;
            }
        }
        return null;
    }
}
