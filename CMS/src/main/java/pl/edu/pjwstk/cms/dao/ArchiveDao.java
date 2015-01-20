package pl.edu.pjwstk.cms.dao;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.models.Archive;


public class ArchiveDao extends GenericDao<Archive>{
    
    private final static Logger LOGGER = Logger.getLogger(ArchiveDao.class.getName()); 

    public ArchiveDao() {
        super(Archive.class);
    }
}
