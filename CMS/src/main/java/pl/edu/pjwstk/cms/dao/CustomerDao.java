package pl.edu.pjwstk.cms.dao;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.models.Customer;

/**
 *
 * @author Sergio
 */
public class CustomerDao extends GenericDao<Customer>{
    
    private final static Logger LOGGER = Logger.getLogger(CustomerDao.class.getName()); 

    public CustomerDao() {
        super(Customer.class);
    }
}
