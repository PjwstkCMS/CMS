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
import pl.edu.pjwstk.cms.models.Address;
import pl.edu.pjwstk.cms.models.Company;
import pl.edu.pjwstk.cms.models.Customer;

/**
 *
 * @author Konrad
 */
public class CompanyDao extends GenericDao<Company> {

    private final static Logger LOGGER = Logger.getLogger(CompanyDao.class.getName());

    public CompanyDao() {
        super(Company.class);
    }

    public List<CompanyDto> getCompanyDtoList() {
        return getCompanyDtoList(new HashMap<String, List<String>>());
    }

    public List<CompanyDto> getCompanyDtoList(Map<String, List<String>> params) {
        String query = "SELECT com.name as name, com.id as id, com.contactpersonId as contactpersonId ";
        query += "FROM company as com ";
        if (!params.isEmpty()) {
            query += "WHERE";
            query = this.addParamConditions(query, params);
        }
        ResultSet set = this.connectionManager.select(query);
        List<CompanyDto> comDtos = new ArrayList<>();
        try {
            while (set.next()) {
                CompanyDto dto = new CompanyDto();
                dto.setId(set.getLong("id"));
                dto.setName(set.getString("name"));
                dto.setContactPersonId(Long.parseLong(set.getString("contactpersonId")));
                CustomerDao customerDao = new CustomerDao();
                Customer cus = customerDao.selectRecordsWithFieldValues("companyId", dto.getId()).get(0);
                AddressDao addDao = new AddressDao();
                List<Address> adds = addDao.selectRecordsWithFieldValues("personId", cus.getId());
                //Ustawianie, aby pierwszy adres na listy to był adres głównej siedziby
                if (adds.size() > 1 &&
                        !adds.get(0).getDictId().equals("4")) {
                    for (Address a : adds) {
                        if(a.getDictId().equals("4")) {
                            adds.set(0, a);
                        }
                    }
                }
                dto.setAddresses(adds);
                comDtos.add(dto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        int a = 2;
        return comDtos;
    }

    private Address getComAddress(List<Address> address, String id) {
        for (Address c : address) {
            if (c.getId() == Long.parseLong(id)) {
                return c;
            }
        }
        return null;
    }

}
