package pl.edu.pjwstk.cms.dao;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.models.Address;


public class AddressDao extends GenericDao<Address>{
    
    private final static Logger LOGGER = Logger.getLogger(AddressDao.class.getName()); 

    public AddressDao() {
        super(Address.class);
    }
}
