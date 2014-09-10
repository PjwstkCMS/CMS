package pl.edu.pjwstk.cms.dao;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.models.SystemConfiguration;

/**
 *
 * @author Macha
 */
public class SystemConfigurationDao extends GenericDao<SystemConfiguration>{
    
    private final static Logger LOGGER = Logger.getLogger(SystemConfigurationDao.class.getName()); 

    public SystemConfigurationDao() {
        super(SystemConfiguration.class);
    }

}
