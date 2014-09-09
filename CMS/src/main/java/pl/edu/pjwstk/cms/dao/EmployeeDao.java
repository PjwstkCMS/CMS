
package pl.edu.pjwstk.cms.dao;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.models.Employee;
/**
 *
 * @author Konrad
 */
public class EmployeeDao extends GenericDao<Employee>{
     
    private final static Logger LOGGER = Logger.getLogger(EmployeeDao.class.getName()); 

    public EmployeeDao() {
        super(Employee.class);
    }
}
