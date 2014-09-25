/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.pjwstk.cms.dao;

import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.models.PersonData;

/**
 *
 * @author sergio
 */
public class PersonDataDao extends GenericDao<PersonData>{
    
    public PersonDataDao() {
        super(PersonData.class);
    }
}
