
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
import pl.edu.pjwstk.cms.models.Employee;
/**
 *
 * @author Konrad
 */
public class CompanyDao extends GenericDao<Company>{
    
    private final static Logger LOGGER = Logger.getLogger(CompanyDao.class.getName()); 

    public CompanyDao() {
        super(Company.class);
    }
    public List<CompanyDto> getCompanyDtoList() {
        return getCompanyDtoList(new HashMap<String, List<String>>());
    }
    
    public List<CompanyDto> getCompanyDtoList(Map<String, List<String>> params) {
        String query = "SELECT com.name as name, com.id as id ";
        query += "FROM company as com ";
        if(!params.isEmpty()) {
            query += "WHERE";
            query = this.addParamConditions(query, params);
        }
        ResultSet set = this.connectionManager.select(query);
        List<CompanyDto> comDtos = new ArrayList<>();
        try {
            while(set.next()) {
                CompanyDto dto = new CompanyDto();
                dto.setId(set.getLong("id"));
                dto.setName(set.getString("name"));
                //dto.setSurname(set.getString("surname"));
               /* AddressDao addDao = new AddressDao();
                List<Address> adds = addDao.selectAll();
                Address a = getComAddress(adds, set.getString("addressId"));
                dto.setAddressId(a.getCountry());*/
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
            if(c.getId() == Long.parseLong(id)) {
                return c;
            }
        }
        return null;
    }
    
   
}
