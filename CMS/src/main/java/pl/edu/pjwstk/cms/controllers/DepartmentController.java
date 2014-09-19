/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import pl.edu.pjwstk.cms.dao.DepartmentDao;
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
        
        return model;
    }
    @RequestMapping(value = "/department/departments")
    @ResponseBody
    public ResponseEntity<String> getData(HttpSession session, ModelMap model) {
        DepartmentDao depDao = new DepartmentDao();
        Map<String, Object> initData = new HashMap<String, Object>();
        initData.put("departments", depDao.getDepartmentDtoList());
        return Utils.createResponseEntity(session, initData);
    }
}
