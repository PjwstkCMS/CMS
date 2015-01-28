package pl.edu.pjwstk.cms.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Klasa zarządzająca połączeniem z bazą danych; Aby wykonywać połączenia z 
 * bazą danych należy korzystać z zwawrtych w niej metod.
 * @author sergi_000
 */
public class ConnectionManager {

    public static String staticUrl = "jdbc:mysql://localhost/hrcms_glowna?useUnicode=true&characterEncoding=UTF-8&collation=utf8_polish_ci";
    public static String staticLogin = "root";
    public static String staticPass = "leofram";
    
  /*public static String staticUrl = "jdbc:mysql://sql.s20.vdl.pl/hrcms_glowna?useUnicode=true&characterEncoding=UTF-8&collation=utf8_polish_ci";
    public static String staticLogin = "hrcms_system";
    public static String staticPass = "hrsystem"; */
    
    public static String staticUrl2 = "jdbc:mysql://famalis.no-ip.info/hrsystem?useUnicode=true&characterEncoding=UTF-8&collation=utf8_polish_ci";
    public static String staticLogin2 = "Sergio";
    public static String staticPass2 = "quovadis1";
    
    public static int queryTimeout = 5;
    
    private final static Logger LOGGER = Logger.getLogger("utils.ConnectionManager");
    private String url;
    private String login;
    private String pass;
    private Connection connection;
    private static Map<String, ConnectionManager> activeConnections = new HashMap<>();

    
    public void setDBUrl(String url) {
        this.url = url;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    
    public void setPassword(String password) {
        pass = password;
    }
    
    protected ConnectionManager() {
        
    }
    
    public static ConnectionManager getConnectionManager(String login, String password, String url) {
        if(activeConnections.containsKey(login)){
            return activeConnections.get(login);
        } else {
            ConnectionManager cm = new ConnectionManager();
            cm.setLogin(login);
            cm.setDBUrl(url);
            cm.setPassword(password);
            activeConnections.put(login, cm);
            return activeConnections.get(login);
        }
        
    }
    
    public static ConnectionManager getConnectionManager() {
        if(activeConnections.containsKey(staticLogin)){
            return activeConnections.get(staticLogin);
        } else {
            ConnectionManager cm = new ConnectionManager();
            cm.setLogin(staticLogin);
            cm.setDBUrl(staticUrl);
            cm.setPassword(staticPass);
            activeConnections.put(staticLogin, cm);
            return activeConnections.get(staticLogin);
        }
        
    }
    
     public static ConnectionManager getConnectionManagerAuxilary() {
        if(activeConnections.containsKey(staticLogin2)){
            return activeConnections.get(staticLogin2);
        } else {
            ConnectionManager cm = new ConnectionManager();
            cm.setLogin(staticLogin2);
            cm.setDBUrl(staticUrl2);
            cm.setPassword(staticPass2);
            activeConnections.put(staticLogin2, cm);
            return activeConnections.get(staticLogin2);
        }
        
    }
    
    public void newConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Connection c = null;

        try {
            c = DriverManager.getConnection(
                    url, login, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        connection = c;
    }

    public boolean closeConnection() {

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                newConnection();
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return connection;


    }

    /**
     * Send a select type query to the database; Returns a set of records wich
     * match the requirements of the query.
     * @param query
     * @return 
     */
    public ResultSet select(String query) {
        ResultSet rs = null;
        //System.out.println("DEBUG: Execute query - "+query);
        LOGGER.info("Execute query - "+query);
        try {
            Statement s = getConnection().createStatement();
            s.setQueryTimeout(queryTimeout);
            rs = s.executeQuery(query);            
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }

        return rs;
    }
    /**
     * Send an update type query to the databse; Returns true or false depending
     * if the insertion or update was succesful.
     * @param query
     * @return 
     */
    public boolean update(String query) {
        //System.out.println("DEBUG: Execute query - "+query);
        LOGGER.info("Execute query - "+query);
        try {
            Statement s = getConnection().createStatement();
            s.executeUpdate(query);            
            return true;
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            return false;
        }
    }
}
