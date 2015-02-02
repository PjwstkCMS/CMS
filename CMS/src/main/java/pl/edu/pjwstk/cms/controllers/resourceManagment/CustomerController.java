package pl.edu.pjwstk.cms.controllers.resourceManagment;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.pjwstk.cms.controllers.general.BaseController;
import pl.edu.pjwstk.cms.dao.CompanyDao;
import pl.edu.pjwstk.cms.dao.ContractDao;
import pl.edu.pjwstk.cms.dao.CustomerDao;
import pl.edu.pjwstk.cms.dao.PersonDataDao;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.dto.CustomerDto;
import pl.edu.pjwstk.cms.models.Customer;
import pl.edu.pjwstk.cms.models.PersonData;
import pl.edu.pjwstk.cms.utils.Utils;


@Controller
public class CustomerController extends BaseController {

    private final static Logger LOGGER = Logger.getLogger(CustomerController.class.getName());

    public CustomerController() {
        super("ManageContracts","all");
    }

    @Override
    @RequestMapping("customer")
    protected ModelAndView home(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if(!checkPrivileges(request)) {
            ModelAndView model = new ModelAndView("accessdenied");
            return model;
        }
        ModelAndView model = new ModelAndView("customer");
        model.addObject("server", GenericDao.server);
        return model;
    }

    @RequestMapping(value = "/customer/customers")
    @ResponseBody
    public ResponseEntity<String> getData(HttpSession session) {
        CustomerDao cusDao = new CustomerDao();
        CompanyDao comDao = new CompanyDao();
        ContractDao conDao = new ContractDao();
        Map<String, Object> initData = new HashMap<String, Object>();
        initData.put("customers", cusDao.getCustomerDtoList(false));
        initData.put("companies", comDao.getCompanyDtoList(false));
        initData.put("contracts", conDao.getContractDtoList(false));
        return Utils.createResponseEntity(session, initData);
    }
    
    @RequestMapping(value = "/customer/save/:object.htm")
    @ResponseBody
    public ResponseEntity<String> save(@RequestBody String object, HttpSession session) {
        CustomerDao customerDao = new CustomerDao();
        PersonDataDao perDao = new PersonDataDao();
        CustomerDto customerDto = (CustomerDto) Utils.convertJSONStringToObject(object, "object", CustomerDto.class);
        Customer customer = new Customer();
        PersonData person = new PersonData();
        Map<String, Object> data = new HashMap<>();
        if(customerDto.getId() != null ){            
            customer = customerDao.selectRecordsWithFieldValues("id", customerDto.getId()).get(0);
            person = perDao.selectRecordsWithFieldValues("id", customerDto.getPersondataId()).get(0);
            person.setForename(customerDto.getForename());
            person.setSurname(customerDto.getSurname());
            person.setEmail(customerDto.getEmail());
            person.setPhone(customerDto.getPhone());
            customerDao.update(customer);
            perDao.update(person);
            data.put("id", customerDto.getId());
            return Utils.createResponseEntity(session, data);
        } else {            
            person.setForename(customerDto.getForename());
            person.setSurname(customerDto.getSurname());
            person.setEmail(customerDto.getEmail());
            person.setPhone(customerDto.getPhone());
            customer.setCompanyId(customerDto.getCompanyId()+"");
            customer.setPersondataId(perDao.insert(person)+"");
            data.put("id", customerDao.insert(customer));
            return Utils.createResponseEntity(session, data);
        }
    }
    
    @RequestMapping(value = "/customer/delete/:object", method = RequestMethod.POST)
    public @ResponseBody
    void deleteData(@RequestBody String object) {
        CustomerDto dto = (CustomerDto) Utils.convertJSONStringToObject(object, "object", CustomerDto.class);
        CustomerDao cusDao = new CustomerDao();
        PersonDataDao perDao = new PersonDataDao();
        ContractDao conDao = new ContractDao();
        CompanyDao comDao = new CompanyDao();
        if (dto.getId() != null) {
            conDao.delete("customerId="+dto.getId());
            perDao.delete("id="+dto.getPersondataId());
            comDao.updateFieldForAllElementsWithId(
                    "contactpersonId", dto.getPersondataId() + "",
                    "contactpersonId", "-1");
            cusDao.delete("id="+dto.getId());
        }
    }
    
    @RequestMapping(value = "/customer/archive/:object", method = RequestMethod.POST)
    public @ResponseBody
    void archiveData(@RequestBody String object) {
        CustomerDto dto = (CustomerDto) Utils.convertJSONStringToObject(object, "object", CustomerDto.class);
        CustomerDao cusDao = new CustomerDao();
        Customer cus = cusDao.selectForId(dto.getId());
        cusDao.archive(cus);
    }
}
