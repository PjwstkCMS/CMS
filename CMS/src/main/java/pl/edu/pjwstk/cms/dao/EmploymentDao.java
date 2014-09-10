package pl.edu.pjwstk.cms.dao;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.models.Employment;

/**
 *
 * @author Macha
 */
public class EmploymentDao extends GenericDao<Employment>{
    
    private final static Logger LOGGER = Logger.getLogger(EmploymentDao.class.getName()); 

    public EmploymentDao() {
        super(Employment.class);
    }

}
