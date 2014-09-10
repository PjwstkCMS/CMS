package pl.edu.pjwstk.cms.dao.general;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    public static final int OR = 0, AND = 1;

    private final static Logger LOGGER = Logger.getLogger(GenericDao.class.getName());
    private static ConnectionManager connectionManager;
    private final Class modelClass;

    public GenericDao(Class c) {
        modelClass = c;
        if (connectionManager == null) {
            try {
                connectionManager = ConnectionManager.getConnectionManager();
                List<T> selectRecordsWithFieldValues = this.selectRecordsWithFieldValues("id", "1");
                if (selectRecordsWithFieldValues.size() == 0) {
                    throw new Exception();
                }
            } catch (Exception noConnection) {
                LOGGER.warning("Can't reach main sql server. Switching to auxilary.");
                connectionManager = ConnectionManager.getConnectionManagerAuxilary();
            }
        }

    }

    public List<T> selectAll() {
        return selectRecordsWithFieldValues(new ArrayList<String>(), new ArrayList<String>());
    }

    /**
     * Zwraca listę rekordów. Przyjmuje mapę, w której klucze to pola rekordów a
     * wartości kluczy, to wartość jaką dane pole ma przyjmować.
     *
     * @param map
     * @return
     */
    public List<T> selectRecordsFromObjectsMap(Map<String, Object> map) {
        List<String> fields = new ArrayList<>();
        List<Object> values = new ArrayList<>();

        for (String field : map.keySet()) {
            fields.add(field);
            values.add(map.get(field));
        }
        return selectRecordsWithFieldValuesForObjectList(fields, values);
    }

    /**
     * Zwraca listę rekordów. Przyjmuje mapę, w której klucze to pola rekordów a
     * wartości kluczy, to wartość jaką dane pole ma przyjmować.
     *
     * @param map
     * @return
     */
    public List<T> selectRecordsStirngsFromMap(Map<String, String> map) {
        List<String> fields = new ArrayList<>();
        List<String> values = new ArrayList<>();

        for (String field : map.keySet()) {
            fields.add(field);
            values.add(map.get(field));
        }
        return selectRecordsWithFieldValues(fields, values);
    }

    /**
     * Podstawowa metoda do pobierania danych z bazy danych. Przyjmuje listę
     * nazw pól i wartości tych pól oraz zwraca listę rekordów spełniających
     * wymagania.
     *
     * @param fieldNames
     * @param fieldValues
     * @return
     */
    public List<T> selectRecordsWithFieldValuesForObjectList(List<String> fieldNames, List<Object> fieldValues) {
        List<String> l = new ArrayList<>();
        for (Object o : fieldValues) {
            l.add((String) o);
        }
        return selectRecordsWithFieldValues(fieldNames, l);
    }

    /**
     * Metoda pobiera rekordy, gdzie dane pole przyjmuje daną wartość
     *
     * @param fieldName
     * @param fieldValues
     * @return
     */
    public List<T> selectRecordsWithFieldValues(String fieldName, String fieldValue) {
        List<String> l1 = new ArrayList<>();
        l1.add(fieldName);
        List<String> l2 = new ArrayList<>();
        l2.add(fieldValue);
        return selectRecordsWithFieldValues(l1, l2);
    }

    /**
     * Podstawowa metoda do pobierania danych z bazy danych. Przyjmuje listę
     * nazw pól i wartości tych pól oraz zwraca listę rekordów spełniających
     * wymagania.
     *
     * @param fieldNames
     * @param fieldValues
     * @return
     */
    public List<T> selectRecordsWithFieldValues(List<String> fieldNames, List<String> fieldValues) {
        String className = modelClass.getSimpleName();
        String query = "SELECT * ";
        query += "FROM " + className + " ";
        if (fieldValues.size() == 0) {

        } else {
            query += "WHERE ";
            for (int i = 0; i < fieldValues.size(); i++) {
                query += fieldNames.get(i) + "='" + fieldValues.get(i) + "'";
                if (i < fieldValues.size() && i > 1) {
                    query += " AND ";
                } else {
                    query += " ";
                }
            }
        }
        System.out.println("DEBUG:" + query);
        ArrayList<T> resultList = new ArrayList<>();
        try {
            ResultSet resultSet = connectionManager.select(query);
            while (resultSet.next()) {
                T obj = (T) modelClass.newInstance();
                Field[] fields = obj.getClass().getDeclaredFields();
                for (Field f : fields) {
                    if (!"LOGGER".equals(f.getName())) {
                        String fieldValue = "";
                        fieldValue = resultSet.getString(f.getName());
                        f.setAccessible(true);
                        f.set(obj, fieldValue);
                    }

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

    /**
     * Zwraca listę rekordów z bazdy danych dla danego zapytania MySQL.
     *
     * @param query
     * @return
     */
    public List<T> selectForQuery(String query) {
        System.out.println(query);
        ArrayList<T> resultList = new ArrayList<>();
        try {
            ResultSet resultSet = connectionManager.select(query);
            while (resultSet.next()) {
                T obj = (T) modelClass.newInstance();
                Field[] fields = obj.getClass().getDeclaredFields();
                for (Field f : fields) {
                    if (!"LOGGER".equals(f.getName())) {
                        String fieldValue = "";
                        fieldValue = resultSet.getString(f.getName());
                        f.setAccessible(true);
                        f.set(obj, fieldValue);
                    }

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

    /**
     * Zwraca listę rekordów z bazy danych. Przyjmuje mapę, gdzie klucztem jest
     * nazwa kolumny w tabelce, wartością jest lista wartości jakich dana
     * kolumna może przyjąć.
     *
     * @param map
     * @return
     */
    public List<T> selectForFieldsWithMultiplePossibileValues(Map<String, List<Object>> map) {
        String query = "SELECT *";
        query += " FROM " + modelClass.getSimpleName() + " WHERE ";
        int iKey = 0;
        for (String field : map.keySet()) {
            iKey++;
            List<Object> values = map.get(field);
            query += field + " IN ('";
            for (int i = 0; i < values.size(); i++) {
                query += values.get(i);
                if (i < values.size() - 1) {
                    query += "', '";
                } else {
                    query += "')";
                }
            }
            if (iKey < map.size()) {
                query += ", ";
            } else {
                query += " ";
            }
        }
        return selectForQuery(query);
    }
}
