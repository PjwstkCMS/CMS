package pl.edu.pjwstk.cms.controllers.configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.edu.pjwstk.cms.controllers.general.BaseController;
import pl.edu.pjwstk.cms.dao.DictionaryDao;
import pl.edu.pjwstk.cms.dto.DictionaryDto;
import pl.edu.pjwstk.cms.models.Dictionary;
import pl.edu.pjwstk.cms.utils.Utils;


@Controller
public class DictionaryController extends BaseController {

    private final static Logger LOGGER = Logger.getLogger(DictionaryController.class.getName());

    public DictionaryController() {
        super("ManageDictionaries","all");
    }

    @RequestMapping(value = "/dictionary/save/:object", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> saveData(@RequestBody String object, HttpSession session) {
        DictionaryDto dictDto = (DictionaryDto) Utils.convertJSONStringToObject(object, "object", DictionaryDto.class);
        DictionaryDao dictDao = new DictionaryDao();
        Dictionary dict = new Dictionary();
        Map<String, Object> data = new HashMap<>();
        if(dictDto.getId() != null ){
            dict = dictDao.selectRecordsWithFieldValues("id", dictDto.getId()).get(0);
            dict.setDescription(dictDto.getDescription());
            dict.setValue(dictDto.getValue());
            dict.setDictTypeId(dictDto.getDictTypeId()+"");
            
            dictDao.update(dict);
            data.put("id", dictDto.getId());
            return Utils.createResponseEntity(session, data);
        } else {
            dict.setDescription(dictDto.getDescription());
            dict.setValue(dictDto.getValue());
            dict.setDictTypeId(dictDto.getDictTypeId()+"");
            
            data.put("id", dictDao.insert(dict));
            return Utils.createResponseEntity(session, data);
        }
    }
    
    @RequestMapping(value = "/dictionary/delete/:object", method = RequestMethod.POST)
    public @ResponseBody
    void deleteData(@RequestBody String object) {
        System.out.println("delete");
        DictionaryDto dto = (DictionaryDto) Utils.convertJSONStringToObject(object, "object", DictionaryDto.class);
        if (dto != null) {
            DictionaryDao dictDao = new DictionaryDao();
            dictDao.delete("id=" + dto.getId());
        }
    }
}

