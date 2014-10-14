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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.pjwstk.cms.controllers.general.BaseController;
import pl.edu.pjwstk.cms.dao.ContractDao;
import pl.edu.pjwstk.cms.dao.CustomerDao;
import pl.edu.pjwstk.cms.dao.EmployeeDao;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.dto.ContractDto;
import pl.edu.pjwstk.cms.models.Contract;
import pl.edu.pjwstk.cms.utils.Utils;

@Controller
public class ContractController extends BaseController {

    private final static Logger LOGGER = Logger.getLogger(ContractController.class.getName());

    public ContractController() {
        super("ManageContracts","all");
    }

    @Override
    @RequestMapping("contract")
    protected ModelAndView home(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if(!checkPrivileges(request)) {
            ModelAndView model = new ModelAndView("accessdenied");
            return model;
        }
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
    
    @RequestMapping(value = "/contract/save/:object", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> saveData(@RequestBody String object, HttpSession session) {
        ContractDto conDto = (ContractDto) Utils.convertJSONStringToObject(object, "object", ContractDto.class);
        ContractDao conDao = new ContractDao();
        Contract con = new Contract();
        Map<String, Object> data = new HashMap<>();
        if(conDto.getId() != null ){
            con = conDao.selectRecordsWithFieldValues("id", conDto.getId()).get(0);
            con.setEmployeeId(conDto.getEmployeeId());
            con.setCustomerId(conDto.getCustomerId());
            con.setStartDate(conDto.getStartDate());
            con.setCloseDate(conDto.getCloseDate());
            if(conDto.getFinalisationDate() != null && conDto.getFinalisationDate() != ""){
                con.setFinalisationDate(conDto.getFinalisationDate());
            }else{
                con.setFinalisationDate("NULL");
            }
            con.setPrice(conDto.getPrice());
            con.setDescription(conDto.getDescription());
            conDao.update(con);
            data.put("id", conDto.getId());
            return Utils.createResponseEntity(session, data);
        } else {
            con.setEmployeeId(conDto.getEmployeeId());
            con.setCustomerId(conDto.getCustomerId());
            con.setStartDate(conDto.getStartDate());
            con.setCloseDate(conDto.getCloseDate());
            if(conDto.getFinalisationDate() != null && conDto.getFinalisationDate() != ""){
                con.setFinalisationDate(conDto.getFinalisationDate());
            }else{
                con.setFinalisationDate("NULL");
            }
            con.setPrice(conDto.getPrice());
            con.setDescription(conDto.getDescription());
            data.put("id", conDao.insert(con));
            return Utils.createResponseEntity(session, data);
        }
    }
    
    @RequestMapping(value = "/contract/delete/:object", method = RequestMethod.POST)
    public @ResponseBody
    void deleteData(@RequestBody String object) {
        ContractDto conDto = (ContractDto) Utils.convertJSONStringToObject(object, "object", ContractDto.class);
        ContractDao conDao = new ContractDao();
        if (conDto.getId() != null) {
            conDao.delete("id="+conDto.getId());
        }
    }
}

