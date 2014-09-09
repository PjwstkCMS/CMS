
package pl.edu.pjwstk.cms.dao;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.models.Contract;
/**
 *
 * @author Konrad
 */
public class ContractDao extends GenericDao<Contract>{
    
    private final static Logger LOGGER = Logger.getLogger(ContractDao.class.getName()); 

    public ContractDao() {
        super(Contract.class);
    }
    
}
