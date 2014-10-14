/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.pjwstk.cms.controllers.configuration;

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
import pl.edu.pjwstk.cms.dao.PrivilegeKeyDao;
import pl.edu.pjwstk.cms.utils.Utils;

/**
 *
 * @author Konrad
 */
@Controller
public class KeyController extends BaseController {

    private final static Logger LOGGER = Logger.getLogger(KeyController.class.getName());

    public KeyController() {
        super("ManagePrivilegeKeys","all");
    }

    @Override
    @RequestMapping("key")
    protected ModelAndView home(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (!checkPrivileges(request)) {
            ModelAndView model = new ModelAndView("accessdenied");
            return model;
        }
        ModelAndView model = new ModelAndView("key");
        PrivilegeKeyDao privilegeKeyDao = new PrivilegeKeyDao();
        privilegeKeyDao.selectAll();
        return model;
    }

    @RequestMapping("privilegeKeyList/privKeys.htm")
    @ResponseBody
    public ResponseEntity<String> getData(HttpServletRequest request, HttpSession session, ModelMap model) {
        //UserConfigurationDao userConfigDao = new UserConfigurationDao();
        PrivilegeKeyDao privilegeKeyDao = new PrivilegeKeyDao();
        Map<String, Object> initData = new HashMap<>();
        initData.put("privilegeKeys", privilegeKeyDao.selectAll());
        return Utils.createResponseEntity(request.getSession(), initData);
    }
}
