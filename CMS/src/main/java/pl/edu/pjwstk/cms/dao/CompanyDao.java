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
import pl.edu.pjwstk.cms.models.Archive;
import pl.edu.pjwstk.cms.models.Company;
import pl.edu.pjwstk.cms.models.PersonData;


public class CompanyDao extends GenericDao<Company> {

    private final static Logger LOGGER = Logger.getLogger(CompanyDao.class.getName());

    public CompanyDao() {
        super(Company.class);
    }

    public List<CompanyDto> getCompanyDtoList(boolean archive) {
        return getCompanyDtoList(new HashMap<String, List<String>>(),archive);
    }

    public List<CompanyDto> getCompanyDtoListOld(Map<String, List<String>> params, boolean archive) {
        String query = "SELECT com.name as name, com.id as id, com.contactpersonId as contactpersonId ";
        query += "FROM company as com ";
        query += "WHERE ";
        if (!params.isEmpty()) {            
            query = this.addParamConditions(query, params);
            query += " AND ";
        }        
        if(archive) {
            query+= "com.id IN (SELECT companyId FROM archive) ";
        } else {
            query+= "com.id NOT IN (SELECT companyId FROM archive) ";
        }
        ResultSet set = this.connectionManager.select(query);
        List<CompanyDto> comDtos = new ArrayList<>();
        try {
            while (set.next()) {
                CompanyDto dto = new CompanyDto();
                dto.setId(set.getLong("id"));
                dto.setName(set.getString("name"));
                dto.setContactPersonId(Long.parseLong(set.getString("contactpersonId")));
                if(dto.getContactPersonId()!=Long.parseLong("-1")){
                    PersonData per = new PersonDataDao().selectRecordsWithFieldValues("id", dto.getContactPersonId()).get(0);
                    dto.setForename(per.getForename());
                    dto.setSurname(per.getSurname());
                    dto.setEmail(per.getEmail());
                    dto.setPhone(per.getPhone());
                }
                AddressDao addDao = new AddressDao();
                List<Address> adds = addDao.selectRecordsWithFieldValues("companyId", dto.getId());
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
        return comDtos;
    }
    
    public List<CompanyDto> getCompanyDtoList(Map<String, List<String>> params, boolean archive) {
        String query = "SELECT com.name as name, com.id as id, com.contactpersonId as contactpersonId, "
                + "per.forename as forename, per.surname as surname, per.phone as phone, "
                + "per.email as email, per.pesel as pesel ";
        query += "FROM company as com, persondata as per ";
        query += "WHERE per.id=contactpersonId AND ";
        if (!params.isEmpty()) {            
            query = this.addParamConditions(query, params);
            query += " AND ";
        }        
        if(archive) {
            query+= "com.id IN (SELECT companyId FROM archive) ";
        } else {
            query+= "com.id NOT IN (SELECT companyId FROM archive) ";
        }
        ResultSet set = this.connectionManager.select(query);
        List<CompanyDto> comDtos = new ArrayList<>();
        AddressDao addDao = new AddressDao();
        List<Address> adds = addDao.selectForQuery("SELECT * FROM address WHERE companyId!=-1");
        try {
            while (set.next()) {
                CompanyDto dto = new CompanyDto();
                dto.setId(set.getLong("id"));
                dto.setName(set.getString("name"));
                dto.setContactPersonId(Long.parseLong(set.getString("contactpersonId")));
                dto.setForename(set.getString("forename"));
                dto.setSurname(set.getString("surname"));
                dto.setEmail(set.getString("email"));
                dto.setPhone(set.getString("phone"));     
                List<Address> as = new ArrayList<>();
                for (Address a : adds) {
                    if(a.getCompanyId().equals(dto.getId())) {
                        as.add(a);
                    }
                }
                //Ustawianie, aby pierwszy adres na listy to był adres głównej siedziby
                if (as.size() > 1 &&
                        !as.get(0).getDictId().equals("4")) {
                    for (Address a : as) {
                        if(a.getDictId().equals("4")) {
                            as.set(0, a);
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
        return comDtos;
    }
    
    public boolean archive(Company company) {
        Archive ar = new Archive();
        ArchiveDao arDao = new ArchiveDao();
        ar.setCompanyId(company.getId()+"");
        return (arDao.insert(ar)>0);
    }

}
