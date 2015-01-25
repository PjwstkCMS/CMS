package pl.edu.pjwstk.cms.models;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.models.general.DatabaseObject;


public class DictionaryType extends DatabaseObject{
    
    private final static Logger LOGGER = Logger.getLogger(DictionaryType.class.getName());
    
    private String value;
    private String description;

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
     
    public DictionaryType(){
        super();
    }
}
