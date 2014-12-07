package pl.edu.pjwstk.cms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.dto.DictionaryTypeDto;
import pl.edu.pjwstk.cms.models.Dictionary;
import pl.edu.pjwstk.cms.models.DictionaryType;

/**
 *
 * @author Macha
 */
public class DictionaryTypeDao extends GenericDao<DictionaryType>{
    
    private final static Logger LOGGER = Logger.getLogger(DictionaryTypeDao.class.getName()); 

    public DictionaryTypeDao() {
        super(DictionaryType.class);
    }

    public List<DictionaryTypeDto> getDictionaryTypeDtos() {
        return getDictionaryTypeDtos(new HashMap<String, List<String>>());
    }
    
    public List<DictionaryTypeDto> getDictionaryTypeDtos(Map<String, List<String>> params) {
        String query = "SELECT id, value, description ";
        query += "FROM dictionarytype ";
        if (!params.isEmpty()) {
            query += "WHERE";
            query = this.addParamConditions(query, params);
        }
        
        ResultSet set = this.connectionManager.select(query);
        List<DictionaryTypeDto> dicts = new ArrayList<>();
        try {
            while (set.next()) {
                DictionaryTypeDto dto = new DictionaryTypeDto();
                dto.setId(set.getLong("id"));
                dto.setValue(set.getString("value"));
                dto.setDescription(set.getString("description"));
                DictionaryDao dictDao = new DictionaryDao();
                List<Dictionary> dictionaries = dictDao.selectRecordsWithFieldValues("dictTypeId", dto.getId());
                dto.setDictionaries(dictionaries);
                dicts.add(dto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return dicts;
    }
}
