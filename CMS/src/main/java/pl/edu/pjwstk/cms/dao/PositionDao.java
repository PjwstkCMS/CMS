package pl.edu.pjwstk.cms.dao;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.models.Position;

/**
 *
 * @author Macha
 */
public class PositionDao extends GenericDao<Position>{
    
    private final static Logger LOGGER = Logger.getLogger(PositionDao.class.getName()); 

    public PositionDao() {
        super(Position.class);
    }

}
