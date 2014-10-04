package pl.edu.pjwstk.cms.controllers.resourceManagment;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.pjwstk.cms.controllers.general.BaseController;
import pl.edu.pjwstk.cms.dao.ContractDao;
import pl.edu.pjwstk.cms.dao.CustomerDao;
import pl.edu.pjwstk.cms.dao.EmployeeDao;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.utils.Utils;

@Controller
public class ContractController extends BaseController {

    private final static Logger LOGGER = Logger.getLogger(ContractController.class.getName());

    public ContractController() {

    }

    @Override
    @RequestMapping("contract")
    protected ModelAndView home(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ModelAndView model = new ModelAndView("contract");
        model.addObject("msg", "HelloGuestController");
        model.addObject("server", GenericDao.server);
        
        return model;
    }
    @RequestMapping(value = "/contract/contracts")
    @ResponseBody
    public ResponseEntity<String> getData(HttpSession session, ModelMap model) {
        ContractDao conDao = new ContractDao();
        CustomerDao cusDao = new CustomerDao();
        EmployeeDao empDao = new EmployeeDao();
        Map<String, Object> initData = new HashMap<String, Object>();
        initData.put("contracts", conDao.selectAll());
        initData.put("customers", cusDao.getCustomerDtoList());
        initData.put("employees", empDao.getEmployeeDtoList());
        return Utils.createResponseEntity(session, initData);
    }
}

