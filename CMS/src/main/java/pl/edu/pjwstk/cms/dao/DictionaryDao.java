package pl.edu.pjwstk.cms.dao;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.models.Dictionary;

/**
 *
 * @author Macha
 */
public class DictionaryDao extends GenericDao<Dictionary>{
    
    private final static Logger LOGGER = Logger.getLogger(DictionaryDao.class.getName()); 

    public DictionaryDao() {
        super(Dictionary.class);
    }

}
