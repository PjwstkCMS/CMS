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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.pjwstk.cms.controllers.general.BaseController;
import pl.edu.pjwstk.cms.dao.AddressDao;
import pl.edu.pjwstk.cms.dao.CompanyDao;
import pl.edu.pjwstk.cms.dao.CustomerDao;
import pl.edu.pjwstk.cms.dao.DictionaryDao;
import pl.edu.pjwstk.cms.dao.PersonDataDao;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.dto.CompanyDto;
import pl.edu.pjwstk.cms.models.Company;
import pl.edu.pjwstk.cms.models.Customer;
import pl.edu.pjwstk.cms.models.PersonData;
import pl.edu.pjwstk.cms.utils.Utils;


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
    
    @RequestMapping(value = "/company/save/:object", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> saveData(@RequestBody String object, HttpSession session) {
        CompanyDto compDto = (CompanyDto) Utils.convertJSONStringToObject(object, "object", CompanyDto.class);
        CompanyDao compDao = new CompanyDao();
        Company comp = new Company();
        PersonDataDao perDao = new PersonDataDao();
        PersonData per = new PersonData();
        Map<String, Object> data = new HashMap<>();
        if(compDto.getId() != null ){
            comp = compDao.selectRecordsWithFieldValues("id", compDto.getId()).get(0);
            comp.setName(compDto.getName());
            if(compDto.getContactPersonId()==-1){
                per.setForename(compDto.getForename());
                per.setSurname(compDto.getSurname());
                per.setEmail(compDto.getEmail());
                per.setPhone(compDto.getPhone());
                Long id = perDao.insert(per);
                comp.setContactpersonId(id+"");
                data.put("contactPersonId", id);
            }else{
                per.setForename(compDto.getForename());
                per.setSurname(compDto.getSurname());
                per.setEmail(compDto.getEmail());
                per.setPhone(compDto.getPhone());
                per.setId(compDto.getContactPersonId());
                perDao.update(per);
                comp.setContactpersonId(compDto.getContactPersonId()+"");
            }
            compDao.update(comp);
            data.put("id", compDto.getId());
            return Utils.createResponseEntity(session, data);
        } else {
            comp.setName(compDto.getName());
            if(compDto.getContactPersonId()==null){
                per.setForename(compDto.getForename());
                per.setSurname(compDto.getSurname());
                per.setEmail(compDto.getEmail());
                per.setPhone(compDto.getPhone());
                Long id = perDao.insert(per);
                comp.setContactpersonId(id+"");
                data.put("contactPersonId", id);
            }else{
                per.setForename(compDto.getForename());
                per.setSurname(compDto.getSurname());
                per.setEmail(compDto.getEmail());
                per.setPhone(compDto.getPhone());
                per.setId(compDto.getContactPersonId());
                perDao.update(per);
                comp.setContactpersonId(compDto.getContactPersonId()+"");
            }
            data.put("id", compDao.insert(comp));
            return Utils.createResponseEntity(session, data);
        }
    }
    
    @RequestMapping(value = "/company/delete/:object", method = RequestMethod.POST)
    public @ResponseBody
    void deleteData(@RequestBody String object) {
        CompanyDto compDto = (CompanyDto) Utils.convertJSONStringToObject(object, "object", CompanyDto.class);
        CompanyDao compDao = new CompanyDao();
        AddressDao addDao = new AddressDao();
        PersonDataDao perDao = new PersonDataDao();
        CustomerDao cusDao = new CustomerDao();
        if (compDto.getId() != null) {
            compDao.delete("id="+compDto.getId());
            addDao.delete("companyId="+compDto.getId());
            if(compDto.getContactPersonId() != -1){
                perDao.delete("id="+compDto.getContactPersonId());
            }
            List<Customer> cusList = cusDao.selectAll();
            for(Customer cus : cusList){
                if(Long.parseLong(cus.getCompanyId()) == compDto.getId()){
                    perDao.delete("id="+cus.getPersondataId());
                }
            }
            cusDao.delete("companyId="+compDto.getId());
        }
    }
}

