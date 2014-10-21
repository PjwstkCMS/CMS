package pl.edu.pjwstk.cms.controllers.resourceManagment;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.pjwstk.cms.controllers.general.BaseController;
import pl.edu.pjwstk.cms.dao.FileDao;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.models.File;
import pl.edu.pjwstk.cms.utils.Utils;


@Controller
public class FileController extends BaseController {

    private final static Logger LOGGER = Logger.getLogger(FileController.class.getName());

    public FileController() {
        super("DownloadFiles","all");
    }

    @Override
    @RequestMapping("fileList")
    protected ModelAndView home(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if(!checkPrivileges(request)) {
            ModelAndView model = new ModelAndView("accessdenied");
            return model;
        }
        ModelAndView model = new ModelAndView("fileList");
        model.addObject("msg", "HelloGuestController");
        model.addObject("server", GenericDao.server);
        
        return model;
    }
    
    @RequestMapping(value = "/fileList/files")
    @ResponseBody
    public ResponseEntity<String> getData(HttpSession session, ModelMap model) {
        Map<String, Object> initData = new HashMap<String, Object>();
        FileDao reportDao = new FileDao();
        initData.put("files", reportDao.getReportDtos());
        return Utils.createResponseEntity(session, initData);
    }

    @RequestMapping(value = "/fileList/download")
    public @ResponseBody
    void download(@RequestParam("id") Long id, HttpServletResponse response) {
        File r = new File();
        FileDao dao = new FileDao();
        r = dao.selectRecordsWithFieldValues("id", id).get(0);
        Utils.download(r.getHashCode(), r.getName(), r.getMimeType(), response);
    }
}
