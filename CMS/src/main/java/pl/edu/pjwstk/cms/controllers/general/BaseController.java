package pl.edu.pjwstk.cms.controllers.general;

import java.util.List;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author Sergio
 */
public class BaseController extends AbstractController{
    
    private final static Logger LOGGER = Logger.getLogger(BaseController.class.getName()); 

    protected List<String> privilegesNeeded;
    
    public BaseController() {

    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
