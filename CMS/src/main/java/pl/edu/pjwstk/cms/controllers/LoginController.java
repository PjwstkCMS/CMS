package pl.edu.pjwstk.cms.controllers;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.pjwstk.cms.controllers.general.BaseController;
import pl.edu.pjwstk.cms.dao.CustomerDao;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.models.Customer;

/**
 *
 * @author Sergio
 */
@Controller
public class LoginController extends BaseController {

    private final static Logger LOGGER = Logger.getLogger(LoginController.class.getName());

    public LoginController() {

    }
    
    @Override
    @RequestMapping("login")
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ModelAndView model = new ModelAndView("login");
        model.addObject("msg", "HelloGuestController");
        CustomerDao customerDao = new CustomerDao();
        customerDao.selectRecordsWithFieldValues(new ArrayList<String>(), new ArrayList<String>());
        Map<String, List<Object>> map = new HashMap<>();
        List<Object> list = new ArrayList<>();
        list.add("Pawełe");
        list.add("dyuuyy");
        map.put("name", list);
        customerDao.selectForFieldsWithMultiplePossibileValues(map);
        return model;
    }
}
