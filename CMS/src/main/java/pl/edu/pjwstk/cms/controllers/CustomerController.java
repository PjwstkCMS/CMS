/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.pjwstk.cms.controllers;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.pjwstk.cms.controllers.general.BaseController;
import pl.edu.pjwstk.cms.dao.CustomerDao;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.dto.CustomerDto;
import pl.edu.pjwstk.cms.models.Customer;
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
    public ResponseEntity<String> getData(HttpSession session, ModelMap model) {
        CustomerDao cusDao = new CustomerDao();
        Map<String, Object> initData = new HashMap<String, Object>();
        initData.put("customers", cusDao.getCustomerDtoList());
        return Utils.createResponseEntity(session, initData);
    }
    
    @RequestMapping(value = "/customerSave/:object.htm")
    @ResponseBody
    public ResponseEntity<String> save(@RequestBody String object, HttpSession session, ModelMap model) {
        CustomerDao customerDao = new CustomerDao();
        CustomerDto customerDto = (CustomerDto) Utils.convertJSONStringToObject(object, "object", CustomerDto.class);
        Customer customer = new Customer();
        if(customerDto.getId() != null ){            
            customer = customerDao.selectRecordsWithFieldValues("id", customerDto.getId()).get(0);
            customer.setName(customerDto.getName());
            customer.setSurname(customerDto.getSurname());
            customer.setEmail(customerDto.getEmail());
            customer.setPhone(customerDto.getPhone());
            customerDao.update(customer);
            return Utils.createResponseEntity(session, model);
        } else {            
            customer.setName(customerDto.getName());
            customer.setSurname(customerDto.getSurname());
            customer.setEmail(customerDto.getEmail());
            customer.setPhone(customerDto.getPhone());
            customer.setCompanyId("-1");
            customerDao.insert(customer);
            return Utils.createResponseEntity(session, model);
        }
    }
}
