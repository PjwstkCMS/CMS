package pl.edu.pjwstk.cms.dao;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.models.Department;
/**
 *
 * @author Macha
 */
public class DepartmentDao extends GenericDao<Department>{
    
    private final static Logger LOGGER = Logger.getLogger(DepartmentDao.class.getName()); 

    public DepartmentDao() {
        super(Department.class);
    }
}
