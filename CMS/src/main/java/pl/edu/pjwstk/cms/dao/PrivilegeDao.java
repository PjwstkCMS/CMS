package pl.edu.pjwstk.cms.dao;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.models.Privilege;

/**
 *
 * @author Sergio
 */
public class PrivilegeDao extends GenericDao<Privilege>{
    
    private final static Logger LOGGER = Logger.getLogger(PrivilegeDao.class.getName()); 

    public PrivilegeDao() {
        super(Privilege.class);
    }
}
