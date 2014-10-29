package pl.edu.pjwstk.cms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.dto.EmploymentDto;
import pl.edu.pjwstk.cms.models.Dictionary;
import pl.edu.pjwstk.cms.models.Employee;
import pl.edu.pjwstk.cms.models.Employment;
import pl.edu.pjwstk.cms.models.PersonData;


public class EmploymentDao extends GenericDao<Employment>{
    
    private final static Logger LOGGER = Logger.getLogger(EmploymentDao.class.getName()); 

    public EmploymentDao() {
        super(Employment.class);
    }
    
    public List<EmploymentDto> getEmploymentList() {
        return getEmploymentList(new HashMap<String, List<String>>());
    }
    
    public List<EmploymentDto> getEmploymentList(Map<String, List<String>> params) {
        String query = "SELECT emp.employeeId as employeeId, emp.id as id, emp.dictId as dictId, "
                + "emp.dateFrom as dateFrom, emp.dateTo as dateTo ";
        query += "FROM employment as emp ";
        if(!params.isEmpty()) {
            query += "WHERE";
            query = this.addParamConditions(query, params);
        }
        ResultSet set = this.connectionManager.select(query);
        PersonDataDao personDao = new PersonDataDao();
        List<PersonData> persons = personDao.selectAll();
        DictionaryDao dictDao = new DictionaryDao();
        List<Dictionary> dictionaries = dictDao.selectAll();
        EmployeeDao empDao = new EmployeeDao();
        List<Employee> employees = empDao.selectAll();
        
        
        
        List<EmploymentDto> empDtos = new ArrayList<>();
        try {
            while(set.next()) {
                EmploymentDto dto = new EmploymentDto();
                dto.setId(set.getLong("id"));
                dto.setDateFrom(set.getString("dateFrom"));
                dto.setDateTo(set.getString("dateTo"));
                
                Employee e = getEmployee(employees, set.getString("employeeId"));
                dto.setEmployeeId(e.getId());
                PersonData person = getPersonData(e.getPersondataId(), persons);
                dto.setEmployee(person.getForename() + " " + person.getSurname());
                
                Dictionary d = getDictionary(dictionaries, set.getString("dictId"));
                dto.setDictId(d.getId());
                dto.setDict(d.getDescription());
                             
                empDtos.add(dto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return empDtos;
    }
    
    private Employee getEmployee(List<Employee> employee, String id) {
        for (Employee c : employee) {
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
    
    private Dictionary getDictionary(List<Dictionary> dict, String id) {
        for (Dictionary p : dict) {
            if(p.getId()==Long.parseLong(id)) {
                return p;
            }
        }
        return null;
    }

}
