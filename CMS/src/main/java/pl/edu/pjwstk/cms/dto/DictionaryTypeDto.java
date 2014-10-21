package pl.edu.pjwstk.cms.dto;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import pl.edu.pjwstk.cms.models.Dictionary;

public class DictionaryTypeDto implements Serializable{
    
    private final static Logger LOGGER = Logger.getLogger(DictionaryTypeDto.class.getName()); 

    private Long id;
    private String value, description;
    private List<Dictionary> dictionaries;
    
    public DictionaryTypeDto() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Dictionary> getDictionaries() {
        return dictionaries;
    }

    public void setDictionaries(List<Dictionary> dictionaries) {
        this.dictionaries = dictionaries;
    }

}
