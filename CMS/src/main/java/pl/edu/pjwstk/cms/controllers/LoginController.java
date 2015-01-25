package pl.edu.pjwstk.cms.controllers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.pjwstk.cms.controllers.general.BaseController;
import pl.edu.pjwstk.cms.dao.EmployeeDao;
import pl.edu.pjwstk.cms.dao.PrivilegeGroupDao;
import pl.edu.pjwstk.cms.dao.PrivilegeKeyDao;
import pl.edu.pjwstk.cms.dao.SystemConfigurationDao;
import pl.edu.pjwstk.cms.dao.UserDao;
import pl.edu.pjwstk.cms.dto.EmployeeDto;
import pl.edu.pjwstk.cms.dto.UserDto;
import pl.edu.pjwstk.cms.models.PrivilegeGroup;
import pl.edu.pjwstk.cms.models.User;
import pl.edu.pjwstk.cms.utils.HexConverter;
import pl.edu.pjwstk.cms.utils.Utils;


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
        String login = (String) request.getParameter("login");
        String pass = (String) request.getParameter("password");
        LOGGER.info(login + " " + pass);
        UserDao userDao = new UserDao();
        List<User> users = userDao.selectRecordsWithFieldValues("login", login);
        SystemConfigurationDao sysDao = new SystemConfigurationDao();
        request.getSession().setAttribute("idleTimeout", sysDao.getIdleTimeout());
        if (users.size() > 0) {
            User user = users.get(0);
            if (user.getPassword().equals(pass)) {
                UserDto userDto = new UserDto();
                userDto.setLogin(login);
                userDto.setPassword(pass);
                userDto.setGroupId(Long.parseLong(user.getGroupId()));
                userDto.setEmployeeId(Long.parseLong(user.getEmployeeId()));
                userDto.setId(user.getId());
                EmployeeDao empDao = new EmployeeDao();
                EmployeeDto empDto = empDao.getEmployeeListDtoWithoutCards(Long.parseLong(user.getEmployeeId()), false).get(0);
                //Employee emp = empDao.selectForId(user.getEmployeeId());
                userDto.setPersondataId(Long.parseLong(empDto.getPersondataId() + ""));
                userDto.setPhotoHash(user.getPhotoHash());
                LOGGER.info(userDto.getLogin());
                PrivilegeGroupDao groupDao = new PrivilegeGroupDao();
                PrivilegeGroup group = groupDao.selectRecordsWithFieldValues("id", userDto.getGroupId()).get(0);
                PrivilegeKeyDao keyDao = new PrivilegeKeyDao();
                userDto.setPrivilegeKeyCodes(keyDao.getPrivilegeKeyCodesForGroup(user.getGroupId()));
                //Cookie c = new Cookie("user", Utils.convertObjectToJSON(userDto));
                //response.addCookie(c);
                //request.setAttribute("user", userDto);
                java.io.File file = Utils.getFileFromHash(userDto.getPhotoHash());
                request.getSession().setAttribute("user", userDto);
                request.getSession().setAttribute("userimage", file);
                request.getSession().setAttribute("sendUsers", userDao.getUserNames());
                request.getSession().setAttribute("userData", empDto);
                model.addObject("loginMsg", "Welcome " + login + "!");
            } else {
                model.addObject("loginMsg", "Wrong password.");
            }
        } else {
            model.addObject("loginMsg", "Username not found.");
        }
        LOGGER.info(login + " " + pass);

        UserDto userDto = (UserDto) request.getSession().getAttribute("user");
        try {
            byte barray[] = HexConverter.toBytesFromHex(userDto.getPhotoHash());
            //String get_price = rs.getString(5);
            java.io.File someFile = new java.io.File("image.jpg");
            FileOutputStream fos = new FileOutputStream(someFile);
            fos.write(barray);
            fos.flush();
            fos.close();
            model.addObject("userImage", someFile);
            System.out.println("photo");
        } catch (IOException io) {
            LOGGER.warning(io.getLocalizedMessage());
        } catch (NullPointerException nullEx) {
            LOGGER.warning(nullEx.getLocalizedMessage());
            model = new ModelAndView("login");
            model.addObject("error", "Brak użytkownika");
        }
        return model;
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    protected ModelAndView logout(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView model = new ModelAndView("login");
        request.getSession().setAttribute("user", null);
        request.getSession().setAttribute("userimage", null);
        request.getSession().setAttribute("sendUsers", null);
        request.getSession().setAttribute("userData", null);
        LOGGER.info("logout");
        return model;
    }

    @RequestMapping(value = "createAccount", method = RequestMethod.POST)
    protected ModelAndView create(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView model = new ModelAndView("login");

        String login = (String) request.getParameter("login");
        String pass = (String) request.getParameter("password");
        //String employeeId = (String) request.getParameter("employeeId");
        //String group = (String) request.getParameter("group");
        String forename = (String) request.getParameter("forename");
        String surname = (String) request.getParameter("surname");
        String subject = "Account request";
        String body = "Imię: " + forename + "</br>Nazwisko: " + surname + "</br> Login: " + login + "</br> Hasło " + pass;
        String username = "pjwstkhrsystem@vp.pl";

        if (Utils.sendMail(username, body, subject)) {
            model.addObject("sendStatus", "Wysłane");
        } else {
            model.addObject("sendStatus", "Niewysłane");
        }

        return model;
    }
}
