package pl.edu.pjwstk.cms.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class UserDto implements Serializable{
    
    private final static Logger LOGGER = Logger.getLogger(UserDto.class.getName()); 

    private Long id, employeeId, groupId, persondataId;
    private String login, password, employee, group, photoHash;
    private List<String> privilegeKeyCodes = new ArrayList<>();
    
    public UserDto() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getPersondataId() {
        return persondataId;
    }

    public void setPersondataId(Long persondataId) {
        this.persondataId = persondataId;
    }

    public String getPhotoHash() {
        return photoHash;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setPhotoHash(String photoHash) {
        this.photoHash = photoHash;
    }

    public List<String> getPrivilegeKeyCodes() {
        return privilegeKeyCodes;
    }

    public void setPrivilegeKeyCodes(List<String> privilegeKeyCodes) {
        this.privilegeKeyCodes = privilegeKeyCodes;
    }
    
}
