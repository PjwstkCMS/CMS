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
import pl.edu.pjwstk.cms.dao.DepartmentDao;
import pl.edu.pjwstk.cms.dao.EmployeeDao;
import pl.edu.pjwstk.cms.dao.PositionDao;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.dto.PositionDto;
import pl.edu.pjwstk.cms.models.Position;
import pl.edu.pjwstk.cms.utils.Utils;


@Controller
public class PositionController extends BaseController {

    private final static Logger LOGGER = Logger.getLogger(PositionController.class.getName());

    public PositionController() {
        super("ManagePositions","all");
    }

    @Override
    @RequestMapping("position")
    protected ModelAndView home(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if(!checkPrivileges(request)) {
            ModelAndView model = new ModelAndView("accessdenied");
            return model;
        }
        ModelAndView model = new ModelAndView("position");     
        model.addObject("msg", "HelloGuestController");
        model.addObject("server", GenericDao.server);
        
        return model;
    }
    @RequestMapping(value = "/position/positions")
    @ResponseBody
    public ResponseEntity<String> getData(HttpSession session, ModelMap model) {
        PositionDao posDao = new PositionDao();
        EmployeeDao empDao = new EmployeeDao();
        DepartmentDao depDao = new DepartmentDao();
        Map<String, Object> initData = new HashMap<>();
        initData.put("positions", posDao.selectAll());
        initData.put("employees", empDao.getEmployeeDtoList());
        initData.put("departments", depDao.getDepartmentDtoList());
        return Utils.createResponseEntity(session, initData);
    }
    
    @RequestMapping(value = "/position/save/:object.htm")
    @ResponseBody
    public ResponseEntity<String> save(@RequestBody String object, HttpSession session) {
        PositionDto dto = (PositionDto) Utils.convertJSONStringToObject(object, "object", PositionDto.class);
        PositionDao posDao = new PositionDao();
        Position pos = new Position();
        Map<String, Object> data = new HashMap<>();
        if(dto.getId() != null ){            
            pos = posDao.selectRecordsWithFieldValues("id", dto.getId()).get(0);
            pos.setName(dto.getName());
            pos.setDescription(dto.getDescription());
            posDao.update(pos);
            data.put("id", dto.getId());
            return Utils.createResponseEntity(session, data);
        } else {            
            pos.setName(dto.getName());
            pos.setDescription(dto.getDescription());
            data.put("id", posDao.insert(pos));
            return Utils.createResponseEntity(session, data);
        }
    }
    
    @RequestMapping(value = "/position/delete/:object", method = RequestMethod.POST)
    public @ResponseBody
    void deleteData(@RequestBody String object) {
        PositionDto dto = (PositionDto) Utils.convertJSONStringToObject(object, "object", PositionDto.class);
        PositionDao posDao = new PositionDao();
        EmployeeDao empDao = new EmployeeDao();
        if (dto.getId() != null) {
            empDao.updateFieldForAllElementsWithId(
                    "positionId", dto.getId() + "",
                    "positionId", "-1");
            posDao.delete("id="+dto.getId());
        }
    }
}
