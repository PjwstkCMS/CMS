package pl.edu.pjwstk.cms.controllers.general;

import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Sergio
 */
public class BaseController {
    
    private final static Logger LOGGER = Logger.getLogger(BaseController.class.getName()); 

    protected List<String> privileges;
    
    public BaseController() {

    }
}
