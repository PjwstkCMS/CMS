/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.pjwstk.cms.controllers.reportTemplates;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.pjwstk.cms.controllers.general.BaseReportTemplateController;
import pl.edu.pjwstk.cms.dao.CustomerDao;
import pl.edu.pjwstk.cms.dto.CustomerDto;

/**
 *
 * @author sergio
 */
@Controller
public class CustomerListReportController extends BaseReportTemplateController{
    private static final Logger LOG = Logger.getLogger(CustomerListReportController.class.getName());
    
    public CustomerListReportController() {
        
    }
    
    @RequestMapping(value = "/customerListReport")
    public
    ModelAndView download(HttpServletResponse response) {
        ModelAndView model = new ModelAndView("templates/customerListReport");
        configHttpResponse(response, "CustomerList");
        Map<String, Object> params = new HashMap<>();
        CustomerDao cusDao = new CustomerDao();
        List<CustomerDto> list = cusDao.getCustomerDtoList(false);
        list.sort(new Comparator<CustomerDto>() {

            @Override
            public int compare(CustomerDto o1, CustomerDto o2) {
                return o1.getSurname().compareTo(o2.getSurname());
            }
            
        });
        params.put("customers", list);
        model.addAllObjects(params);
        return model;
    }
}
