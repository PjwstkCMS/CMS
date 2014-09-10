package pl.edu.pjwstk.cms.dao;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.models.Report;

/**
 *
 * @author Macha
 */
public class ReportDao extends GenericDao<Report>{
  
    private final static Logger LOGGER = Logger.getLogger(ReportDao.class.getName()); 

    public ReportDao() {
        super(Report.class);
    }

}
