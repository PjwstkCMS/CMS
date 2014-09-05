/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.edu.pjwstk.cms.controllers;


import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.pjwstk.cms.controllers.general.BaseController;
/**
 *
 * @author Konrad
 */
@Controller
public class KeyController extends BaseController {

    private final static Logger LOGGER = Logger.getLogger(KeyController.class.getName());

    public KeyController() {

    }

    @Override
    @RequestMapping("key")
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ModelAndView model = new ModelAndView("key");
        model.addObject("msg", "HelloGuestController");
        
        return model;
    }
}
