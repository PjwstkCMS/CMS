package pl.edu.pjwstk.cms.controllers.general;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.pjwstk.cms.dto.UserDto;


public class BaseController {

    private final static Logger LOGGER = Logger.getLogger(BaseController.class.getName());

    protected List<String> privilegesNeeded;

    public BaseController(String... privilegeKeys) {
        privilegesNeeded = new ArrayList<>();
        for (String key : privilegeKeys) {
            privilegesNeeded.add(key);
        }
    }

    protected boolean checkPrivileges(HttpServletRequest request) {
        try {
            UserDto user = (UserDto) request.getSession().getAttribute("user");
            for (String key : privilegesNeeded) {
                if (user.getPrivilegeKeyCodes().contains(key)) {
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    protected ModelAndView home(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
