package pl.edu.pjwstk.cms.dao;

import java.util.List;
import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.models.SystemConfiguration;


public class SystemConfigurationDao extends GenericDao<SystemConfiguration>{
    
    private final static Logger LOGGER = Logger.getLogger(SystemConfigurationDao.class.getName()); 
    private static long defaultLoginPersistanceTime = 3600;
    private static long degaultIdleTimeout = 1000;
    
    public SystemConfigurationDao() {
        super(SystemConfiguration.class);
    }
    
    public long getLoginPersistanceTime() {
        List<SystemConfiguration> list = this.selectRecordsWithFieldValues("name", "LoginPersistanceTime");
        if(list.isEmpty()) {
            return defaultLoginPersistanceTime;
        } else {
            return Long.parseLong(list.get(0).getValue());
        }
    }
    
    public long getIdleTimeout() {
        List<SystemConfiguration> list = this.selectRecordsWithFieldValues("name", "IdleTimeout");
        if(list.isEmpty()) {
            return degaultIdleTimeout;
        } else {
            return Long.parseLong(list.get(0).getValue());
        }
    }
    

}
