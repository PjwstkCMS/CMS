package pl.edu.pjwstk.cms.dao;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.models.PrivilegeGroup;

/**
 *
 * @author Sergio
 */
public class PrivilegeGroupDao extends GenericDao<PrivilegeGroup>{
    
    private final static Logger LOGGER = Logger.getLogger(PrivilegeGroupDao.class.getName()); 

    public PrivilegeGroupDao() {
        super(PrivilegeGroup.class);
    }
}
