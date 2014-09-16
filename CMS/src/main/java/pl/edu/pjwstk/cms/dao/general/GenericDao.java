package pl.edu.pjwstk.cms.dao.general;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
    protected static ConnectionManager connectionManager;
    protected final Class modelClass;

    public GenericDao(Class c) {
        modelClass = c;
        if (connectionManager == null) {
            try {
                connectionManager = ConnectionManager.getConnectionManager();
                List<T> selectRecordsWithFieldValues = this.selectAll();
                if (selectRecordsWithFieldValues.isEmpty()) {
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
    
    protected String addParamConditions(String query, Map<String, List<String>> params) {
        if (!params.isEmpty()) {
            query += " ";
            for (String key : params.keySet()) {
                query += key + " IN (";
                for (int i = 0; i < params.get(key).size(); i++) {
                    query += params.get(key).get(i);
                    if (i < params.get(key).size() && params.get(key).size()>1) {
                        query += ",";
                    }
                    query += ")";
                }
            }
        }
        
        return query;
    }

    /**
     * Metoda pobiera rekordy, gdzie dane pole przyjmuje daną wartość
     *
     * @param fieldName
     * @param fieldValue
     * @return
     */
    public List<T> selectRecordsWithFieldValues(String fieldName, Object fieldValue) {
        List<String> l1 = new ArrayList<>();
        l1.add(fieldName);
        List<String> l2 = new ArrayList<>();
        l2.add(fieldValue+"");
        return selectRecordsWithFieldValues(l1, l2);
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
    
    /**
     * Metoda służąca do zmiany danych we wszystkich wierszach spełniających
     * podane wymagania.
     *
     * @param conditions - warunki w WHERE (np id = 1)
     * @param sets - wszystkie wartości które mają zostać zmienione; przykładowo
     * można napisać name='adam', surname='kowalski'
     * @return
     */
    public boolean update(String conditions, String... sets) {
        String query = "UPDATE " + modelClass.getSimpleName() + " SET ";
        for (int i = 0; i < sets.length; i++) {
            query += sets[i];
            if (i < sets.length - 1) {
                query += ", ";
            }
        }
        query += " WHERE " + conditions;
        return connectionManager.update(query);
    }
    
    public Long insert(Object obj) {
        try {
            String query = "INSERT INTO " + obj.getClass().getSimpleName() + " (";
            Field[] fields = obj.getClass().getDeclaredFields();
            List<Field> list = new ArrayList<Field>(Arrays.asList(fields));
            for (Field f : list){
                String s = f.getName();
                if(s.equals("LOGGER")){
                    list.remove(f);
                    break;
                }
            }
            fields = list.toArray(new Field[0]);
            for (int i = 0; i < fields.length; i++) {
                query += fields[i].getName();
                if (i < fields.length - 1) {
                    query += ", ";
                }
            }
            query += ") VALUES (";
            for (int i = 0; i < fields.length; i++) {
                String fieldValue = "";
                fields[i].setAccessible(true);
                fieldValue = (String) fields[i].get(obj);
                query += "'" + fieldValue + "'";
                if (i < fields.length - 1) {
                    query += ", ";
                }
            }
            query += ") ";
            if (connectionManager.update(query)) {
                ResultSet set = connectionManager.select("SELECT id FROM "+obj.getClass().getSimpleName() );
                try {
                    set.last();
                    return set.getLong("id");
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseObject.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                return -1L;
            }
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
            return -1L;
        }
        return -1L;
    }
    
    /**
     * Metoda ustawia podaną wartość w podanej kolumnie dla wszystkich wpisów
     * posiadających dane id
     *
     * @param id id elementu który będzie zmieniony
     * @param fieldName nazwa kolumny do zmiany
     * @param fieldValue nowa wartość kolumny
     * @return
     */
    public boolean updateFieldForAllElementsWithId(String id, String fieldName, String fieldValue) {
        return update("id=" + id, fieldName + "=" + fieldValue);
    }
    
    /**
     * Metoda ustawia podaną wartość w podanej kolumnie dla wszystkich wpisów
     * spełniających warunki
     *
     * @param conditionFieldName nazwa kolumny na podstawie której wybierane są
     * elementy do zmiany
     * @param conditionFieldValue wartość jaką mają mieć modyfikowane wpisy w
     * danej kolumie
     * @param fieldName nazwa modyfikowanej kolumny
     * @param fieldValue nowa watość
     * @return
     */
    public boolean updateFieldForAllElementsWithId(String conditionFieldName, String conditionFieldValue, String fieldName, String fieldValue) {
        return update(conditionFieldName + "=" + conditionFieldValue, fieldName + "=" + fieldValue);
    }
    
        /**
     * Metoda służąca do usuwania wpisów w bazie danych
     *
     * @param conditions warunki w WHERE
     * @return
     */
    public boolean delete(String conditions) {
        String query = "DELETE FROM " + modelClass.getSimpleName()
                + " WHERE " + conditions;
        return connectionManager.update(query);
    }
    
    
}
