package pl.edu.pjwstk.cms.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.edu.pjwstk.cms.dao.SystemConfigurationDao;
import pl.edu.pjwstk.cms.dto.UserDto;
import pl.edu.pjwstk.cms.models.SystemConfiguration;

/**
 * Tutaj przetrzymywane będą różne przydatne metody wykorzystywane w więcej niż
 * jedenj klasie.
 *
 */
public abstract class Utils {

    /**
     * Metoda zamieniająca JSON w postaci Stringa na object Javovy; zwracany
     * obiekt wciąż trzeba zrzutować do konkretnego obiektu.
     *
     * @param json
     * @param javaClass
     * @return
     */
    public static Object convertJSONStringToObject(String json, Class javaClass) {
        ObjectMapper mapper = new ObjectMapper();
        Object o = null;
        try {
            o = mapper.readValue(json, javaClass);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return o;
    }

    /**
     * Metoda zamieniająca JSON w postaci Stringa (z nazwą obiektu na początku)
     * na object Javovy; zwracany obiekt wciąż trzeba zrzutować do konkretnego
     * obiektu.
     *
     * @param json - JSON w formie Stringa
     * @param objectName - Nazwa zmiennej String w której zawarty jest JSON
     * (innymi słowy nazwa zmiennej ktora jest podana jako pierwszy paramter)
     * @param javaClass - Klasa na podstawie której ma zostać stworzony zwracany
     * obiekt
     * @return Obiekt java typu <Object>
     */
    public static Object convertJSONStringToObject(String json, String objectName, Class javaClass) {
        String actualJsonString = json.substring(
                ("'" + objectName + "':'").length(), json.length() - 1);
        ObjectMapper mapper = new ObjectMapper();
        Object o = null;
        try {
            o = mapper.readValue(actualJsonString, javaClass);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return o;
    }

    public static String convertObjectToJSON(Object obj) {
        System.out.println("DEBUG: requestJsons");
        ObjectMapper mapper = new ObjectMapper();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] data;
        try {
            mapper.writeValue(out, obj);
            data = out.toByteArray();
            return new String(data, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String convertObjectListToJSON(List<?> list) {
        System.out.println("DEBUG: requestJsons");
        ObjectMapper mapper = new ObjectMapper();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] data;
        try {
            mapper.writeValue(out, list);
            data = out.toByteArray();
            return new String(data, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String convertOMapToJSON(Map<?, ?> map) {
        System.out.println("DEBUG: requestJsons");
        ObjectMapper mapper = new ObjectMapper();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] data;
        try {
            mapper.writeValue(out, map);
            data = out.toByteArray();
            return new String(data, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Metoda przygotowuje odpowiedź serwera pod kątem pobrania pliku
     *
     * @param hash
     * @param fileName
     * @param mimeType
     * @param response
     */
    public static void download(String hash, String fileName, String mimeType, HttpServletResponse response) {
        File file;
        try {
            byte[] barr = HexConverter.toBytesFromHex(hash);
            file = new File(fileName);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(barr);
            fos.close();

            FileInputStream fis = new FileInputStream(file);
            response.setContentType(mimeType);

            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"",
                    fileName);
            response.setHeader(headerKey, headerValue);

            IOUtils.copy(fis, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException io) {
            System.err.println("Download IO error");
            io.printStackTrace();
        }
    }

    /**
     * Metoda służąca do odpowiedniego przygotowania JSONa z danymi aby
     * prawidłowo przekazać je do klienta (np. do angulara).
     *
     * @param session <HttpSession>
     * @param initData Dane, które mają zostać zamienione w JSON
     * @return dane odpowiedzi serwera
     */
    public static ResponseEntity<String> createResponseEntity(HttpSession session, Map<String, Object> initData) {
        HttpHeaders responseHeaders = new HttpHeaders();
        SystemConfigurationDao sysConfigDao = new SystemConfigurationDao();
        SystemConfiguration charset = sysConfigDao.selectRecordsWithFieldValues("name", "DefaultPageEncoding").get(0);
        responseHeaders.add("Content-Type", "text/html; charset=" + charset.getValue());
        UserDto user = (UserDto) (session.getAttribute("user"));
//        initData.put("privileges", user.getPrivilegeKeyCodes());

        Logger.getLogger(Utils.class.getName()).log(Level.INFO, "SEND: {0}", Utils.convertOMapToJSON(initData));

        return new ResponseEntity<>(convertOMapToJSON(initData), responseHeaders, HttpStatus.OK);
    }

    public static ResponseEntity<java.io.File> createResponseEntityForFile(HttpSession session) {
        java.io.File file = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        try {
            responseHeaders.add("Content-Type", "image/jpg");
            UserDto user = (UserDto) (session.getAttribute("user"));
            file = Utils.getFileFromHash(user.getPhotoHash());
            //Logger.getLogger(Utils.class.getName()).log(Level.INFO, "SEND: {0}", Utils.convertOMapToJSON(initData));
            int a = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<java.io.File>(file, responseHeaders, HttpStatus.OK);
    }

    public static File getFileFromHash(String hash) throws FileNotFoundException, IOException {
        byte barray[] = HexConverter.toBytesFromHex(hash);
        //String get_price = rs.getString(5);
        java.io.File someFile = new java.io.File("image.jpg");
        FileOutputStream fos = new FileOutputStream(someFile);
        fos.write(barray);
        fos.flush();
        fos.close();
        return someFile;
    }

    public static boolean sendMail(String recipent, String body, String subject) {

        String username = "pjwstkhrsystem@vp.pl";
        String password = "hrsystem123";
        String host = "smtp.poczta.onet.pl";
        
        try {
            Properties prop = System.getProperties();
            Authenticator auth = new myAuthenticator();
            prop.put("mail.smtp.host", host);
            prop.put("mail.smtp.user", username);
            prop.put("mail.smtp.password", password);
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.enable", "true");
            prop.put("mail.smtp.port", 587);

            Session session = Session.getInstance(prop, auth);

            session.setDebug(true);
            Message message = new MimeMessage(session);
            //wstawienie treści
            //message.setContent("tresc","text/plain");
            message.setText(body);
            //wstawienie tytułu
            message.setSubject(subject);
            Address address = new InternetAddress(username);
            Address addressOdbiorcy = new InternetAddress(recipent);

            //wstawienie adresu nadawcy do wiadomości
            message.setFrom(address);
            //wstawienie adresu odbiorcy
            message.addRecipient(Message.RecipientType.TO, addressOdbiorcy);
            message.saveChanges();
            Transport transport = session.getTransport("smtp");
            transport.connect(host, username, password);
            transport.sendMessage(message, message.getAllRecipients());

            transport.close();

        } catch (Exception ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
}

class myAuthenticator extends Authenticator {

    String username = "pjwstkhrsystem@vp.pl";
    String password = "hrsystem123";

    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }
}
