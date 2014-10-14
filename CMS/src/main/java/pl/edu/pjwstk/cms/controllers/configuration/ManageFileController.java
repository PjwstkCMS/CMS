package pl.edu.pjwstk.cms.controllers.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.pjwstk.cms.controllers.general.BaseController;
import pl.edu.pjwstk.cms.dao.FileDao;
import pl.edu.pjwstk.cms.models.File;
import pl.edu.pjwstk.cms.utils.HexConverter;
import pl.edu.pjwstk.cms.utils.Utils;
/**
 *
 * @author Konrad
 */
@Controller
public class ManageFileController extends BaseController {

    private final static Logger LOGGER = Logger.getLogger(ManageFileController.class.getName());

    public ManageFileController() {
        super("ManageFiles","all");
    }

    @Override
    @RequestMapping("manageFile")
    protected ModelAndView home(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if(!checkPrivileges(request)) {
            ModelAndView model = new ModelAndView("accessdenied");
            return model;
        }
        ModelAndView model = new ModelAndView("manageFile");
        
        return model;
    }
    
    @RequestMapping("/manageFile/reports")
    @ResponseBody
    public ResponseEntity<String> getData(HttpSession session, ModelMap model) {
        Map<String, Object> initData = new HashMap<String, Object>();
        FileDao reportDao = new FileDao();
        initData.put("reports", reportDao.getReportDtos());
        return Utils.createResponseEntity(session, initData);
    }
    
    @RequestMapping(value = "/manageFile/upload", method = RequestMethod.POST)
    public String upload(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            File r = new File();
            FileDao dao = new FileDao();
            for (FileItem item : items) {
                if (item.isFormField()) {
                    String fieldname = item.getFieldName();
                    String fieldvalue = item.getString();
                    if (fieldname.equals("fileExt")) {
                        r.setMimeType(fieldvalue);
                    } else {
                        r.setDescription(fieldvalue);
                    }
                } else {
                    //String fieldname = item.getFieldName();
                    //String filename = item.getName();
                    r.setName(item.getName());
                    InputStream input = item.getInputStream();
                    byte[] barr = new byte[(int) item.getSize()];
                    input.read(barr);
                    String s = HexConverter.toHexFromBytes(barr);
                    System.out.println(s.length());
                    r.setHashCode(s);
                }
            }
            dao.insert(r);
        } catch (FileUploadException ex) {
            System.err.println("Can't process file\n" + ex.getMessage());
        } catch (IOException io) {
            System.err.println("Can't process file\n" + io.getMessage());
        }
        return "redirect:/manageFile.htm";
    }

    @RequestMapping(value = "/manageFile/download")
    public @ResponseBody
    void download(@RequestParam("id") Long id, HttpServletResponse response) {
        File r = new File();
        FileDao dao = new FileDao();
        r = dao.selectRecordsWithFieldValues("id", id).get(0);
        Utils.download(r.getHashCode(), r.getName(), r.getMimeType(), response);
    }
}
