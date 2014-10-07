/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import pl.edu.pjwstk.cms.dao.ContractDao;
import pl.edu.pjwstk.cms.dao.DepartmentDao;
import pl.edu.pjwstk.cms.dao.DictionaryDao;
import pl.edu.pjwstk.cms.dao.EmployeeDao;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.dto.ContractDto;
import pl.edu.pjwstk.cms.dto.DepartmentDto;
import pl.edu.pjwstk.cms.models.Address;
import pl.edu.pjwstk.cms.models.Department;
import pl.edu.pjwstk.cms.utils.Utils;
/**
 *
 * @author Konrad
 */
@Controller
public class DepartmentController extends BaseController {

    private final static Logger LOGGER = Logger.getLogger(DepartmentController.class.getName());

    public DepartmentController() {

    }

    @Override
    @RequestMapping("department")
    protected ModelAndView home(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ModelAndView model = new ModelAndView("department");
        model.addObject("msg", "HelloGuestController");
        model.addObject("server", GenericDao.server);
        
        return model;
    }
    @RequestMapping(value = "/department/departments")
    @ResponseBody
    public ResponseEntity<String> getData(HttpSession session, ModelMap model) {
        DepartmentDao depDao = new DepartmentDao();
        EmployeeDao empDao = new EmployeeDao();
        DictionaryDao dictDao = new DictionaryDao();
        Map<String, Object> initData = new HashMap<String, Object>();
        initData.put("departments", depDao.getDepartmentDtoList());
        initData.put("employees", empDao.getEmployeeDtoList());
        initData.put("dictionaries", dictDao.getCompanyAddressesTypes());
        return Utils.createResponseEntity(session, initData);
    }
    
    @RequestMapping(value = "/department/save/:object", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> saveData(@RequestBody String object, HttpSession session) {
        DepartmentDto dto = (DepartmentDto) Utils.convertJSONStringToObject(object, "object", DepartmentDto.class);
        DepartmentDao depDao = new DepartmentDao();
        Department dep = new Department();
        AddressDao addDao = new AddressDao();
        Map<String, Object> data = new HashMap<>();
        if(dto.getId() != null ){
            dep = depDao.selectRecordsWithFieldValues("id", dto.getId()).get(0);
            dep.setName(dto.getName());
            dep.setManagerId(dto.getManagerId());
            
            Address add = dto.getAddress();
            addDao.update(add);
            dep.setAddressId(dto.getAddressId());
            depDao.update(dep);
            data.put("id", dto.getId());
            return Utils.createResponseEntity(session, data);
        } else {
            dep.setName(dto.getName());
            dep.setManagerId(dto.getManagerId());
            
            Address add = dto.getAddress();
            Long id = addDao.insert(add);
            dep.setAddressId(id+"");
            data.put("addressId", id+"");
            data.put("id", depDao.insert(dep));
            return Utils.createResponseEntity(session, data);
        }
    }
    
    @RequestMapping(value = "/department/delete/:object", method = RequestMethod.POST)
    public @ResponseBody
    void deleteData(@RequestBody String object) {
        DepartmentDto dto = (DepartmentDto) Utils.convertJSONStringToObject(object, "object", DepartmentDto.class);
        DepartmentDao depDao = new DepartmentDao();
        AddressDao addDao = new AddressDao();
        EmployeeDao empDao = new EmployeeDao();
        if (dto.getId() != null) {
            empDao.updateFieldForAllElementsWithId(
                    "departmentId", dto.getId() + "",
                    "departmentId", "-1");
            addDao.delete("id="+dto.getAddressId());
            depDao.delete("id="+dto.getId());
        }
    }
}
