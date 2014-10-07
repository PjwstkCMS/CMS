package pl.edu.pjwstk.cms.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.models.Privilege;
import pl.edu.pjwstk.cms.models.PrivilegeKey;

/**
 *
 * @author Sergio
 */
public class PrivilegeKeyDao extends GenericDao<PrivilegeKey>{
    
    private final static Logger LOGGER = Logger.getLogger(PrivilegeKeyDao.class.getName()); 

    public PrivilegeKeyDao() {
        super(PrivilegeKey.class);
    }
    
    public List<PrivilegeKey> getKeysForGroup(String groupId) {
        List<PrivilegeKey> keys;
        PrivilegeDao priDao = new PrivilegeDao();
        List<Privilege> privs = priDao.selectRecordsWithFieldValues("groupId", groupId);
        List<String> keyIds = new ArrayList<>();
        for (Privilege p : privs) {
            keyIds.add(p.getKeyId());
        }
        PrivilegeKeyDao keyDao = new PrivilegeKeyDao();
        keys = keyDao.selectRecordsWithFieldValueForStrings("id", keyIds);
        return keys;
    }
    
    public List<String> getPrivilegeKeyCodesForGroup(String groupId) {
        List<PrivilegeKey> keys = getKeysForGroup(groupId);
        List<String> codes = new ArrayList<>();
        for (PrivilegeKey key : keys) {
            codes.add(key.getCode());
        }
        return codes;
    }
}
