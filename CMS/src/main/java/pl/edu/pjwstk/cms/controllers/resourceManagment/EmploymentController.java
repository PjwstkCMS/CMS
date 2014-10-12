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
import pl.edu.pjwstk.cms.dao.DictionaryDao;
import pl.edu.pjwstk.cms.dao.EmployeeDao;
import pl.edu.pjwstk.cms.dao.EmploymentDao;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.dto.EmploymentDto;
import pl.edu.pjwstk.cms.models.Employment;
import pl.edu.pjwstk.cms.utils.Utils;


@Controller
public class EmploymentController extends BaseController {

    private final static Logger LOGGER = Logger.getLogger(EmploymentController.class.getName());

    public EmploymentController() {

    }
    
    @Override
    @RequestMapping("employment")
    protected ModelAndView home(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ModelAndView model = new ModelAndView("employment");
        model.addObject("msg", "HelloGuestController");
        model.addObject("server", GenericDao.server);
        
        return model;
    }
    
    @RequestMapping(value = "/employment/employments")
    @ResponseBody
    public ResponseEntity<String> getData(HttpSession session, ModelMap model) {
        EmploymentDao dao = new EmploymentDao();
        EmployeeDao empDao = new EmployeeDao();
        DictionaryDao dictDao = new DictionaryDao();
        Map<String, Object> initData = new HashMap<String, Object>();
        initData.put("employments", dao.selectAll());
        initData.put("employees", empDao.getEmployeeDtoList());
        initData.put("dictionaries", dictDao.getEmploymentTypes());
        return Utils.createResponseEntity(session, initData);
    }
    
    @RequestMapping(value = "/employment/save/:object.htm")
    @ResponseBody
    public ResponseEntity<String> save(@RequestBody String object, HttpSession session) {
        EmploymentDto dto = (EmploymentDto) Utils.convertJSONStringToObject(object, "object", EmploymentDto.class);
        EmploymentDao empDao = new EmploymentDao();
        Employment emp = new Employment();
        Map<String, Object> data = new HashMap<>();
        if(dto.getId() != null ){            
            emp = empDao.selectRecordsWithFieldValues("id", dto.getId()).get(0);
            emp.setDateFrom(dto.getDateFrom());
            emp.setDateTo(dto.getDateTo());
            emp.setDictId(dto.getDictId()+"");
            emp.setEmployeeId(dto.getEmployeeId()+"");
            empDao.update(emp);
            data.put("id", dto.getId());
            return Utils.createResponseEntity(session, data);
        } else {
            emp.setDateFrom(dto.getDateFrom());
            emp.setDateTo(dto.getDateTo());
            emp.setDictId(dto.getDictId()+"");
            emp.setEmployeeId(dto.getEmployeeId()+"");
            data.put("id", empDao.insert(emp));
            return Utils.createResponseEntity(session, data);
        }
    }
    
    @RequestMapping(value = "/employment/delete/:object", method = RequestMethod.POST)
    public @ResponseBody
    void deleteData(@RequestBody String object) {
        EmploymentDto dto = (EmploymentDto) Utils.convertJSONStringToObject(object, "object", EmploymentDto.class);
        EmploymentDao empDao = new EmploymentDao();
        if (dto.getId() != null) {
            empDao.delete("id="+dto.getId());
        }
    }
}
