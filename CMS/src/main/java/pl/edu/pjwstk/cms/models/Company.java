package pl.edu.pjwstk.cms.models;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.models.general.DatabaseObject;

/**
 *
 * @author Konrad
 */
public class Company extends DatabaseObject {

    private final static Logger LOGGER = Logger.getLogger(Company.class.getName());

    private String name;
    private String contactpersonId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactpersonId() {
        return contactpersonId;
    }

    public void setContactpersonId(String contactpersonId) {
        this.contactpersonId = contactpersonId;
    }

    public Company() {
        super();
    }

}
