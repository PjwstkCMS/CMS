package pl.edu.pjwstk.cms.dto;

import java.io.Serializable;
import java.util.logging.Logger;

public class DictionaryDto implements Serializable{
    
    private final static Logger LOGGER = Logger.getLogger(AddressDto.class.getName()); 

    private Long id, dictTypeId;
    private String value, description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDictTypeId() {
        return dictTypeId;
    }

    public void setDictTypeId(Long dictTypeId) {
        this.dictTypeId = dictTypeId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
