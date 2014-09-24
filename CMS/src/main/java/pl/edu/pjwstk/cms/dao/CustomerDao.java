package pl.edu.pjwstk.cms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.dto.CompanyDto;
import pl.edu.pjwstk.cms.dto.CustomerDto;
import pl.edu.pjwstk.cms.models.Address;
import pl.edu.pjwstk.cms.models.Customer;

/**
 *
 * @author Sergio
 */
public class CustomerDao extends GenericDao<Customer> {

    private final static Logger LOGGER = Logger.getLogger(CustomerDao.class.getName());

    public CustomerDao() {
        super(Customer.class);
    }

    public List<CustomerDto> getCustomerDtoList() {
        return getCustomerDtoList(new HashMap<String, List<String>>());
    }

    public List<CustomerDto> getCustomerDtoList(Map<String, List<String>> params) {
        String query = "SELECT cus.name as name, cus.surname as surname, cus.id as id, cus.email as email, cus.phone as phone, cus.companyId as companyId ";
        query += "FROM customer as cus ";
        if (!params.isEmpty()) {
            query += "WHERE";
            query = this.addParamConditions(query, params);
        }
        ResultSet set = connectionManager.select(query);
        CompanyDao comDao = new CompanyDao();
        List<CustomerDto> cusDtos = new ArrayList<>();
        List<CompanyDto> comDtos = comDao.getCompanyDtoList();
        try {
            while (set.next()) {
                CustomerDto dto = new CustomerDto();
                dto.setId(set.getLong("id"));
                dto.setName(set.getString("name"));
                dto.setSurname(set.getString("surname"));
                dto.setEmail(set.getString("email"));
                dto.setPhone(set.getString("phone"));
                dto.setCompanyId(Long.parseLong(set.getString("companyId")));
                cusDtos.add(dto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
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
        values.add(id + "");
        params.put("id", values);
        return getCustomerDtoList(params).get(0);
    }
}
