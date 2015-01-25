package pl.edu.pjwstk.cms.dao;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.models.Terminal;


public class TerminalDao extends GenericDao<Terminal>{
    
    private final static Logger LOGGER = Logger.getLogger(TerminalDao.class.getName()); 

    public TerminalDao() {
        super(Terminal.class);
    }

}
