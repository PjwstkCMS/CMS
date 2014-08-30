package pl.edu.pjwstk.cms.controllers;

import java.util.ArrayList;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.pjwstk.cms.controllers.general.BaseController;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.models.Customer;
import pl.edu.pjwstk.cms.utils.ConnectionManager;

/**
 *
 * @author Sergio
 */
public class LoginController extends BaseController {

    private final static Logger LOGGER = Logger.getLogger(LoginController.class.getName());

    public LoginController() {

    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ModelAndView model = new ModelAndView("newjsp");
        model.addObject("msg", "HelloGuestController");
        ConnectionManager.getConnectionManager().select("SELECT * FROM employee");
        GenericDao<Customer> dao = new GenericDao<>(Customer.class);
        dao.selectRecordsWithFieldValues(new ArrayList<String>(), new ArrayList<String>());
        return model;
    }
}
