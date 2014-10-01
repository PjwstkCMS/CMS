/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.pjwstk.cms.controllers.general.BaseController;
import pl.edu.pjwstk.cms.dao.AddressDao;
import pl.edu.pjwstk.cms.dao.CompanyDao;
import pl.edu.pjwstk.cms.dao.ContractDao;
import pl.edu.pjwstk.cms.dao.CustomerDao;
import pl.edu.pjwstk.cms.dao.PersonDataDao;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.dto.CustomerDto;
import pl.edu.pjwstk.cms.models.Customer;
import pl.edu.pjwstk.cms.models.PersonData;
import pl.edu.pjwstk.cms.utils.Utils;

/**
 *
 * @author Konrad
 */
@Controller
public class CustomerController extends BaseController {

    private final static Logger LOGGER = Logger.getLogger(CustomerController.class.getName());

    public CustomerController() {

    }

    @Override
    @RequestMapping("customer")
    protected ModelAndView home(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ModelAndView model = new ModelAndView("customer");
        model.addObject("server", GenericDao.server);
        return model;
    }

    @RequestMapping(value = "/customer/customers")
    @ResponseBody
    public ResponseEntity<String> getData(HttpSession session) {
        CustomerDao cusDao = new CustomerDao();
        CompanyDao comDao = new CompanyDao();
        //AddressDao addDao = new AddressDao();
        ContractDao conDao = new ContractDao();
        Map<String, Object> initData = new HashMap<String, Object>();
        initData.put("customers", cusDao.getCustomerDtoList());
        initData.put("companies", comDao.getCompanyDtoList());
        initData.put("contracts", conDao.selectAll());
        return Utils.createResponseEntity(session, initData);
    }
    
    @RequestMapping(value = "/customerSave/:object.htm")
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
            customer.setCompanyId("-1");
            customer.setPersondataId(perDao.insert(person)+"");
            data.put("id", customerDao.insert(customer));
            return Utils.createResponseEntity(session, data);
        }
    }
}
