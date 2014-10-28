package pl.edu.pjwstk.cms.controllers;

import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
        String login = (String) request.getParameter("login");
        String pass = (String) request.getParameter("password");
        LOGGER.info(login + " " + pass);
        UserDao userDao = new UserDao();
        List<User> users = userDao.selectRecordsWithFieldValues("login", login);
        if (users.size() > 0) {
            User user = users.get(0);
            if (user.getPassword().equals(pass)) {
                UserDto userDto = new UserDto();
                userDto.setLogin(login);
                userDto.setPassword(pass);
                userDto.setGroupId(Long.parseLong(user.getGroupId()));
                LOGGER.info(userDto.getLogin());
                PrivilegeGroupDao groupDao = new PrivilegeGroupDao();
                PrivilegeGroup group = groupDao.selectRecordsWithFieldValues("id", userDto.getGroupId()).get(0);
                PrivilegeKeyDao keyDao = new PrivilegeKeyDao();
                userDto.setPrivilegeKeyCodes(keyDao.getPrivilegeKeyCodesForGroup(user.getGroupId()));
                //Cookie c = new Cookie("user", Utils.convertObjectToJSON(userDto));
                //response.addCookie(c);
                //request.setAttribute("user", userDto);
                request.getSession().setAttribute("user", userDto);
                model.addObject("loginMsg", "Welcome " + login + "!");
            } else {
                model.addObject("loginMsg", "Wrong password.");
            }
        } else {
            model.addObject("loginMsg", "Username not found.");
        }
        LOGGER.info(login + " " + pass);
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

    @RequestMapping(value = "createAccount", method = RequestMethod.POST)
    protected ModelAndView create(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        String login = (String) request.getParameter("login");
        String pass = (String) request.getParameter("password");
        String name = (String) request.getParameter("name");
        String surname = (String) request.getParameter("surname");
        String subject = "Account request";
        String body = "Imie: "+name+"</br>Nazwisko: "+surname+"</br> Login: "+login+"</br> Hasło "+pass;
        String username = "pjwstkhrsystem@vp.pl";
        String password = "hrsystem123";
        String host = "smtp.poczta.onet.pl";
        ModelAndView model = new ModelAndView("login");

        try {
            Properties prop = System.getProperties();
            Authenticator auth = new myAuthenticator();
            prop.put("mail.smtp.host", host);
            prop.put("mail.smtp.user", username);
            prop.put("mail.smtp.password", password);
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.enable", "true");
            prop.put("mail.smtp.port", 587);

            Session session = Session.getInstance(prop, auth);

            session.setDebug(true);
            Message message = new MimeMessage(session);
            //wstawienie treści
            //message.setContent("tresc","text/plain");
            message.setText(body);
            //wstawienie tytułu
            message.setSubject(subject);
            Address address = new InternetAddress(username);
            Address addressOdbiorcy = new InternetAddress(username);

            //wstawienie adresu nadawcy do wiadomości
            message.setFrom(address);
            //wstawienie adresu odbiorcy
            message.addRecipient(Message.RecipientType.TO, addressOdbiorcy);
            message.saveChanges();
            Transport transport = session.getTransport("smtp");
            transport.connect(host, username, password);
            transport.sendMessage(message, message.getAllRecipients());

            transport.close();

        } catch (Exception ex) {
            LOGGER.warning(ex.getLocalizedMessage());
            //Logger.getLogger(mailWyslanie.class.getName()).log(Level.SEVERE, null, ex);
        }

        return model;
    }
}

class myAuthenticator extends Authenticator {

    String username = "pjwstkhrsystem@vp.pl";
    String password = "hrsystem123";

    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }
}
