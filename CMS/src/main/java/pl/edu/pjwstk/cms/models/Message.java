/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.pjwstk.cms.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.logging.Logger;
import pl.edu.pjwstk.cms.models.general.DatabaseObject;

/**
 *
 * @author sergio
 */
public class Message extends DatabaseObject{
    
    private final static Logger LOGGER = Logger.getLogger(Log.class.getName());
    
    //@JsonIgnore
    //public final static String NOT_READ = "0";
    //@JsonIgnore
    //public final static String READ = "1";
    
    private String timestamp;
    private String from_userid;
    private String to_userid;
    private String content;
    private String ifread;
    
    public Message() {
        
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getFrom_userid() {
        return from_userid;
    }

    public void setFrom_userid(String from_userid) {
        this.from_userid = from_userid;
    }

    public String getTo_userid() {
        return to_userid;
    }

    public void setTo_userid(String to_userid) {
        this.to_userid = to_userid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRead() {
        return ifread;
    }

    public void setRead(String read) {
        this.ifread = read;
    }
    
    public boolean getBooleanRead() {
        return !"0".equals(ifread);
    }
    
    public void setBooleanRead(Boolean bool) {
        if(bool) {
            ifread = "1";
        } else {
            ifread = "0'";
        }
    }
    
    
    
}
