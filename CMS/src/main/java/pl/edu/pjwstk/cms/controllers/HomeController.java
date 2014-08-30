/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.edu.pjwstk.cms.controllers;

import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.pjwstk.cms.controllers.general.BaseController;

/**
 *
 * @author Macha
 */
public class HomeController extends BaseController{
    
    private final static Logger LOGGER = Logger.getLogger(KeyController.class.getName());

    public HomeController() {

    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ModelAndView model = new ModelAndView("home");
        model.addObject("msg", "HelloGuestController");
        
        return model;
    }
}
