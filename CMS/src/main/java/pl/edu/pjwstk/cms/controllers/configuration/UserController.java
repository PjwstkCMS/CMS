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
import pl.edu.pjwstk.cms.dao.EmployeeDao;
import pl.edu.pjwstk.cms.dao.PrivilegeGroupDao;
import pl.edu.pjwstk.cms.dao.SystemConfigurationDao;
import pl.edu.pjwstk.cms.dao.UserDao;
import pl.edu.pjwstk.cms.dto.UserDto;
import pl.edu.pjwstk.cms.models.SystemConfiguration;
import pl.edu.pjwstk.cms.models.User;
import pl.edu.pjwstk.cms.utils.Utils;


@Controller
public class UserController extends BaseController {

    private final static Logger LOGGER = Logger.getLogger(UserController.class.getName());

    public UserController() {
        super("ManageUsers","all");
    }

    @Override
    @RequestMapping("userList")
    protected ModelAndView home(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if(!checkPrivileges(request)) {
            ModelAndView model = new ModelAndView("accessdenied");
            return model;
        }
        ModelAndView model = new ModelAndView("userList");
        model.addObject("msg", "HelloGuestController");
        
        return model;
    }
    
    @RequestMapping(value = "/userList/users")
    @ResponseBody
    public ResponseEntity<String> getData(HttpSession session, ModelMap model) {
        UserDao userDao = new UserDao();
        EmployeeDao empDao = new EmployeeDao();
        PrivilegeGroupDao groupDao = new PrivilegeGroupDao();
        Map<String, Object> initData = new HashMap<>();
        initData.put("users", userDao.getUserDtos());
        initData.put("employees", empDao.getEmployeeDtoList());
        initData.put("groups", groupDao.selectAll());
        //List<UserDTO> userDtos = userDao.getUserWithConfig();
        return Utils.createResponseEntity(session, initData);
    }
    
    @RequestMapping(value = "/userList/save/:object", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> saveData(@RequestBody String object, HttpSession session) {
        UserDto userDto = (UserDto) Utils.convertJSONStringToObject(object, "object", UserDto.class);
        UserDao userDao = new UserDao();
        User user = new User();
        Map<String, Object> data = new HashMap<>();
            
        user.setEmployeeId(userDto.getEmployeeId()+"");
        user.setGroupId(userDto.getGroupId()+"");
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        data.put("id", userDao.insert(user));
        return Utils.createResponseEntity(session, data);
        
        
    }
    
    @RequestMapping(value = "/userList/resetPass/:object", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> resetPassword(@RequestBody String object, HttpSession session) {
        UserDto userDto = (UserDto) Utils.convertJSONStringToObject(object, "object", UserDto.class);
        UserDao userDao = new UserDao();
        User user = new User();
        Map<String, Object> data = new HashMap<>();
            
        SystemConfigurationDao sysDao = new SystemConfigurationDao();
        
        SystemConfiguration sc = (SystemConfiguration) sysDao.selectSingleRecord("name", "DefaultUserPassword");
        
        user.setPassword(sc.getValue());
            
        userDao.update(user);
        data.put("id", userDto.getId());
        return Utils.createResponseEntity(session, data);
    }
    
     
    @RequestMapping(value = "/userList/delete/:object", method = RequestMethod.POST)
    public @ResponseBody
    void deleteData(@RequestBody String object) {
        System.out.println("delete");
        UserDto dto = (UserDto) Utils.convertJSONStringToObject(object, "object", UserDto.class);
        if (dto != null) {
            UserDao dao = new UserDao();
            dao.delete("id=" + dto.getId());
        }
    }
}

