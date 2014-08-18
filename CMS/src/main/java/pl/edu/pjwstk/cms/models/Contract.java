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
 * @author Konrad
 */
public class Contract extends DatabaseObject{
    
    private final static Logger LOGGER = Logger.getLogger(Contract.class.getName());
    
    private int id;
    private int employeeId;
    private int customerId;
    private String date;
    private String description;
    private long price;
    
}
