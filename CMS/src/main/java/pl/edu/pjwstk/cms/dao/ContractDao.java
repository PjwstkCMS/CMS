
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
import pl.edu.pjwstk.cms.models.Contract;
import pl.edu.pjwstk.cms.models.Customer;
import pl.edu.pjwstk.cms.models.Employee;
/**
 *
 * @author Konrad
 */
public class ContractDao extends GenericDao<Contract>{
    
    private final static Logger LOGGER = Logger.getLogger(ContractDao.class.getName()); 

    public ContractDao() {
        super(Contract.class);
    }
    public List<ContractDto> getContractDtoList() {
        return getContractDtoList(new HashMap<String, List<String>>());
    }
    
    public List<ContractDto> getContractDtoList(Map<String, List<String>> params) {
        String query = "SELECT con.customerId as customerId, con.employeeId as employeeId, con.id as id ";
        query += "FROM contract as con ";
        if(!params.isEmpty()) {
            query += "WHERE";
            query = this.addParamConditions(query, params);
        }
        ResultSet set = this.connectionManager.select(query);
        List<ContractDto> conDtos = new ArrayList<>();
        try {
            while(set.next()) {
                ContractDto dto = new ContractDto();
                dto.setId(set.getLong("id"));
                //dto.setName(set.getString("name"));
                //dto.setSurname(set.getString("surname"));
                CustomerDao cusDao = new CustomerDao();
                List<Customer> custs = cusDao.selectAll();
                Customer c = getConCustomer(custs, set.getString("customerId"));
                EmployeeDao empDao = new EmployeeDao();
                List<Employee> emps = empDao.selectAll();
                Employee e = getConEmployee(emps, set.getString("employeeId"));
                dto.setCustomerId(c.getId()+"");
                dto.setEmployeeId(e.getId()+"");
              //  Company c = getCusCompany(comps, set.getString("companyId"));
              //  dto.setCompanyName(c.getName());
                
               /* Address a = getComAddress(addrs, c.getAddressId());
                dto.setApartmentNumber(a.getApartmentNumber());
                dto.setCountry(a.getCountry());
                dto.setCity(a.getCity());
                dto.setStreetName(a.getStreetName());
                dto.setStreetNumber(a.getStreetNumber());
                dto.setPostalCode(a.getPostalCode());
                conDtos.add(dto);*/
                conDtos.add(dto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        int a = 2;
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
    
    
}
