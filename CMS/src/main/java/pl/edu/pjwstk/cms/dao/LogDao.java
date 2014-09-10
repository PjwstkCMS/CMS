package pl.edu.pjwstk.cms.dao;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.models.Log;

/**
 *
 * @author Macha
 */
public class LogDao extends GenericDao<Log>{
    
    private final static Logger LOGGER = Logger.getLogger(LogDao.class.getName()); 

    public LogDao() {
        super(Log.class);
    }

}
