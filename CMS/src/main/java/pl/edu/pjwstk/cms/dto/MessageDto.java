package pl.edu.pjwstk.cms.dto;

import java.util.logging.Logger;


public class MessageDto {
    
    private final static Logger LOGGER = Logger.getLogger(MessageDto.class.getName()); 
    
    private Long id;
    private String content, fromId, toId, from, timestamp, read;
    
    public MessageDto() {
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }
    
    public boolean getBooleanRead() {
        return !"0".equals(read);
    }
    
    public void setBooleanRead(Boolean bool) {
        if(bool) {
            read = "1";
        } else {
            read = "0'";
        }
    }
}
