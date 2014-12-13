package pl.edu.pjwstk.cms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.models.Dictionary;


public class DictionaryDao extends GenericDao<Dictionary>{
    
    private final static Logger LOGGER = Logger.getLogger(DictionaryDao.class.getName()); 

    public DictionaryDao() {
        super(Dictionary.class);
    }
    
    public List<Dictionary> getCompanyAddressesTypes() {
        return getDictionaryTypeContent("3");
    }
    
    public List<Dictionary> getPersonAddressesTypes() {
        return getDictionaryTypeContent("2");
    }
    
    public List<Dictionary> getEmploymentTypes() {
        return getDictionaryTypeContent("1");
    }
    
    public List<Dictionary> getTaskPriorityTypes(){
        return getDictionaryTypeContent("4");
    }
    
    public List<Dictionary> getDictionaryTypeContent(String type) {
        String query = "SELECT id, value, dictTypeId, description ";
        query += "FROM dictionary WHERE dictTypeId = ";
        query += type;
        
        ResultSet set = this.connectionManager.select(query);
        List<Dictionary> dicts = new ArrayList<>();
        try {
            while (set.next()) {
                Dictionary dto = new Dictionary();
                dto.setId(set.getLong("id"));
                dto.setValue(set.getString("value"));
                dto.setDictTypeId(set.getString("dictTypeId"));
                dto.setDescription(set.getString("description"));
                dicts.add(dto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return dicts;
    }

}
