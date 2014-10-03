
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pawelek
 */
public class Card {
    
    long num;
    boolean working = false;
    List<Date[]> dates = new ArrayList<>();
    
    static Map<String, Card> persistance = new HashMap<>();
    
    public Card() {
        
    }
}
