package pl.edu.pjwstk.cms.controllers.reportTemplates;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.pjwstk.cms.controllers.general.BaseReportTemplateController;
import pl.edu.pjwstk.cms.dao.LogDao;
import pl.edu.pjwstk.cms.dto.LogDto;


@Controller
public class EmployeeLogReportController extends BaseReportTemplateController{
    
    private static final Logger LOGGER = Logger.getLogger(EmployeeLogReportController.class.getName());
    
    public EmployeeLogReportController() {
        
    }
    
    @RequestMapping(value = "/employeeLogReport")
    public
    ModelAndView download(@RequestParam("employeeId") String employeeId, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("report");
        model.addObject("error", "Brak pracownika o tym numerze id");
        LogDao logs = new LogDao();
        Map<String, Object> params = new HashMap<>();
        
        if(employeeId!=null){
            try{
                Long id = Long.parseLong(employeeId);
                List<LogDto> list = logs.getLogDtoList(id);
                if(!list.isEmpty()){
                    model = new ModelAndView("templates/employeeLogReport");
                    configHttpResponse(response, "EmployeeLogReport");
                    params.put("logs", list);
                    model.addAllObjects(params);
                }
            }catch(Exception ex){
                LOGGER.warning("Błędne ID");
            }
        }
               
        return model;
    }
    
    
}
