package pl.edu.pjwstk.cms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.dto.ContractDto;
import pl.edu.pjwstk.cms.models.Archive;
import pl.edu.pjwstk.cms.models.Contract;
import pl.edu.pjwstk.cms.models.Customer;
import pl.edu.pjwstk.cms.models.Employee;
import pl.edu.pjwstk.cms.models.PersonData;


public class ContractDao extends GenericDao<Contract>{
    
    private final static Logger LOGGER = Logger.getLogger(ContractDao.class.getName()); 

    public ContractDao() {
        super(Contract.class);
    }
    public List<ContractDto> getContractDtoList(boolean archive) {
        return getContractDtoList(new HashMap<String, List<String>>(),archive);
    }
    
    public List<ContractDto> getContractDtoList(Map<String, List<String>> params, boolean archive) {
        String query = "SELECT id, employeeId, customerId, startDate, closeDate, finalisationDate, description, price ";
        query += "FROM contract as con ";
        query += "WHERE ";
        if(!params.isEmpty()) {        
            query = this.addParamConditions(query, params);
            query += " AND ";
        }        
        if(archive) {
            query+= "id IN (SELECT customerId FROM archive) ";
        } else {
            query+= "id NOT IN (SELECT customerId FROM archive) ";
        }
        ResultSet set = this.connectionManager.select(query);
        List<ContractDto> conDtos = new ArrayList<>();
        
        CustomerDao cusDao = new CustomerDao();
        List<Customer> custs = cusDao.selectAll();
        
        EmployeeDao empDao = new EmployeeDao();
        List<Employee> emps = empDao.selectAll();
        
        PersonDataDao perDao = new PersonDataDao();
        List<PersonData> persons = perDao.selectAll();
                
        try {
            while(set.next()) {
                ContractDto dto = new ContractDto();
                dto.setId(set.getLong("id"));
                dto.setStartDate(set.getString("startDate"));
                dto.setCloseDate(set.getString("closeDate"));
                dto.setFinalisationDate(set.getString("finalisationDate"));
                dto.setDescription(set.getString("description"));
                dto.setPrice(set.getString("price"));
                Customer c = getConCustomer(custs, set.getString("customerId"));
                dto.setCustomerId(c.getId());
                Employee e = getConEmployee(emps, set.getString("employeeId"));
                dto.setEmployeeId(e.getId());
                PersonData per = getPersonData(persons, c.getPersondataId());
                dto.setCustomer(per.getForename() + " " + per.getSurname());
                per = getPersonData(persons, e.getPersondataId());
                dto.setEmployee(per.getForename() + " " + per.getSurname());
                
                conDtos.add(dto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return conDtos;
    }
    private Customer getConCustomer(List<Customer> customers, String id) {
        for (Customer c : customers) {
            if(c.getId() == Long.parseLong(id)) {
                return c;
            }
        }
        return null;
    }
    
    private Employee getConEmployee(List<Employee> employees, String id) {
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
    
    public boolean archive(Contract contract) {
        Archive ar = new Archive();
        ArchiveDao arDao = new ArchiveDao();
        ar.setContractId(contract.getId()+"");
        return (arDao.insert(ar)>0);
    }
    
}
