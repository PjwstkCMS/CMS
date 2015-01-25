package pl.edu.pjwstk.cms.controllers.reportTemplates;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.pjwstk.cms.controllers.general.BaseReportTemplateController;


@Controller
public class TestReportController extends BaseReportTemplateController{
    
    private static final Logger LOGGER = Logger.getLogger(TestReportController.class.getName());
    
    public TestReportController() {
        
    }
    
    @RequestMapping(value = "/testReport")
    public
    ModelAndView download(@RequestParam("msg1") String msg1, 
            @RequestParam("msg2") String msg2,
            @RequestParam("msg3") String msg3,
            HttpServletResponse response) {
        ModelAndView model = new ModelAndView("templates/testReport");
        configHttpResponse(response, "TestReport");
        Map<String, Object> params = new HashMap<>();
        params.put("msg1", msg1);
        params.put("msg2", msg2);
        params.put("msg3", msg3);
        model.addAllObjects(params);
        return model;
    }
    
    
}
