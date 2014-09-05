package pl.edu.pjwstk.cms.dao;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.models.User;

/**
 *
 * @author Sergio
 */
public class UserDao extends GenericDao<User>{
    
    private final static Logger LOGGER = Logger.getLogger(UserDao.class.getName()); 

    public UserDao() {
        super(User.class);
    }
}
