package pl.edu.pjwstk.cms.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.pjwstk.cms.controllers.general.BaseController;
import pl.edu.pjwstk.cms.dao.MessageDao;
import pl.edu.pjwstk.cms.dao.PersonDataDao;
import pl.edu.pjwstk.cms.dao.TaskDao;
import pl.edu.pjwstk.cms.dao.UserDao;
import pl.edu.pjwstk.cms.dto.UserDto;
import pl.edu.pjwstk.cms.models.Message;
import pl.edu.pjwstk.cms.models.PersonData;
import pl.edu.pjwstk.cms.models.User;
import pl.edu.pjwstk.cms.utils.HexConverter;
import pl.edu.pjwstk.cms.utils.Utils;


@Controller
public class HomeController extends BaseController {

    private final static Logger LOGGER = Logger.getLogger(HomeController.class.getName());

    public HomeController() {

    }

    @Override
    @RequestMapping("home")
    protected ModelAndView home(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView model = new ModelAndView("home");
        return model;
    }

    @RequestMapping(value = "getUserImage")
    public void getUserImage(HttpServletRequest request, HttpServletResponse response) throws IOException {

        UserDto userDto = (UserDto) request.getSession().getAttribute("user");
        if (!userDto.getPhotoHash().isEmpty()) {
            java.io.File file = null;
            try {
                file = Utils.getFileFromHash(userDto.getPhotoHash());
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            byte[] buffer = HexConverter.toBytesFromHex(userDto.getPhotoHash());
            response.setContentType("image/jpeg");
            InputStream in1 = new ByteArrayInputStream(buffer);
            IOUtils.copy(in1, response.getOutputStream());
        }
    }
    
    @RequestMapping(value = "getUserTasks")
    public void getUserTasks(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/HTML");
        TaskDao taskDao = new TaskDao();
        UserDto userDto = (UserDto) request.getSession().getAttribute("user");
        Long empId = userDto.getEmployeeId();
        
        Map<String, Object> initData = new HashMap<String, Object>();
        initData.put("tasks", taskDao.getEmployeeTaskDtoList(empId));
        response.getOutputStream().print(Utils.convertOMapToJSON(initData));
        

    }

    @RequestMapping(value = "sendMessage", method = RequestMethod.POST)
    public ModelAndView sendMessage(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("home");
        UserDto userDto = (UserDto) request.getSession().getAttribute("user");
        //UserDao userDao = new UserDao();
        //User fromUser = userDao.selectForId(userDto.getId());
        String sendTo = request.getParameter("sendTo");
        String content = request.getParameter("message");
        Message m = new Message();
        m.setFrom_userid(userDto.getId()+"");
        m.setContent(content);
        m.setTo_userid(sendTo);
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        m.setTimestamp(format.format(today));
        m.setRead("0");
        MessageDao mesDao = new MessageDao();
        mesDao.insert(m);
        return model;
    }

    @RequestMapping(value = "uploadPhoto", method = RequestMethod.POST)
    public ModelAndView uploadPhoto(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("home");
        UserDto userDto = (UserDto) request.getSession().getAttribute("user");
        UserDao userDao = new UserDao();
        User user = userDao.selectForId(userDto.getId());
        try {
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            FileItem item = items.get(0);
            if (item.getSize() == 0) {
                model.addObject("error", "Nie wybrano żadnego pliku");
                return model;
                //System.out.println("no file");
            }
            if (!item.getContentType().equals("image/jpeg")
                    && !item.getContentType().equals("image/png")
                    && !item.getContentType().equals("image/gif")) {
                model.addObject("error", "Plik musi posiadać rozszerzenie jpg, png lub gif.");
                return model;
            }
            InputStream input = item.getInputStream();
            byte[] barr = new byte[(int) item.getSize()];
            input.read(barr);
            String s = HexConverter.toHexFromBytes(barr);
            user.setPhotoHash(s);
            userDto.setPhotoHash(s);
            userDao.update(user);
        } catch (FileUploadException ex) {
            System.err.println("Can't process file\n" + ex.getMessage());
            ex.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }
        request.getSession().setAttribute("user", userDto);
        return model;
    }
    
    @RequestMapping(value = "changeUserData", method = RequestMethod.POST)
    public ModelAndView changeUserData(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("home");
        UserDto userDto = (UserDto) request.getSession().getAttribute("user");
        //PersonData personData = (PersonData) request.getSession().getAttribute("userData");
        //UserDao userDao = new UserDao();
        //User fromUser = userDao.selectForId(userDto.getId());
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        PersonDataDao personDataDao = new PersonDataDao();
        PersonData personData = personDataDao.selectSingleRecord("id", userDto.getPersondataId());
        personData.setEmail(email);
        personData.setPhone(phone);
        personDataDao.update(personData);
        request.getSession().setAttribute("userData", personData);
        return model;
    }
    
    @RequestMapping(value = "changeUserPassword", method = RequestMethod.POST)
    public ModelAndView changeUserPassword(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("home");
        UserDto userDto = (UserDto) request.getSession().getAttribute("user");
        //PersonData personData = (PersonData) request.getSession().getAttribute("userData");
        UserDao userDao = new UserDao();
        User user = userDao.selectForId(userDto.getId());
        String oldPassword = request.getParameter("oldPassword");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        if(userDto.getPassword().equals(oldPassword)) {
            if(password1.equals(password2)) {
                userDto.setPassword(password2);
                user.setPassword(password2);
                userDao.update(user);
                request.getSession().setAttribute("user", userDto);
            } else {
                model.addObject("passwordChangeError", "Błąd nowego hasła");
            }
        } else {
            model.addObject("passwordChangeError", "Stare hasło nieprawidłowe");            
        }        
        model.addObject("passwordChangeError", "Hasło zmienione");            
        return model;
    }

}
