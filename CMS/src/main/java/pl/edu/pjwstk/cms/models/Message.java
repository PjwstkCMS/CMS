/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.pjwstk.cms.models;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.models.general.DatabaseObject;

/**
 *
 * @author sergio
 */
public class Message extends DatabaseObject{
    
    private final static Logger LOGGER = Logger.getLogger(Log.class.getName());
    
    private String timestamp;
    private String from_userid;
    private String to_userid;
    private String content;
    private String read;
    
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
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }
    
    
    
}
