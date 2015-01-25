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
import pl.edu.pjwstk.cms.dao.EmployeeDao;
import pl.edu.pjwstk.cms.dto.EmployeeDto;


@Controller
public class EmployeeListReportController extends BaseReportTemplateController{
    private static final Logger LOG = Logger.getLogger(EmployeeListReportController.class.getName());
    
    public EmployeeListReportController() {
        
    }
    
    @RequestMapping(value = "/employeeListReport")
    public
    ModelAndView download(HttpServletResponse response) {
        ModelAndView model = new ModelAndView("templates/employeeListReport");
        configHttpResponse(response, "EmployeeList");
        Map<String, Object> params = new HashMap<>();
        EmployeeDao empDao = new EmployeeDao();
        List<EmployeeDto> list = empDao.getEmployeeListDtoWithoutCards(false);
        list.sort(new Comparator<EmployeeDto>() {

            @Override
            public int compare(EmployeeDto o1, EmployeeDto o2) {
                return o1.getSurname().compareTo(o2.getSurname());
            }
            
        });
        params.put("employees", list);
        model.addAllObjects(params);
        return model;
    }
}
