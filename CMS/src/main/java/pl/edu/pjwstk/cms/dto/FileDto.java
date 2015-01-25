package pl.edu.pjwstk.cms.dto;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.models.File;


public class FileDto {
    
    private final static Logger LOGGER = Logger.getLogger(FileDto.class.getName()); 
    
    private Long id;
    private String name, description, mimeType;
    
    public FileDto() {
        
    }
    
    public FileDto(File report) {
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
    
}