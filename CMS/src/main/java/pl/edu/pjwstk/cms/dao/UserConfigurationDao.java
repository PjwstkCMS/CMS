package pl.edu.pjwstk.cms.dao;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.models.UserConfiguration;

/**
 *
 * @author Macha
 */
public class UserConfigurationDao extends GenericDao<UserConfiguration>{
    
    private final static Logger LOGGER = Logger.getLogger(UserConfigurationDao.class.getName()); 

    public UserConfigurationDao() {
        super(UserConfiguration.class);
    }

}
