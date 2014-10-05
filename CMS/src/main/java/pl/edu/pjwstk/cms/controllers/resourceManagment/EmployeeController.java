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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.pjwstk.cms.controllers.general.BaseController;
import pl.edu.pjwstk.cms.dao.CardDao;
import pl.edu.pjwstk.cms.dao.ContractDao;
import pl.edu.pjwstk.cms.dao.DepartmentDao;
import pl.edu.pjwstk.cms.dao.DictionaryDao;
import pl.edu.pjwstk.cms.dao.EmployeeDao;
import pl.edu.pjwstk.cms.dao.EmploymentDao;
import pl.edu.pjwstk.cms.dao.PositionDao;
import pl.edu.pjwstk.cms.utils.Utils;

/**
 *
 * @author Konrad
 */
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
}
