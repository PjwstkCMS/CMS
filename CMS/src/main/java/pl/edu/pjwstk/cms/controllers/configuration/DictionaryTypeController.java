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
import pl.edu.pjwstk.cms.dao.DictionaryTypeDao;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.utils.Utils;


@Controller
public class DictionaryTypeController extends BaseController {

    private final static Logger LOGGER = Logger.getLogger(DictionaryTypeController.class.getName());

    public DictionaryTypeController() {
        super("ManageDictionaries","all");
    }

    @Override
    @RequestMapping("dictionaryList")
    protected ModelAndView home(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ModelAndView model = new ModelAndView("dictionaryList");
        model.addObject("msg", "HelloGuestController");
        model.addObject("server", GenericDao.server);
        
        return model;
    }
    
    @RequestMapping("/dictionaryList/dictTypes")
    @ResponseBody
    public ResponseEntity<String> getData(HttpSession session, ModelMap model) {
        DictionaryTypeDao dictTypeDao = new DictionaryTypeDao();
        Map<String, Object> initData = new HashMap<String, Object>();
        initData.put("dictTypes", dictTypeDao.getDictionaryTypeDtos());
        return Utils.createResponseEntity(session, initData);
    }
}

