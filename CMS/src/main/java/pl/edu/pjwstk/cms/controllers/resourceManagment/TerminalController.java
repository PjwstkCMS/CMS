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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.pjwstk.cms.controllers.general.BaseController;
import pl.edu.pjwstk.cms.dao.LogDao;
import pl.edu.pjwstk.cms.dao.TerminalDao;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.dto.TerminalDto;
import pl.edu.pjwstk.cms.models.Terminal;
import pl.edu.pjwstk.cms.utils.Utils;


@Controller
public class TerminalController extends BaseController {

    private final static Logger LOGGER = Logger.getLogger(TerminalController.class.getName());

    public TerminalController() {
        super("ManageTerminals","all");
    }

    @Override
    @RequestMapping("terminal")
    protected ModelAndView home(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if(!checkPrivileges(request)) {
            ModelAndView model = new ModelAndView("accessdenied");
            return model;
        }
        ModelAndView model = new ModelAndView("terminal");
        model.addObject("msg", "HelloGuestController");
        model.addObject("server", GenericDao.server);
        
        return model;
    }
    
    @RequestMapping(value = "/terminal/terminals")
    @ResponseBody
    public ResponseEntity<String> getData(HttpSession session, ModelMap model) {
        TerminalDao terDao = new TerminalDao();
        Map<String, Object> initData = new HashMap<>();
        initData.put("terminals", terDao.selectAll());
        return Utils.createResponseEntity(session, initData);
    }
    
    @RequestMapping(value = "/terminal/save/:object.htm")
    @ResponseBody
    public ResponseEntity<String> save(@RequestBody String object, HttpSession session) {
        TerminalDto dto = (TerminalDto) Utils.convertJSONStringToObject(object, "object", TerminalDto.class);
        TerminalDao terDao = new TerminalDao();
        Terminal ter = new Terminal();
        Map<String, Object> data = new HashMap<>();
        if(dto.getId() != null ){            
            ter = terDao.selectRecordsWithFieldValues("id", dto.getId()).get(0);
            ter.setDescription(dto.getDescription());
            ter.setMac(dto.getMac());
            terDao.update(ter);
            data.put("id", dto.getId());
            return Utils.createResponseEntity(session, data);
        } else {            
            ter.setDescription(dto.getDescription());
            ter.setMac(dto.getMac());
            data.put("id", terDao.insert(ter));
            return Utils.createResponseEntity(session, data);
        }
    }
    
    @RequestMapping(value = "/terminal/delete/:object", method = RequestMethod.POST)
    public @ResponseBody
    void deleteData(@RequestBody String object) {
        TerminalDto dto = (TerminalDto) Utils.convertJSONStringToObject(object, "object", TerminalDto.class);
        TerminalDao terDao = new TerminalDao();
        LogDao logDao = new LogDao();
        if (dto.getId() != null) {
            logDao.delete("terminalId="+dto.getId());
            terDao.delete("id="+dto.getId());
        }
    }
}
