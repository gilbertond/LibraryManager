/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.dao;
import java.util.List;

public interface PersistenceDAO {
    public static String DB_DIR = "";
    /**
     * Update file record
     * @param clazz
     * @param fieldName the unique constant field . e.g ID
     * @param fieldValue the value of the unique constant field
     * @return true for success otherwise false
     */
    public boolean updateRecord(Object clazz, String fieldName, String fieldValue);
    /** 
     * Save an object to the file DB
     * @param clazz the object to save
     * @return true for success otherwise false
     */
    public boolean saveRecordOrUpdate(Object clazz);
    
    /**
     * Delete a record
     * @param clazz
     * @param fieldName the unique constant field like ID
     * @param fieldValue the value of the unique constant field
     * @return true for success otherwise false
     */
    public boolean deleteRecord(Object clazz, String fieldName, String fieldValue);
    /**
     * fetch all records as per domain class
     * @param clazz the class to search
     * @return list of records
     */
    public List<?> getRecords(Class clazz);
    /**
     * Search an object
     * @param clazz the class to which the object belongs
     * @param fieldName the field Name to search by
     * @param fieldValue the field value
     * @return the found object. null if not found
     */
    public Object searchObject(Class clazz, String fieldName, String fieldValue);
    /**
     * Search an object by fieldNames
     * @param clazz the class to which the object belongs
     * @param fieldName the field Name to search by
     * @param fieldValue the field value
     * @return the found object. null if not found
     */
    public Object searchObject(Class clazz, String[] fieldName, Object[] fieldValue);
    
    public String getDB_Dir();
}
