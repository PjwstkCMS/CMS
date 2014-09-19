package pl.edu.pjwstk.cms.dto;

import pl.edu.pjwstk.cms.models.File;

/**
 *
 * @author Macha
 */
public class FileDto {
    
    private Long id;
    private String name, description, mimeType, formCode;
    
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

    public String getFormCode() {
        return formCode;
    }

    public void setFormCode(String formCode) {
        this.formCode = formCode;
    }
    
}