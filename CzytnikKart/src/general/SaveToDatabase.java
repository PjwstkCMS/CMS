package general;

import dao.CardDao;
import dao.LogDao;
import dao.TerminalDao;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import model.Card;
import model.Log;
import model.Terminal;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pawelek
 */

/*
Klasa zawierająca metody służące do zapisywania danych zeskanowania karty do bazy
danych. Nazwy metod mówią same za siebie.
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
        if (!cards.isEmpty()) {
            userCard = cards.get(0);
        } else {
            return false;
        }
        String macString;
        StringBuilder sb = new StringBuilder();
        try {
            Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
            while (networks.hasMoreElements()) {
                NetworkInterface network = networks.nextElement();
                byte[] mac = network.getHardwareAddress();

                if (mac != null) {
                    System.out.print("Current MAC address : ");                    
                    for (int i = 0; i < mac.length; i++) {
                        sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                    }
                    System.out.println(sb.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        macString = sb.toString();
        TerminalDao terDao = new TerminalDao();
        List<Terminal> terminals = terDao.selectRecordsWithFieldValues("mac", macString);
        Terminal terminal;
        if(terminals.isEmpty()) {
            return false;
        }
        terminal = terminals.get(0);
        Log log = new Log();
        log.setEmployeeId(userCard.getEmployeeId());
        log.setTerminalId(terminal.getId()+"");        
        log.setTimestamp(getTimestamp());
        LogDao logDao = new LogDao();
        logDao.insert(log);

        return true;
    }
}
