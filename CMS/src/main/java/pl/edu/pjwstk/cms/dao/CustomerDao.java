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
import pl.edu.pjwstk.cms.models.Archive;
import pl.edu.pjwstk.cms.models.Customer;
import pl.edu.pjwstk.cms.models.Employee;
import pl.edu.pjwstk.cms.models.PersonData;


public class CustomerDao extends GenericDao<Customer> {

    private final static Logger LOGGER = Logger.getLogger(CustomerDao.class.getName());

    public CustomerDao() {
        super(Customer.class);
    }

    public List<CustomerDto> getCustomerDtoList(boolean archive) {
        return getCustomerDtoList(new HashMap<String, List<String>>(),archive);
    }

    public List<CustomerDto> getCustomerDtoList(Map<String, List<String>> params, boolean archive) {
        String query = "SELECT cus.id as id, cus.companyId as companyId, cus.persondataId as persondataId ";
        query += "FROM customer as cus ";
        query += "WHERE ";
        if (!params.isEmpty()) {            
            query = this.addParamConditions(query, params);
            query += " AND ";
        }
        if(archive) {
            query+= "cus.id IN (SELECT customerId FROM archive) ";
        } else {
            query+= "cus.id NOT IN (SELECT customerId FROM archive) ";
        }        
        ResultSet set = connectionManager.select(query);
        PersonDataDao personDao = new PersonDataDao();
        List<CustomerDto> cusDtos = new ArrayList<>();
        List<PersonData> persons = personDao.selectAll();
        try {
            while (set.next()) {
                CustomerDto dto = new CustomerDto();
                PersonData person = getPersonData(set.getString("persondataId"), persons);
                dto.setPersondataId(Long.parseLong(set.getString("persondataId")));
                dto.setId(set.getLong("id"));
                dto.setForename(person.getForename());
                dto.setSurname(person.getSurname());
                dto.setEmail(person.getEmail());
                dto.setPhone(person.getPhone());
                dto.setCompanyId(Long.parseLong(set.getString("companyId")));
                cusDtos.add(dto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return cusDtos;
    }

    public CustomerDto getCustomerDtoById(String id, boolean archive) {
        Map<String, List<String>> params = new HashMap<String, List<String>>();
        List<String> values = new ArrayList<>();
        values.add(id);
        params.put("id", values);
        return getCustomerDtoList(params,archive).get(0);
    }

    public CustomerDto getCustomerDtoById(Long id, boolean archive) {
        Map<String, List<String>> params = new HashMap<String, List<String>>();
        List<String> values = new ArrayList<>();
        values.add(id + "");
        params.put("id", values);
        return getCustomerDtoList(params, archive).get(0);
    }
    
    private PersonData getPersonData(String id, List<PersonData> persons) {
        for (PersonData p : persons) {
            if(p.getId()==Long.parseLong(id)) {
                return p;
            }
        }
        return null;
    }
    
    public boolean archive(Customer customer) {
        Archive ar = new Archive();
        ArchiveDao arDao = new ArchiveDao();
        ar.setCustomerId(customer.getId()+"");
        return (arDao.insert(ar)>0);
    }
}
