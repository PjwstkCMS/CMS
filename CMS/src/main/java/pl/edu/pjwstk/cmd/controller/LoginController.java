package pl.edu.pjwstk.cmd.controller;

import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.pjwstk.cms.controllers.general.BaseController;

/**
 *
 * @author Sergio
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{
    
    private final static Logger LOGGER = Logger.getLogger(LoginController.class.getName());

    public LoginController() {

    }
}
