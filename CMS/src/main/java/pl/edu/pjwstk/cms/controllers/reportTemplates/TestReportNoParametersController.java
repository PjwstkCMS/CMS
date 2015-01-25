package pl.edu.pjwstk.cms.controllers.reportTemplates;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.pjwstk.cms.controllers.general.BaseReportTemplateController;


@Controller
public class TestReportNoParametersController extends BaseReportTemplateController{
    
    private static final Logger LOGGER = Logger.getLogger(TestReportNoParametersController.class.getName());
    
    public TestReportNoParametersController() {
        
    }
    
    @RequestMapping(value = "/reportPrint2")
    public
    ModelAndView download(HttpServletResponse response) {
        ModelAndView model = new ModelAndView("templates/testReportNoParameters");
        configHttpResponse(response, "TestReportNoParameters");
        Map<String, Object> params = new HashMap<>();
        params.put("someData", "this is some data");
        model.addAllObjects(params);
        return model;
    }
    
    
}
