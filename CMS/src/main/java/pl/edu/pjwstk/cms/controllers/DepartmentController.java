/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.edu.pjwstk.cms.controllers;


import java.util.ArrayList;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.pjwstk.cms.controllers.general.BaseController;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.models.Customer;
import pl.edu.pjwstk.cms.utils.ConnectionManager;
/**
 *
 * @author Konrad
 */
public class DepartmentController extends BaseController {

    private final static Logger LOGGER = Logger.getLogger(DepartmentController.class.getName());

    public DepartmentController() {

    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ModelAndView model = new ModelAndView("department");
        model.addObject("msg", "HelloGuestController");
        
        return model;
    }
}
