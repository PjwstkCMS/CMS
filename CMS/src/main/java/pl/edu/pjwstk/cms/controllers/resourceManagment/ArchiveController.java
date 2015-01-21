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
import pl.edu.pjwstk.cms.dao.CompanyDao;
import pl.edu.pjwstk.cms.dao.ContractDao;
import pl.edu.pjwstk.cms.dao.CustomerDao;
import pl.edu.pjwstk.cms.dao.EmployeeDao;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.utils.Utils;


@Controller
public class ArchiveController extends BaseController {

    private final static Logger LOGGER = Logger.getLogger(ArchiveController.class.getName());

    public ArchiveController() {
        super("ManageArchive","all");
    }
    
    @Override
    @RequestMapping("archive")
    protected ModelAndView home(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if(!checkPrivileges(request)) {
            ModelAndView model = new ModelAndView("accessdenied");
            return model;
        }
        ModelAndView model = new ModelAndView("archive");
        model.addObject("server", GenericDao.server);
        
        return model;
    }
    
    @RequestMapping(value = "/archive/employees")
    @ResponseBody
    public ResponseEntity<String> getEmps(HttpSession session, ModelMap model) {
        EmployeeDao empDao = new EmployeeDao();
        Map<String, Object> initData = new HashMap<String, Object>();
        initData.put("employees", empDao.getEmployeeListDtoWithoutCards(true));
        return Utils.createResponseEntity(session, initData);
    }
    
    @RequestMapping(value = "/archive/companies")
    @ResponseBody
    public ResponseEntity<String> getCompanies(HttpSession session, ModelMap model) {
        CompanyDao comDao = new CompanyDao();
        Map<String, Object> initData = new HashMap<String, Object>();
        initData.put("employees", comDao.getCompanyDtoList(true));
        return Utils.createResponseEntity(session, initData);
    }
    
    @RequestMapping(value = "/archive/contracts")
    @ResponseBody
    public ResponseEntity<String> getContracts(HttpSession session, ModelMap model) {
        ContractDao conDao = new ContractDao();
        Map<String, Object> initData = new HashMap<String, Object>();
        initData.put("employees", conDao.getContractDtoList(true));
        return Utils.createResponseEntity(session, initData);
    }
    
    @RequestMapping(value = "/archive/customers")
    @ResponseBody
    public ResponseEntity<String> getCustomers(HttpSession session, ModelMap model) {
        CustomerDao cusDao = new CustomerDao();
        Map<String, Object> initData = new HashMap<String, Object>();
        initData.put("employees", cusDao.getCustomerDtoList(true));
        return Utils.createResponseEntity(session, initData);
    }
}

