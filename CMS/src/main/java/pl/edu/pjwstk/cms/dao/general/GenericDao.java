package pl.edu.pjwstk.cms.dao.general;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.edu.pjwstk.cms.models.general.DatabaseObject;
import pl.edu.pjwstk.cms.utils.ConnectionManager;

/**
 *
 * @author Sergio
 * @param <T>
 */
public class GenericDao<T extends DatabaseObject> {
    
    private final static Logger LOGGER = Logger.getLogger(GenericDao.class.getName()); 
    private final ConnectionManager connectionManager;
    private final Class modelClass;

    public GenericDao(Class c) {
        connectionManager = ConnectionManager.getConnectionManager();
        modelClass = c;        
    }
    
    public List<T> selectRecordsWithFieldValues(List<String> fieldNames, List<String> fieldValues) {
        String className = modelClass.getSimpleName();
        String query = "SELECT ";
        if (fieldNames.size()==0) {
            query+=" * ";
        } else {
            for (int i = 0; i<fieldNames.size(); i++) {
                query+=fieldNames.get(i);
                if(i<fieldNames.size()) {
                    query+=", ";
                } else {
                    query+=" ";
                }
            }
            
        }
        query+="FROM "+className+" ";
        if(fieldValues.size()==0){
            
        } else {
            query+="WHERE";
            for (int i = 0; i<fieldValues.size(); i++) {
                query+=fieldValues.get(i);
                if(i<fieldValues.size()) {
                    query+=", ";
                } else {
                    query+=" ";
                }
            }
        }
        ConnectionManager conMan = ConnectionManager.getConnectionManager();
        System.out.println(query);
        ArrayList<T> resultList = new ArrayList<>();
        try {
        ResultSet resultSet = conMan.select(query);
         while (resultSet.next()) {
                T obj = (T) modelClass.newInstance();
                Field[] fields = obj.getClass().getDeclaredFields();
                for (Field f : fields) {
                    String fieldValue = "";
                    fieldValue = resultSet.getString(f.getName());
                    f.setAccessible(true);
                    f.set(obj, fieldValue);

                }
                obj.setId(resultSet.getLong("id"));
                resultList.add(obj);
            }
        } catch (InstantiationException ex) {
            Logger.getLogger(GenericDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GenericDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GenericDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultList;
        
    }
}
