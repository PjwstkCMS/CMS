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
import pl.edu.pjwstk.cms.dao.AddressDao;
import pl.edu.pjwstk.cms.dao.CardDao;
import pl.edu.pjwstk.cms.dao.ContractDao;
import pl.edu.pjwstk.cms.dao.DepartmentDao;
import pl.edu.pjwstk.cms.dao.DictionaryDao;
import pl.edu.pjwstk.cms.dao.EmployeeDao;
import pl.edu.pjwstk.cms.dao.EmploymentDao;
import pl.edu.pjwstk.cms.dao.PersonDataDao;
import pl.edu.pjwstk.cms.dao.PositionDao;
import pl.edu.pjwstk.cms.dao.UserDao;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.dto.EmployeeDto;
import pl.edu.pjwstk.cms.models.Employee;
import pl.edu.pjwstk.cms.models.PersonData;
import pl.edu.pjwstk.cms.models.Position;
import pl.edu.pjwstk.cms.utils.Utils;


@Controller
public class EmployeeController extends BaseController {

    private final static Logger LOGGER = Logger.getLogger(EmployeeController.class.getName());

    public EmployeeController() {

    }

    @Override
    @RequestMapping("employee")
    protected ModelAndView home(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ModelAndView model = new ModelAndView("employee");
        model.addObject("msg", "HelloGuestController");
        model.addObject("server", GenericDao.server);
        
        return model;
    }

    @RequestMapping(value = "/employee/employees")
    @ResponseBody
    public ResponseEntity<String> getData(HttpSession session, ModelMap model) {
        EmployeeDao empDao = new EmployeeDao();
        PositionDao posDao = new PositionDao();
        DepartmentDao depDao = new DepartmentDao();
        DictionaryDao dicDao = new DictionaryDao();
        
        CardDao carDao = new CardDao();
        EmploymentDao emplDao = new EmploymentDao();
        ContractDao conDao = new ContractDao();
        Map<String, Object> initData = new HashMap<String, Object>();
        initData.put("employees", empDao.getEmployeeDtoList());
        initData.put("cards", carDao.selectAll());
        initData.put("employments", emplDao.selectAll());
        initData.put("contracts", conDao.selectAll());
        initData.put("positions", posDao.selectAll());
        initData.put("departments", depDao.selectAll());
        initData.put("dictionaries", dicDao.getPersonAddressesTypes());
        return Utils.createResponseEntity(session, initData);
    }
    
    @RequestMapping(value = "/employee/save/:object.htm")
    @ResponseBody
    public ResponseEntity<String> save(@RequestBody String object, HttpSession session) {
        EmployeeDto dto = (EmployeeDto) Utils.convertJSONStringToObject(object, "object", EmployeeDto.class);
        EmployeeDao empDao = new EmployeeDao();
        Employee emp = new Employee();
        PersonDataDao perDao = new PersonDataDao();
        PersonData per = new PersonData();
        Position pos = new Position();
        Map<String, Object> data = new HashMap<>();
        if(dto.getId() != null ){            
            emp = empDao.selectRecordsWithFieldValues("id", dto.getId()).get(0);
            emp.setDepartmentId(dto.getDepartmentId()+"");
            emp.setPositionId(dto.getPositionId()+"");
            emp.setSalary(dto.getSalary());
            per = perDao.selectRecordsWithFieldValues("id", dto.getPersondataId()).get(0);
            per.setForename(dto.getForename());
            per.setSurname(dto.getSurname());
            per.setPhone(dto.getPhone());
            per.setPesel(dto.getPesel());
            per.setEmail(dto.getEmail());
            emp.setPersondataId(dto.getPersondataId()+"");
            
            perDao.update(per);
            empDao.update(emp);
            data.put("id", dto.getId());
            return Utils.createResponseEntity(session, data);
        } else {
            emp.setDepartmentId(dto.getDepartmentId()+"");
            emp.setPositionId(dto.getPositionId()+"");
            emp.setSalary(dto.getSalary());
            per.setForename(dto.getForename());
            per.setSurname(dto.getSurname());
            per.setPhone(dto.getPhone());
            per.setPesel(dto.getPesel());
            per.setEmail(dto.getEmail());
            
            emp.setPersondataId(perDao.insert(per)+"");
            data.put("id", empDao.insert(emp));
            return Utils.createResponseEntity(session, data);
        }
    }
    
    @RequestMapping(value = "/employee/delete/:object", method = RequestMethod.POST)
    public @ResponseBody
    void deleteData(@RequestBody String object) {
        EmployeeDto dto = (EmployeeDto) Utils.convertJSONStringToObject(object, "object", EmployeeDto.class);
        EmployeeDao empDao = new EmployeeDao();
        EmploymentDao employDao = new EmploymentDao();
        PersonDataDao perDao = new PersonDataDao();
        AddressDao addDao = new AddressDao();
        ContractDao conDao = new ContractDao();
        UserDao userDao = new UserDao();
        if (dto.getId() != null) {
            perDao.delete("id="+dto.getPersondataId());
            addDao.delete("persondataId="+dto.getPersondataId());
            userDao.delete("employeeId="+dto.getId());
            employDao.delete("employeeId="+dto.getId());
            conDao.updateFieldForAllElementsWithId(
                    "employeeId", dto.getId() + "",
                    "employeeId", "-1");
            empDao.delete("id="+dto.getId());
        }
    }
}
