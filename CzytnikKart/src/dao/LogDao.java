package dao;

import java.util.logging.Logger;
import model.Log;

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
