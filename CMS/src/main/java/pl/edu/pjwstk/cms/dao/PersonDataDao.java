/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.pjwstk.cms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.dto.PositionDto;
import pl.edu.pjwstk.cms.models.PersonData;
import pl.edu.pjwstk.cms.models.User;

/**
 *
 * @author sergio
 */
public class PersonDataDao extends GenericDao<PersonData> {

    public PersonDataDao() {
        super(PersonData.class);
    }

    public PersonData getPersonDataForUser(Long id) {
        String query = "SELECT per.id, per.forename, per.surname, per.email, per.pesel, per.phone "
                + "FROM persondata as per, employee as emp, user as u "
                + "WHERE u.id="+id+" AND emp.id=u.employeeId AND per.id=emp.persondataId";
        ResultSet set = this.connectionManager.select(query);
        PersonData userData = new PersonData();
        try {
            while(set.next()) {
                userData.setEmail(set.getString("email"));
                userData.setId(set.getLong("id"));
                userData.setForename(set.getString("forename"));
                userData.setPesel(set.getString("pesel"));
                userData.setSurname(set.getString("surname"));
                userData.setPhone(set.getString("phone"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        
        return userData;
    }
}
