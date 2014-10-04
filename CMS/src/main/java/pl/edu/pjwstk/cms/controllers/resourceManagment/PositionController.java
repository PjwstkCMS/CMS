/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this tposlate file, choose Tools | Tposlates
 * and open the tposlate in the editor.
 */

package pl.edu.pjwstk.cms.controllers.resourceManagment;


import java.util.HashMap;
import java.util.List;
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
import pl.edu.pjwstk.cms.dao.DepartmentDao;
import pl.edu.pjwstk.cms.dao.EmployeeDao;
import pl.edu.pjwstk.cms.dao.PositionDao;
import pl.edu.pjwstk.cms.dto.EmployeeDto;
import pl.edu.pjwstk.cms.models.Position;
import pl.edu.pjwstk.cms.utils.Utils;
/**
 *
 * @author Konrad
 */
@Controller
public class PositionController extends BaseController {

    private final static Logger LOGGER = Logger.getLogger(PositionController.class.getName());

    public PositionController() {

    }

    @Override
    @RequestMapping("position")
    protected ModelAndView home(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView model = new ModelAndView("position");        
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
}
