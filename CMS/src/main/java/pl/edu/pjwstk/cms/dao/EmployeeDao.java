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
import pl.edu.pjwstk.cms.models.Position;


public class EmployeeDao extends GenericDao<Employee>{
     
    private final static Logger LOGGER = Logger.getLogger(EmployeeDao.class.getName()); 

    public EmployeeDao() {
        super(Employee.class);
    }
    public List<EmployeeDto> getEmployeeDtoList() {
        return getEmployeeDtoList(-1L);
    }
    
    public List<EmployeeDto> getManagerEmployeesDtoList(Long managerId){
        return getEmployeeDtoList(managerId);
    }
    
    private List<EmployeeDto> getEmployeeDtoList(Long manager) {
        String query = "SELECT emp.departmentId as departmentId, emp.id as id, emp.persondataId as persondataId, "
                + "emp.positionId as positionId, emp.salary as salary ";
        query += "FROM employee as emp ";
        
        DepartmentDao depDao = new DepartmentDao();
        List<Department> deps = depDao.selectAll();
        
        List<Department> managDep = getManagerDepartments(deps, manager+"");
        if(managDep.size() > 0){
            query +="WHERE";
            for(int i=0; i<managDep.size();i++){
                if (i < managDep.size() && i > 0) {
                    query += " OR ";
                } else {
                    query += " ";
                }
                query += "departmentId='" + managDep.get(i) + "'";
            
            }
            
        }
        
        ResultSet set = this.connectionManager.select(query);
        PersonDataDao personDao = new PersonDataDao();
        CardDao carDao = new CardDao();
        AddressDao addDao = new AddressDao();
        List<EmployeeDto> empDtos = new ArrayList<>();
        List<PersonData> persons = personDao.selectAll();
        
        
        
        PositionDao posDao = new PositionDao();
        List<Position> pos = posDao.selectAll();
        
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
                
                dto.setSalary(set.getString("salary"));
                List<Address> adds = addDao.selectRecordsWithFieldValues("persondataId", dto.getPersondataId());
                dto.setAddresses(adds);

                Department d = getEmpDepartment(deps, set.getString("departmentId"));
                dto.setDepartmentId(d.getId());
                dto.setDepartment(d.getName());
                
                Position p = getEmpPosition(pos, set.getString("positionId"));
                dto.setPositionId(p.getId());
                dto.setPosition(p.getName());
                
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
    
    private Position getEmpPosition(List<Position> positions, String id) {
        for (Position c : positions) {
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
    
    private List<Department> getManagerDepartments(List<Department> departments, String managerId) {
        List<Department> dep = new ArrayList();
        for (Department c : departments) {
            if(Long.parseLong(c.getManagerId())== Long.parseLong(managerId)) {
                dep.add(c);
            }
        }
        return dep;
    }
}
