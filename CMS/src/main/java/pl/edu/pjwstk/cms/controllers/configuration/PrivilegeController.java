package pl.edu.pjwstk.cms.controllers.configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.edu.pjwstk.cms.dao.PrivilegeDao;
import pl.edu.pjwstk.cms.dto.PrivilegeDto;
import pl.edu.pjwstk.cms.models.Privilege;
import pl.edu.pjwstk.cms.utils.Utils;


@Controller
public class PrivilegeController {

    private final static Logger LOGGER = Logger.getLogger(PrivilegeController.class.getName());

    public PrivilegeController() {

    }
    
    @RequestMapping(value = "/privilege/save/:object", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> saveData(@RequestBody String object, HttpSession session) {
        PrivilegeDto dto = (PrivilegeDto) Utils.convertJSONStringToObject(object, "object", PrivilegeDto.class);
        PrivilegeDao dao = new PrivilegeDao();
        Privilege priv = new Privilege();
        
        Map<String, Object> data = new HashMap<>();
        if(dto.getId() != null ){
            priv = dao.selectRecordsWithFieldValues("id", dto.getId()).get(0);
            priv.setKeyId(dto.getKeyId());
            priv.setGroupId(dto.getGroupId());
            
            dao.update(priv);
            data.put("id", dto.getId());
            return Utils.createResponseEntity(session, data);
        } else {
            priv.setKeyId(dto.getKeyId());
            priv.setGroupId(dto.getGroupId());
            
            data.put("id", dao.insert(priv));
            return Utils.createResponseEntity(session, data);
        }
    }
    
    @RequestMapping(value = "/privilege/delete/:object", method = RequestMethod.POST)
    public @ResponseBody
    void deleteData(@RequestBody String object) {
        System.out.println("delete");
        PrivilegeDto dto = (PrivilegeDto) Utils.convertJSONStringToObject(object, "object", PrivilegeDto.class);
        PrivilegeDao dao = new PrivilegeDao();
        if (dto != null) {
            dao.delete("id=" + dto.getId());
        }
    }
}
