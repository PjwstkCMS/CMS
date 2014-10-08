package dao;

import java.util.logging.Logger;
import model.Terminal;

/**
 *
 * @author Macha
 */
public class TerminalDao extends GenericDao<Terminal>{
    
    private final static Logger LOGGER = Logger.getLogger(TerminalDao.class.getName()); 

    public TerminalDao() {
        super(Terminal.class);
    }

}
