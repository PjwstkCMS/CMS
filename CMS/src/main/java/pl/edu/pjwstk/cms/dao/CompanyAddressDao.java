
package pl.edu.pjwstk.cms.dao;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.models.CompanyAddress;

/**
 *
 * @author Konrad
 */
public class CompanyAddressDao extends GenericDao<CompanyAddress>{
    
    private final static Logger LOGGER = Logger.getLogger(CompanyAddressDao.class.getName()); 

    public CompanyAddressDao() {
        super(CompanyAddress.class);
    }
}
