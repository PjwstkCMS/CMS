/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.pjwstk.cms.controllers.general.BaseController;
import pl.edu.pjwstk.cms.dao.EmployeeDao;
import pl.edu.pjwstk.cms.dao.PrivilegeGroupDao;
import pl.edu.pjwstk.cms.dao.UserDao;
import pl.edu.pjwstk.cms.utils.Utils;
/**
 *
 * @author Konrad
 */
@Controller
public class UserController extends BaseController {

    private final static Logger LOGGER = Logger.getLogger(UserController.class.getName());

    public UserController() {

    }

    @Override
    @RequestMapping("userList")
    protected ModelAndView home(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

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
        initData.put("users", userDao.getUserWithConfig());
        initData.put("employees", empDao.getEmployeeDtoList());
        initData.put("groups", groupDao.selectAll());
        //List<UserDTO> userDtos = userDao.getUserWithConfig();
        return Utils.createResponseEntity(session, initData);
    }
}
