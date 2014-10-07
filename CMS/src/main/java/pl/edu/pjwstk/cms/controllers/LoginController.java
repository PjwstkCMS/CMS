package pl.edu.pjwstk.cms.controllers;


import java.util.List;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.pjwstk.cms.controllers.general.BaseController;
import pl.edu.pjwstk.cms.dao.PrivilegeGroupDao;
import pl.edu.pjwstk.cms.dao.PrivilegeKeyDao;
import pl.edu.pjwstk.cms.dao.UserDao;
import pl.edu.pjwstk.cms.dto.UserDto;
import pl.edu.pjwstk.cms.models.Privilege;
import pl.edu.pjwstk.cms.models.PrivilegeGroup;
import pl.edu.pjwstk.cms.models.User;
/**
 *
 * @author Sergio
 */
@Controller
public class LoginController extends BaseController {

    private final static Logger LOGGER = Logger.getLogger(LoginController.class.getName());

    public LoginController() {

    }    
    
    @RequestMapping("loginPage")
    protected ModelAndView home(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ModelAndView model = new ModelAndView("login");     
        return model;
    }
    
    
    
    @RequestMapping(value = "login", method = RequestMethod.POST)
    protected ModelAndView login(HttpServletRequest request,
            HttpServletResponse response) throws Exception {      
        ModelAndView model = new ModelAndView("home");
        String login = (String)request.getParameter("login");
        String pass = (String)request.getParameter("password");
        LOGGER.info(login +" "+pass);
        UserDao userDao = new UserDao(); 
        List<User> users = userDao.selectRecordsWithFieldValues("login", login);
        if(users.size()>0) {
            User user = users.get(0);
            if(user.getPassword().equals(pass)) {
                UserDto userDto = new UserDto();
                userDto.setForename(login);
                userDto.setPassword(pass);
                userDto.setGroupId(user.getGroupId());
                LOGGER.info(userDto.getForename());
                PrivilegeGroupDao groupDao = new PrivilegeGroupDao();
                PrivilegeGroup group = groupDao.selectRecordsWithFieldValues("id", userDto.getGroupId()).get(0);
                userDto.setGroupName(group.getName());
                PrivilegeKeyDao keyDao = new PrivilegeKeyDao();
                userDto.setPrivilegeKeyCodes(keyDao.getPrivilegeKeyCodesForGroup(user.getGroupId()));
                //Cookie c = new Cookie("user", Utils.convertObjectToJSON(userDto));
                //response.addCookie(c);
                //request.setAttribute("user", userDto);
                request.getSession().setAttribute("user", userDto);
                model.addObject("loginMsg", "Welcome "+login+"!");
            } else {
                model.addObject("loginMsg", "Wrong password.");
            }
        } else {
            model.addObject("loginMsg", "Username not found.");
        }
        LOGGER.info(login+" "+pass);
        return model;
    }
    
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    protected ModelAndView logout(HttpServletRequest request,
            HttpServletResponse response) throws Exception {      
        ModelAndView model = new ModelAndView("login");
        request.getSession().setAttribute("user", null);
        LOGGER.info("logout");
        return model;
    }
}
