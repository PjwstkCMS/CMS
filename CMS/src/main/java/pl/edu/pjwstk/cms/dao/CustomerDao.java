package pl.edu.pjwstk.cms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.dto.CustomerDto;
import pl.edu.pjwstk.cms.models.Customer;

/**
 *
 * @author Sergio
 */
public class CustomerDao extends GenericDao<Customer>{
    
    private final static Logger LOGGER = Logger.getLogger(CustomerDao.class.getName()); 

    public CustomerDao() {
        super(Customer.class);
    }
    
     public List<CustomerDto> getCustomerDtoList() {
        return getCustomerDtoList(new HashMap<String, List<String>>());
    }
    
    public List<CustomerDto> getCustomerDtoList(Map<String, List<String>> params) {
        String query = "SELECT cus.name as name, cus.surname as surname, cus.id as id ";
        query += "FROM customer as cus ";
        if(!params.isEmpty()) {
            query += "WHERE";
            query = this.addParamConditions(query, params);
        }
        //ResultSet set =this.selectForQuery(query);
        ResultSet set = this.connectionManager.select(query);
        List<CustomerDto> cusDtos = new ArrayList<>();
        //ResultSet set2 = ConnectionManager.getConnectionManager().select("Select * from user");
       // AddressDao addressDao = new AddressDao();
       // CompanyDao comDao = new CompanyDao();
        //List<Address> addrs = addressDao.select();
        //List<Company> comps = comDao.select();
        int b = 3;
        System.out.println(" ");
        try {
            while(set.next()) {
                CustomerDto dto = new CustomerDto();
                dto.setId(set.getLong("id"));
                dto.setName(set.getString("name"));
                dto.setSurname(set.getString("surname"));
              //  Company c = getCusCompany(comps, set.getString("companyId"));
              //  dto.setCompanyName(c.getName());
                
               /* Address a = getComAddress(addrs, c.getAddressId());
                dto.setApartmentNumber(a.getApartmentNumber());
                dto.setCountry(a.getCountry());
                dto.setCity(a.getCity());
                dto.setStreetName(a.getStreetName());
                dto.setStreetNumber(a.getStreetNumber());
                dto.setPostalCode(a.getPostalCode());
                cusDtos.add(dto);*/
                cusDtos.add(dto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        int a = 2;
        return cusDtos;
    }
    
    public CustomerDto getCustomerDtoById(String id) {
        Map<String, List<String>> params = new HashMap<String, List<String>>();
        List<String> values = new ArrayList<>();
        values.add(id);
        params.put("id", values);
        return getCustomerDtoList(params).get(0);
    }
    
    public CustomerDto getCustomerDtoById(Long id) {
        Map<String, List<String>> params = new HashMap<String, List<String>>();
        List<String> values = new ArrayList<>();
        values.add(id+"");
        params.put("id", values);
        return getCustomerDtoList(params).get(0);
    }
}
