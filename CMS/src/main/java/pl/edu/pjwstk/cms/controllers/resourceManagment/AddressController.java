package pl.edu.pjwstk.cms.controllers.resourceManagment;

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
import pl.edu.pjwstk.cms.dao.AddressDao;
import pl.edu.pjwstk.cms.dto.AddressDto;
import pl.edu.pjwstk.cms.models.Address;
import pl.edu.pjwstk.cms.utils.Utils;


@Controller
public class AddressController extends BaseController {

    private final static Logger LOGGER = Logger.getLogger(AddressController.class.getName());

    public AddressController() {

    }
    
    @RequestMapping(value = "/address/save/:object", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> saveData(@RequestBody String object, HttpSession session) {
        AddressDto addDto = (AddressDto) Utils.convertJSONStringToObject(object, "object", AddressDto.class);
        AddressDao addDao = new AddressDao();
        Address address = new Address();
        Map<String, Object> data = new HashMap<>();
        if(addDto.getId() != null ){
            address = addDao.selectRecordsWithFieldValues("id", addDto.getId()).get(0);
            address.setCountry(addDto.getCountry());
            address.setCity(addDto.getCity());
            address.setStreetName(addDto.getStreetName());
            address.setStreetNumber(addDto.getStreetNumber());
            address.setApartmentNumber(addDto.getApartmentNumber());
            address.setPostalCode(addDto.getPostalCode());
            address.setDictId(addDto.getDictId()+"");
            
            if(addDto.getCompanyId() == null){
                address.setCompanyId("-1");
            }else{
                address.setCompanyId(addDto.getCompanyId()+"");
            }
            
            if(addDto.getPersondataId()== null){
                address.setPersondataId("-1");
            }else{
                address.setPersondataId(addDto.getPersondataId()+"");
            }
            addDao.update(address);
            data.put("id", addDto.getId());
            return Utils.createResponseEntity(session, data);
        } else {
            address.setCountry(addDto.getCountry());
            address.setCity(addDto.getCity());
            address.setStreetName(addDto.getStreetName());
            address.setStreetNumber(addDto.getStreetNumber());
            address.setApartmentNumber(addDto.getApartmentNumber());
            address.setPostalCode(addDto.getPostalCode());
            address.setDictId(addDto.getDictId()+"");
            
            if(addDto.getCompanyId() == null){
                address.setCompanyId("-1");
            }else{
                address.setCompanyId(addDto.getCompanyId()+"");
            }
            
            if(addDto.getPersondataId()== null){
                address.setPersondataId("-1");
            }else{
                address.setPersondataId(addDto.getPersondataId()+"");
            }
            data.put("id", addDao.insert(address));
            return Utils.createResponseEntity(session, data);
        }
    }
    
    @RequestMapping(value = "/address/delete/:object", method = RequestMethod.POST)
    public @ResponseBody
    void deleteData(@RequestBody String object) {
        System.out.println("delete");
        AddressDto dto = (AddressDto) Utils.convertJSONStringToObject(object, "object", AddressDto.class);
        if (dto != null) {
            AddressDao addDao = new AddressDao();
            addDao.delete("id=" + dto.getId());
        }
    }
}

