/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.logging.Logger;

/**
 *
 * @author sergio
 */
public class Card extends DatabaseObject{
    
    private static final Logger LOGGER = Logger.getLogger(Card.class.getName());
    
    private String employeeId;
    private String number;
    
    public Card() {
        super();
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    
    
}
