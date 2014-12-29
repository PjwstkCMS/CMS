/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.pjwstk.cms.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static javax.swing.UIManager.put;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.pjwstk.cms.controllers.general.BaseController;
import pl.edu.pjwstk.cms.dao.MessageDao;
import pl.edu.pjwstk.cms.dao.UserDao;
import pl.edu.pjwstk.cms.dto.MessageDto;
import pl.edu.pjwstk.cms.dto.UserDto;
import pl.edu.pjwstk.cms.models.Message;
import pl.edu.pjwstk.cms.utils.Utils;

/**
 *
 * @author sergio
 */
@Controller
public class ChatController extends BaseController {

    private final static Logger LOGGER = Logger.getLogger(HomeController.class.getName());

    public ChatController() {

    }

    @RequestMapping(value = "getUserMessages")
    public ModelAndView getUserMessages(HttpServletRequest request, HttpServletResponse response) throws IOException {
        MessageDao mesDao = new MessageDao();
        ModelAndView model = new ModelAndView("chat");
        UserDto userDto = (UserDto) request.getSession().getAttribute("user");
        UserDao userDao = new UserDao();
        //List<Message> messages = mesDao.selectRecordsWithFieldValues("to_userid", userDto.getId());
        /*
         UserDto userDto = (UserDto) request.getSession().getAttribute("user");
         UserDao userDao = new UserDao();
         List<Message> messages = mesDao.selectRecordsWithFieldValues("to_userid", userDto.getId());
         response.setContentType("text/HTML");
         String content = "";
         for (Message m : messages) {
         User user = userDao.selectForId(m.getFrom_userid());
         content += user.getLogin() + "<br/>";
         content += m.getTimestamp() + "<br/>";
         content += m.getContent() + "<br/>";
         content += "<input type='button' ng-click='' value='Przeczytane'></input><br/>";
         content += "<hr/><br/>";
         }
         response.getOutputStream().print(content); */
        return model;
    }

    @RequestMapping(value = "/messages/get")
    @ResponseBody
    public ResponseEntity<String> getData(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model) {
        UserDto userDto = (UserDto) request.getSession().getAttribute("user");
        UserDao userDao = new UserDao();
        MessageDao mesDao = new MessageDao();
        Map<String, Object> initData = new HashMap<String, Object>();
        Map<String, List<String>> params = new HashMap<>();
        List<String> paramValues = new ArrayList<>();
        paramValues.add(userDto.getId()+"");
        params.put("to_userid", paramValues);
        List<MessageDto> messages = mesDao.getMessageDtos(params);
        initData.put("messages", messages);
        initData.put("users", userDao.getUserNames());
        return Utils.createResponseEntity(session, initData);
    }

    @RequestMapping(value = "/messages/:message")
    @ResponseBody
    ResponseEntity<String> saveData(@RequestBody String message, HttpSession session) {
        MessageDto dto = (MessageDto) Utils.convertJSONStringToObject(message, "message", MessageDto.class);
        MessageDao msgDao = new MessageDao();
        Message actualMessage = msgDao.selectForId(dto.getId());
        actualMessage.setRead("1");
        msgDao.update(actualMessage);
        return null;
        //Map<String, Object> data = new HashMap<>();
        //return Utils.createResponseEntity(session, data);
    }
}
