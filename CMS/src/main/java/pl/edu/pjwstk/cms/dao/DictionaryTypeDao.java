package pl.edu.pjwstk.cms.dao;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
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

}
