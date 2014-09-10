package pl.edu.pjwstk.cms.dao;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.models.PrivilegeKey;

/**
 *
 * @author Sergio
 */
public class PrivilegeKeyDao extends GenericDao<PrivilegeKey>{
    
    private final static Logger LOGGER = Logger.getLogger(PrivilegeKeyDao.class.getName()); 

    public PrivilegeKeyDao() {
        super(PrivilegeKey.class);
    }
}
