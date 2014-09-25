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
import pl.edu.pjwstk.cms.models.Customer;
import pl.edu.pjwstk.cms.models.PersonData;

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
        String query = "SELECT cus.id as id, cus.companyId as companyId, cus.persondataId as persondataId ";
        query += "FROM customer as cus ";
        if (!params.isEmpty()) {
            query += "WHERE";
            query = this.addParamConditions(query, params);
        }
        ResultSet set = connectionManager.select(query);
        CompanyDao comDao = new CompanyDao();
        PersonDataDao personDao = new PersonDataDao();
        List<CustomerDto> cusDtos = new ArrayList<>();
        List<CompanyDto> comDtos = comDao.getCompanyDtoList();
        List<PersonData> persons = personDao.selectAll();
        try {
            while (set.next()) {
                CustomerDto dto = new CustomerDto();
                PersonData person = getPersonData(set.getString("persondataId"), persons);
                dto.setPersondataId(Long.parseLong(set.getString("persondataId")));
                dto.setId(set.getLong("id"));
                dto.setName(person.getName());
                dto.setSurname(person.getSurname());
                dto.setEmail(person.getEmail());
                dto.setPhone(person.getPesel());
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
    
    private PersonData getPersonData(String id, List<PersonData> persons) {
        for (PersonData p : persons) {
            if(p.getId()==Long.parseLong(id)) {
                return p;
            }
        }
        return null;
    }
}
