package pl.edu.pjwstk.cms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.dto.TaskDto;
import pl.edu.pjwstk.cms.models.Dictionary;
import pl.edu.pjwstk.cms.models.Employee;
import pl.edu.pjwstk.cms.models.PersonData;
import pl.edu.pjwstk.cms.models.Task;


public class TaskDao extends GenericDao<Task>{
    
    private final static Logger LOGGER = Logger.getLogger(TaskDao.class.getName()); 

    public TaskDao() {
        super(Task.class);
    }
    public List<TaskDto> getTaskDtoList() {
        return getTaskDtoList("");
    }
    
    public List<TaskDto> getManagerTaskDtoList(Long managerId) {
        return getTaskDtoList("WHERE managerId='"+managerId +"'");
    }
    
    public List<TaskDto> getEmployeeTaskDtoList(Long employeeId){
        return getTaskDtoList("WHERE employeeId='"+employeeId +"'");
    }
    
    public List<TaskDto> getTaskDtoList(String param) {
        String query = "SELECT id, employeeId, managerId, startDate, closeDate, finalisationDate, description, dictId ";
        query += "FROM task as task ";
        
        query +=param;
        
        ResultSet set = this.connectionManager.select(query);
        List<TaskDto> conDtos = new ArrayList<>();
                
        EmployeeDao empDao = new EmployeeDao();
        List<Employee> emps = empDao.selectAll();
        
        PersonDataDao perDao = new PersonDataDao();
        List<PersonData> persons = perDao.selectAll();
        
        DictionaryDao dictDao = new DictionaryDao();
        List<Dictionary> dictionaries = dictDao.selectAll();
                
        try {
            while(set.next()) {
                TaskDto dto = new TaskDto();
                dto.setId(set.getLong("id"));
                dto.setStartDate(set.getString("startDate"));
                dto.setCloseDate(set.getString("closeDate"));
                dto.setFinalisationDate(set.getString("finalisationDate"));
                dto.setDescription(set.getString("description"));
                dto.setManagerId(Long.parseLong(set.getString("managerId")));
                
                Dictionary d = getDictionary(dictionaries, set.getString("dictId"));
                dto.setDictId(d.getId());
                dto.setDict(d.getDescription());
                if(Long.parseLong(set.getString("employeeId"))!=-1){
                    Employee e = getTaskEmployee(emps, set.getString("employeeId"));
                    dto.setEmployeeId(e.getId());
                    PersonData per = getPersonData(persons, e.getPersondataId());
                    dto.setEmployee(per.getForename() + " " + per.getSurname());
                }
                conDtos.add(dto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return conDtos;
    }

    private Employee getTaskEmployee(List<Employee> employees, String id) {
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
    
    private Dictionary getDictionary(List<Dictionary> dict, String id) {
        for (Dictionary p : dict) {
            if(p.getId()==Long.parseLong(id)) {
                return p;
            }
        }
        return null;
    }
    
}
