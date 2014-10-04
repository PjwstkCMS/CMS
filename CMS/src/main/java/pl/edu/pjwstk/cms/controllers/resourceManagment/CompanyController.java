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
import pl.edu.pjwstk.cms.dao.CompanyDao;
import pl.edu.pjwstk.cms.dao.DictionaryDao;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.utils.Utils;
/**
 *
 * @author Konrad
 */
@Controller
public class CompanyController extends BaseController {

    private final static Logger LOGGER = Logger.getLogger(CompanyController.class.getName());

    public CompanyController() {

    }

    @Override
    @RequestMapping("company")
    protected ModelAndView home(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ModelAndView model = new ModelAndView("company");
        model.addObject("msg", "HelloGuestController");
        model.addObject("server", GenericDao.server);
        
        return model;
    }
    @RequestMapping(value = "/company/companies")
    @ResponseBody
    public ResponseEntity<String> getData(HttpSession session, ModelMap model) {
        CompanyDao comDao = new CompanyDao();
        DictionaryDao dictDao = new DictionaryDao();
        Map<String, Object> initData = new HashMap<String, Object>();
        initData.put("companies", comDao.getCompanyDtoList());
        initData.put("dictionaries", dictDao.getCompanyAddressesTypes());
        return Utils.createResponseEntity(session, initData);
    }
}

