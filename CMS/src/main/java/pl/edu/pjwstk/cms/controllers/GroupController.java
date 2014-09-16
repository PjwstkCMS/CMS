package pl.edu.pjwstk.cms.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.pjwstk.cms.controllers.general.BaseController;
import pl.edu.pjwstk.cms.dao.PrivilegeDao;
import pl.edu.pjwstk.cms.dao.PrivilegeGroupDao;
import pl.edu.pjwstk.cms.dao.PrivilegeKeyDao;
import pl.edu.pjwstk.cms.dao.UserConfigurationDao;
import pl.edu.pjwstk.cms.dto.GroupDto;
import pl.edu.pjwstk.cms.models.Privilege;
import pl.edu.pjwstk.cms.models.PrivilegeGroup;
import pl.edu.pjwstk.cms.models.PrivilegeKey;
import pl.edu.pjwstk.cms.utils.Utils;
/**
 *
 * @author Konrad
 */
@Controller
public class GroupController extends BaseController {

    private final static Logger LOGGER = Logger.getLogger(GroupController.class.getName());

    public GroupController() {

    }

    @Override
    @RequestMapping("groupList")
    protected ModelAndView home(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ModelAndView model = new ModelAndView("groupList");
        model.addObject("msg", "HelloGuestController");
        
        return model;
    }
    
    @RequestMapping("/groupList/groups")
    @ResponseBody
    public ResponseEntity<String> getData(HttpSession session, ModelMap model) {
        PrivilegeGroupDao groupDao = new PrivilegeGroupDao();
        List<PrivilegeGroup> list = groupDao.selectAll();
        List<GroupDto> groups = new ArrayList<>();
        for (PrivilegeGroup g : list) {
            groups.add(new GroupDto(g));
        }

        PrivilegeKeyDao privKeyDao = new PrivilegeKeyDao();
        List<PrivilegeKey> privKeys = privKeyDao.selectAll();

        Map<String, Object> initData = new HashMap<>();
        initData.put("groups", groups);
        initData.put("privilegeKeys", privKeys);
        return Utils.createResponseEntity(session, initData);
    }
    
    @RequestMapping(value = "/groupList/save/:object", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> saveData(@RequestBody String object, HttpSession session) {
        GroupDto dto = (GroupDto) Utils.convertJSONStringToObject(object, "object", GroupDto.class);
        if (dto != null) {
            PrivilegeGroup actualGroup = new PrivilegeGroup();
            PrivilegeGroupDao groupDao = new PrivilegeGroupDao();
            
            PrivilegeDao privilegeDao = new PrivilegeDao();
            if (dto.getId() != null) {
                actualGroup = groupDao.selectRecordsWithFieldValues("id", dto.getId()).get(0);
                actualGroup.setName(dto.getName());

                groupDao.update("id="+actualGroup.getId(), "name='"+actualGroup.getName()+"'");
                
                privilegeDao.delete("groupId=" + actualGroup.getId());
            } else {
                actualGroup.setName(dto.getName());
                Long id = groupDao.insert(actualGroup);
                actualGroup.setId(id);
            }
            if (dto.getPrivilegeKeyIds() != null) {
                for (Long keyId : dto.getPrivilegeKeyIds()) {
                    Privilege p = new Privilege();
                    p.setGroupId(actualGroup.getId() + "");
                    p.setKeyId(keyId + "");
                    privilegeDao.insert(p);
                }
            }
            Map<String, Object> data = new HashMap<>();
            data.put("id", actualGroup.getId());
            return Utils.createResponseEntity(session, data);

        }
        return null;

    }

    @RequestMapping(value = "/groupList/delete/:object", method = RequestMethod.POST)
    public @ResponseBody
    void deleteData(@RequestBody String object) {
        System.out.println("delete");
        GroupDto dto = (GroupDto) Utils.convertJSONStringToObject(object, "object", GroupDto.class);
        if (dto != null) {
            PrivilegeGroupDao privilegeGroupDao = new PrivilegeGroupDao();
            UserConfigurationDao userConfigDao = new UserConfigurationDao();
            PrivilegeDao privilegeDao = new PrivilegeDao();
            privilegeDao.delete("groupId=" + dto.getId());
            privilegeGroupDao.delete("id=" + dto.getId());
            userConfigDao.updateFieldForAllElementsWithId(
                    "groupId", dto.getId() + "",
                    "groupId", null);

        }

    }
    
}
