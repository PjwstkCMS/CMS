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
import pl.edu.pjwstk.cms.dao.DictionaryDao;
import pl.edu.pjwstk.cms.dao.EmployeeDao;
import pl.edu.pjwstk.cms.dao.TaskDao;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.dto.TaskDto;
import pl.edu.pjwstk.cms.dto.UserDto;
import pl.edu.pjwstk.cms.models.Task;
import pl.edu.pjwstk.cms.utils.Utils;


@Controller
public class TaskController extends BaseController{
    
    private final static Logger LOGGER = Logger.getLogger(TaskController.class.getName());

    public TaskController() {
        super("ManageTasks","all");
    }

    @Override
    @RequestMapping("task")
    protected ModelAndView home(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if(!checkPrivileges(request)) {
            ModelAndView model = new ModelAndView("accessdenied");
            return model;
        }
        ModelAndView model = new ModelAndView("task");
        model.addObject("msg", "HelloGuestController");
        model.addObject("server", GenericDao.server);
        
        return model;
    }
    
    @RequestMapping(value = "/task/tasks")
    @ResponseBody
    public ResponseEntity<String> getData(HttpSession session, ModelMap model) {
        EmployeeDao empDao = new EmployeeDao();
        TaskDao taskDao = new TaskDao();
        DictionaryDao dictDao = new DictionaryDao();
        
        UserDto us = (UserDto)session.getAttribute("user");
        Long empId = us.getEmployeeId();
        
        Map<String, Object> initData = new HashMap<String, Object>();
        
        List<String> privs = us.getPrivilegeKeyCodes();
        if(privs.contains("all")){
            initData.put("employees", empDao.getEmployeeDtoList());
            initData.put("tasks", taskDao.getTaskDtoList());
        }else{
            initData.put("employees", empDao.getManagerEmployeesDtoList(empId));
            initData.put("tasks", taskDao.getManagerTaskDtoList(empId));
        }
        
        initData.put("dictionaries",dictDao.getTaskPriorityTypes());
        initData.put("managerId",empId);
        
        return Utils.createResponseEntity(session, initData);
    }
    
    @RequestMapping(value = "/task/save/:object", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> saveData(@RequestBody String object, HttpSession session) {
        TaskDto taskDto = (TaskDto) Utils.convertJSONStringToObject(object, "object", TaskDto.class);
        TaskDao taskDao = new TaskDao();
        Task task = new Task();
        Map<String, Object> data = new HashMap<>();
        if(taskDto.getId() != null ){
            task = taskDao.selectRecordsWithFieldValues("id", taskDto.getId()).get(0);
            task.setEmployeeId(taskDto.getEmployeeId()+"");
            
            if(taskDto.getEmployeeId()!= null && taskDto.getEmployeeId() != -1){
                task.setEmployeeId(taskDto.getEmployeeId()+"");
            }else{
                task.setEmployeeId("-1");
            }
            task.setManagerId(taskDto.getManagerId()+"");
            
            task.setStartDate(taskDto.getStartDate());
            task.setCloseDate(taskDto.getCloseDate());
            if(taskDto.getFinalisationDate() != null && taskDto.getFinalisationDate() != ""){
                task.setFinalisationDate(taskDto.getFinalisationDate());
            }else{
                task.setFinalisationDate("NULL");
            }
            task.setDescription(taskDto.getDescription());
            task.setDictId(taskDto.getDictId()+"");
            taskDao.update(task);
            data.put("id", taskDto.getId());
            return Utils.createResponseEntity(session, data);
        } else {
            if(taskDto.getEmployeeId()!= null && taskDto.getEmployeeId() != -1){
                task.setEmployeeId(taskDto.getEmployeeId()+"");
            }else{
                task.setEmployeeId("-1");
            }
            task.setManagerId(taskDto.getManagerId()+"");
            task.setStartDate(taskDto.getStartDate());
            task.setCloseDate(taskDto.getCloseDate());
            if(taskDto.getFinalisationDate() != null && taskDto.getFinalisationDate() != ""){
                task.setFinalisationDate(taskDto.getFinalisationDate());
            }else{
                task.setFinalisationDate("NULL");
            }
            task.setDescription(taskDto.getDescription());
            task.setDictId(taskDto.getDictId()+"");
            data.put("id", taskDao.insert(task));
            return Utils.createResponseEntity(session, data);
        }
    }
    
    @RequestMapping(value = "/task/delete/:object", method = RequestMethod.POST)
    public @ResponseBody
    void deleteData(@RequestBody String object) {
        TaskDto taskDto = (TaskDto) Utils.convertJSONStringToObject(object, "object", TaskDto.class);
        TaskDao taskDao = new TaskDao();
        if (taskDto.getId() != null) {
            taskDao.delete("id="+taskDto.getId());
        }
    }
    
}
