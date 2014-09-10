package pl.edu.pjwstk.cms.dao;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.models.File;


/**
 *
 * @author Macha
 */
public class FileDao extends GenericDao<File>{
    
    private final static Logger LOGGER = Logger.getLogger(FileDao.class.getName()); 

    public FileDao() {
        super(File.class);
    }

}
