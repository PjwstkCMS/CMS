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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.pjwstk.cms.controllers.general.BaseController;
import pl.edu.pjwstk.cms.dao.FileDao;
import pl.edu.pjwstk.cms.utils.Utils;
/**
 *
 * @author Macha
 */
@Controller
public class FileListUploadController extends BaseController {

    private final static Logger LOGGER = Logger.getLogger(FileListUploadController.class.getName());

    public FileListUploadController() {

    }

    @Override
    @RequestMapping("fileListUpload")
    protected ModelAndView home(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ModelAndView model = new ModelAndView("file");
        model.addObject("msg", "HelloGuestController");
        
        return model;
    }
    
    @RequestMapping("/fileListUpload/reports")
    @ResponseBody
    public ResponseEntity<String> getData(HttpSession session, ModelMap model) {
        Map<String, Object> initData = new HashMap<String, Object>();
        FileDao reportDao = new FileDao();
        initData.put("reports", reportDao.getReportDtos());
        return Utils.createResponseEntity(session, initData);
    }
}
