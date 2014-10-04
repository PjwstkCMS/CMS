/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 *
 * @author sergio
 */
public class Card extends DatabaseObject{
    
    private static final Logger LOGGER = Logger.getLogger(Card.class.getName());
    
    private String employeeId;
    boolean working = false;
    List<Date[]> dates = new ArrayList<>();
    
    static Map<String, Card> persistance = new HashMap<>();
    
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
