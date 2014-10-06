package general;

import dao.CardDao;
import dao.LogDao;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import model.Card;
import model.Log;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pawelek
 */
public class SaveToDatabase {
    
    private static String getTimestamp() {
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return sdf.format(today);
    }
    
    public static boolean sendToDataDB(String cardNum) {        
        CardDao carDao = new CardDao();
        List<Card> cards = carDao.selectRecordsWithFieldValues("number", cardNum);
        Card userCard;
        if(!cards.isEmpty()) {
            userCard = cards.get(0);
        } else {
            return false;
        }
        Log log = new Log();
        log.setEmployeeId(userCard.getEmployeeId());
        log.setTerminalId("-1");
        log.setTimestamp(cardNum);
        log.setTimestamp(getTimestamp());
        LogDao logDao = new LogDao();
        logDao.insert(log);
        
        return true;
    }
}
