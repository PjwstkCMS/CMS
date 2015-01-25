package pl.edu.pjwstk.cms.controllers.configuration;

import java.util.HashMap;
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
import pl.edu.pjwstk.cms.dao.UserDao;
import pl.edu.pjwstk.cms.dto.PrivilegeGroupDto;
import pl.edu.pjwstk.cms.models.PrivilegeGroup;
import pl.edu.pjwstk.cms.utils.Utils;


@Controller
public class GroupController extends BaseController {

    private final static Logger LOGGER = Logger.getLogger(GroupController.class.getName());

    public GroupController() {
        super("ManageGroups","all");
    }

    @Override
    @RequestMapping("groupList")
    protected ModelAndView home(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if(!checkPrivileges(request)) {
            ModelAndView model = new ModelAndView("accessdenied");
            return model;
        }
        ModelAndView model = new ModelAndView("groupList");
        model.addObject("msg", "HelloGuestController");
        
        return model;
    }
    
    @RequestMapping("/groupList/groups")
    @ResponseBody
    public ResponseEntity<String> getData(HttpSession session, ModelMap model) {
        PrivilegeGroupDao privGroupDao = new PrivilegeGroupDao();
        PrivilegeKeyDao privKeyDao = new PrivilegeKeyDao();
        Map<String, Object> initData = new HashMap<String, Object>();
        initData.put("groups", privGroupDao.getDtoList());
        initData.put("privilegeKeys", privKeyDao.selectAll());
        return Utils.createResponseEntity(session, initData);
    }
    
    @RequestMapping(value = "/groupList/save/:object", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> saveData(@RequestBody String object, HttpSession session) {
        PrivilegeGroupDto privGroupDto = (PrivilegeGroupDto) Utils.convertJSONStringToObject(object, "object", PrivilegeGroupDto.class);
        PrivilegeGroupDao privGroupDao = new PrivilegeGroupDao();
        PrivilegeGroup privGroup = new PrivilegeGroup();
        Map<String, Object> data = new HashMap<>();
        if (privGroupDto != null) {
            privGroup = privGroupDao.selectRecordsWithFieldValues("id", privGroupDto.getId()).get(0);
            privGroup.setName(privGroupDto.getName());
            
            privGroupDao.update(privGroup);
            data.put("id", privGroupDto.getId());
            return Utils.createResponseEntity(session, data);
        } else {
            privGroup.setName(privGroupDto.getName());
            data.put("id", privGroupDao.insert(privGroup));
            return Utils.createResponseEntity(session, data);
        }
    }

    @RequestMapping(value = "/groupList/delete/:object", method = RequestMethod.POST)
    public @ResponseBody
    void deleteData(@RequestBody String object) {
        System.out.println("delete");
        PrivilegeGroupDto dto = (PrivilegeGroupDto) Utils.convertJSONStringToObject(object, "object", PrivilegeGroupDto.class);
        PrivilegeGroupDao privilegeGroupDao = new PrivilegeGroupDao();
        PrivilegeDao privilegeDao = new PrivilegeDao();
        UserDao userDao = new UserDao();
        if (dto != null) {
            privilegeDao.delete("groupId=" + dto.getId());
            privilegeGroupDao.delete("id=" + dto.getId());
            userDao.updateFieldForAllElementsWithId(
                    "groupId", dto.getId() + "",
                    "groupId", null);

        }

    }
    
}
