package pl.edu.pjwstk.cms.controllers;


import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.pjwstk.cms.controllers.general.BaseController;
/**
 *
 * @author Konrad
 */
@Controller
public class ReportController extends BaseController {

    private final static Logger LOGGER = Logger.getLogger(ReportController.class.getName());

    public ReportController() {

    }

    @Override
    @RequestMapping("report")
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ModelAndView model = new ModelAndView("report");
        model.addObject("msg", "HelloGuestController");
        
        return model;
    }
}
