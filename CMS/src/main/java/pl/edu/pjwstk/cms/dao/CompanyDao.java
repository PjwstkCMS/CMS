
package pl.edu.pjwstk.cms.dao;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.models.Company;
/**
 *
 * @author Konrad
 */
public class CompanyDao extends GenericDao<Company>{
    
    private final static Logger LOGGER = Logger.getLogger(CompanyDao.class.getName()); 

    public CompanyDao() {
        super(Company.class);
    }
}
