package pl.edu.pjwstk.cms.controllers.resourceManagment;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.pjwstk.cms.controllers.general.BaseController;
import pl.edu.pjwstk.cms.dao.FileDao;
import pl.edu.pjwstk.cms.dao.ReportDao;
import pl.edu.pjwstk.cms.models.File;
import pl.edu.pjwstk.cms.utils.Utils;

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
    protected ModelAndView home(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ModelAndView model = new ModelAndView("report");

        return model;
    }

    @RequestMapping(value = "/reports")
    @ResponseBody
    public ResponseEntity<String> getData(HttpSession session, ModelMap model) {
        Map<String, Object> initData = new HashMap<String, Object>();
        ReportDao reportDao = new ReportDao();
        initData.put("reports", reportDao.selectAll());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=utf-8");
        return new ResponseEntity<String>(Utils.convertOMapToJSON(initData), responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/reportPrint")
    public @ResponseBody
    void download(@RequestParam("id") Long id, HttpServletResponse response, HttpServletRequest request) {
        File r = new File();
        FileDao fileDao = new FileDao();
        r = fileDao.selectRecordsWithFieldValues("id", id).get(0);
        Utils.download(r.getHashCode(), r.getName(), r.getMimeType(), response);
    }

    @RequestMapping(value = "/reportGetForm/{form}")
    public ModelAndView testFormLoad(@PathVariable("form") String form) {
        ModelAndView model = new ModelAndView("reportForms/" + form);
        return model;
    }

    @RequestMapping(value = "/reportTest")
    public ModelAndView testFormLoad() {
        ModelAndView model = new ModelAndView("reportForms/testReportForm");
        return model;
    }
}