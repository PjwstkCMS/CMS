package pl.edu.pjwstk.cms.controllers.general;

import java.util.List;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;


public class BaseReportTemplateController extends BaseController{
    
    private final static Logger LOGGER = Logger.getLogger(BaseReportTemplateController.class.getName()); 

    protected List<String> privilegesNeeded;
    
    public BaseReportTemplateController() {

    }
    
    protected void configHttpResponse(HttpServletResponse response, String filename) {
        response.setContentType("application/rtf");
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                filename+".rtf");
        response.setHeader(headerKey, headerValue);
    }
}
