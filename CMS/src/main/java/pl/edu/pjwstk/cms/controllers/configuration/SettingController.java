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
import pl.edu.pjwstk.cms.dao.SystemConfigurationDao;
import pl.edu.pjwstk.cms.models.SystemConfiguration;
import pl.edu.pjwstk.cms.utils.Utils;


@Controller
public class SettingController extends BaseController {

    private final static Logger LOGGER = Logger.getLogger(SettingController.class.getName());

    public SettingController() {
        super("ManageSettings","all");
    }

    @Override
    @RequestMapping("setting")
    protected ModelAndView home(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if(!checkPrivileges(request)) {
            ModelAndView model = new ModelAndView("accessdenied");
            return model;
        }
        ModelAndView model = new ModelAndView("setting");
        model.addObject("msg", "HelloGuestController");
        
        return model;
    }
    
    @RequestMapping(value = "/systemConfig/configs")
    @ResponseBody
    public ResponseEntity<String> getData(HttpSession session, ModelMap model) {
        Map<String, Object> initData = new HashMap<>();
        SystemConfigurationDao sysDao = new SystemConfigurationDao();
        initData.put("systemConfigs", sysDao.selectAll());
        return Utils.createResponseEntity(session, initData);
    }
    
    @RequestMapping(value = "/systemConfig/save/:object", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> saveData(@RequestBody String object, HttpSession session) {
        SystemConfiguration sc = (SystemConfiguration) Utils.convertJSONStringToObject(object, "object", SystemConfiguration.class);
        SystemConfigurationDao dao = new SystemConfigurationDao();
        dao.update(sc);
        Map<String, Object> data = new HashMap<>();
        data.put("id", sc.getId());
        return Utils.createResponseEntity(session, data);
    }
    
    @RequestMapping(value = "/systemConfig/defaultConfigs")
    @ResponseBody
    public ResponseEntity<String> getDefaultConfigs(HttpSession session, ModelMap model) {
        Map<String, Object> initData = new HashMap<>();
        SystemConfigurationDao sysDao = new SystemConfigurationDao();
        SystemConfiguration idleTimeout = sysDao.selectSingleRecord("name", "IdleTimeout");
        idleTimeout.setDescription("Czas do automatycznego wylogowania.");
        idleTimeout.setValue("600");
        SystemConfiguration DefaultPageEncoding = sysDao.selectSingleRecord("name", "DefaultPageEncoding");
        DefaultPageEncoding.setDescription("Standardowe kodowanie.");
        DefaultPageEncoding.setValue("utf-8");
        sysDao.update(idleTimeout);
        
        SystemConfiguration AccountRequestEmail = sysDao.selectSingleRecord("name", "AccountRequestEmail");
        AccountRequestEmail.setDescription("konto pocztowe na które wysyłane są prośby o konto.");
        AccountRequestEmail.setValue("accreqehr@gmail.com");
        sysDao.update(idleTimeout);
        
        SystemConfiguration AccountRequestPassword = sysDao.selectSingleRecord("name", "AccountRequestPassword");
        AccountRequestPassword.setDescription("Hasło do konta pocztowego do przyjmowania próśb o konto.");
        AccountRequestPassword.setValue("easyhr123");
        sysDao.update(AccountRequestPassword);
        
        SystemConfiguration AccountRequestLogin = sysDao.selectSingleRecord("name", "AccountRequestLogin");
        AccountRequestLogin.setDescription("Login do konta pocztowego do przyjomwania próśb o konto.");
        AccountRequestLogin.setValue("accreqehr@gmail.com");
        sysDao.update(AccountRequestLogin);
        
        SystemConfiguration AccountRequestSMTP = sysDao.selectSingleRecord("name", "AccountRequestSMTP");
        AccountRequestSMTP.setDescription("AccountRequestSMTP");
        AccountRequestSMTP.setValue("smtp.gmail.com");
        sysDao.update(AccountRequestSMTP);
        
        SystemConfiguration AccountCreationLink = sysDao.selectSingleRecord("name", "AccountCreationLink");
        AccountCreationLink.setDescription("Link pod którym będzie automatyczne tworzenie konta dla użytkownika, który wysłał prośbę");
        AccountCreationLink.setValue("todo.com");
        sysDao.update(AccountCreationLink);
        
        SystemConfiguration LoginPersistanceTime = sysDao.selectSingleRecord("name", "LoginPersistanceTime");
        LoginPersistanceTime.setDescription("Czas zapamiętania danych logowania.");
        LoginPersistanceTime.setValue("3600");
        sysDao.update(LoginPersistanceTime);
        
        
        initData.put("systemConfigs", sysDao.selectAll());
        return Utils.createResponseEntity(session, initData);
    }
}

