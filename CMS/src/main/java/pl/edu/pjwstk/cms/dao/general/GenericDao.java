package pl.edu.pjwstk.cms.dao.general;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import pl.edu.pjwstk.cms.models.general.DatabaseObject;
import pl.edu.pjwstk.cms.utils.ConnectionManager;

/**
 *
 * @author Sergio
 * @param <T>
 */
public class GenericDao<T extends DatabaseObject> {
    
    private final static Logger LOGGER = Logger.getLogger(GenericDao.class.getName()); 
    private final ConnectionManager connectionManager;
    private final Class modelClass;

    public GenericDao(Class c) {
        connectionManager = ConnectionManager.getConnectionManager();
        modelClass = c;        
    }
}
