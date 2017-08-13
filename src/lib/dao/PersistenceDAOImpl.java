/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.dao;

import lib.util.OsCheck;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.ArrayList;
import java.io.EOFException;
import java.lang.reflect.InvocationTargetException;

public class PersistenceDAOImpl implements PersistenceDAO{

    private static String OUTPUT_DIR = "";
    private static final Logger logger = Logger.getLogger(PersistenceDAOImpl.class.getName());
    FileOutputStream fileOutputStream;
    FileInputStream fileInputStream = null;
    
    @Override
    public boolean saveRecordOrUpdate(Object clazz) {
        try {
            createDBFile(clazz.getClass().getSimpleName()); //try another extension like db
            
            // Store Serialized User Object in File
            fileOutputStream = new FileOutputStream(OUTPUT_DIR, true);            
            
            try (ObjectOutputStream output = new ObjectOutputStream(fileOutputStream)) {
                System.out.println("****\n");
                System.out.println("Saving..."+clazz.toString());
                System.out.println("****\n");
                
                output.writeObject(clazz);
                output.flush();
            }
            fileOutputStream.close();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(PersistenceDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    private boolean update(List<?> clazz){
        try {
            createDBFile(clazz.get(0).getClass().getSimpleName()); //try another extension like db                           
            System.out.println("+++++++++++++++"+OUTPUT_DIR);
            File file = new File(OUTPUT_DIR);
            if(file.exists()){
                System.out.println("+++++++++++++++");
//                fileOutputStream.close();
                file.delete();
                
                createDBFile(clazz.get(0).getClass().getSimpleName()); 
            }else{
                createDBFile(clazz.get(0).getClass().getSimpleName());
            }
            
             // Store Serialized User Object in File
            fileOutputStream = new FileOutputStream(OUTPUT_DIR, true);
            for (Object clazz1 : clazz) {
                System.out.println("Saving..." + clazz1);
                ObjectOutputStream output = new ObjectOutputStream(fileOutputStream);
                output.writeObject(clazz1);
            }

            fileOutputStream.close();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(PersistenceDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    @Override
    public boolean updateRecord(Object clazz, String fieldName, String fieldValue) {
        List objects = getRecords(clazz.getClass());
        Object search = searchObject(clazz.getClass(), fieldName, fieldValue);
        int size = objects.size();
        
        for (int i = 0; i < size; i++) {
            System.out.println(objects.get(i).equals(search)+"...equals..."+search);
            if(objects.get(i).equals(search)){
                System.out.println(objects.get(i)+"...equals..."+search);
                objects.remove(i);
                objects.add(i, clazz);
            }
        }
        System.out.println("...Check..."+objects.size());
        return update(objects);
    }

    @Override
    public boolean deleteRecord(Object clazz, String fieldName, String fieldValue) {
        List objects = getRecords(clazz.getClass());
        Object search = searchObject(clazz.getClass(), fieldName, fieldValue);
        int size = objects.size();
        
        for (int i = 0; i < size; i++) {
            
            if(objects.get(i).equals(search)){
                System.out.println(clazz+"...equals..."+search);
                objects.remove(i);
            }
        }
        System.out.println("...Check..."+objects.size());
        return update(objects);
    }

    @Override
    public List<?> getRecords(Class clazz) {
            
            List<Object> list = new ArrayList<>();
        try {
            OUTPUT_DIR = getDBFileDirectory() + clazz.getSimpleName() + ".dbo";
            if(!new File(OUTPUT_DIR).exists()){
                logger.log(Level.WARNING, "No DB for {0} exists", clazz.getSimpleName());  
                FileWriter fileWriter = new FileWriter(new File(OUTPUT_DIR));
//                return null;
            }
            //Read from the stored file
            fileInputStream = new FileInputStream(new File(OUTPUT_DIR));
            
            while(true) {
                try {
                    ObjectInputStream input = new ObjectInputStream(fileInputStream);
                    list.add(input.readObject());
//                    input.close();
                } catch (EOFException ex) {
                    break;
                }
            }
            return list;
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PersistenceDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(PersistenceDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(PersistenceDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    @Override
    public Object searchObject(Class clazz, String fieldName, String fieldValue) {
        try {
            
            List<Object> obj = (List<Object>)getRecords(clazz);
            Object retObject = null;
            for (Object obj1 : obj) {
                if((clazz.getMethod(fieldName, (Class[])null).invoke(obj1, (Object[])null)).equals(fieldValue)){
                    retObject = obj1;
                    break;
                }
            }
            
            return retObject;
            
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(PersistenceDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public Object searchObject(Class clazz, String[] fieldName, Object[] fieldValue) throws IllegalArgumentException{
        try {
            if(fieldName.length != fieldValue.length) throw new IllegalArgumentException("FieldName and fieldValue array lengths are different!!");
            List<Object> obj = (List<Object>)getRecords(clazz);
            Object retObject = null;
            for (Object obj1 : obj) {
                
                int gt = 0;
                for (int i = 0; i < fieldName.length; i++) {
                    if(gt == fieldName.length){
                        break;
                    }
                    if((clazz.getMethod(fieldName[i], (Class[]) null).invoke(obj1, (Object[]) null)).equals(fieldValue[i])) {  
                        gt += 1;
                    }
                }         
                System.out.println(gt + "==" + fieldName.length + " : "+obj1);
                if(gt == fieldName.length){
                    retObject = obj1;
                }
            }
            
            return retObject;
            
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(PersistenceDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private void createDBFile(String fileName) throws IOException {
        OUTPUT_DIR = getDBFileDirectory() + fileName + ".dbo";
        File file = new File(OUTPUT_DIR);
        if ((file.exists())) {
            System.out.println("Exists: " + file.exists());
        } else {
            try (FileWriter fileWriter = new FileWriter(file)) {
                System.out.println("Exists: " + file.exists());
            }
        }
    }
    
    @Override
    public String getDB_Dir(){
        return getDBFileDirectory();
    }
    
    private String getDBFileDirectory(){
        String filePath = "";
        
        System.out.println("Os type::" + OsCheck.getOperatingSystemType());

        //creating image temporary storage directory on windows machine
        switch (OsCheck.getOperatingSystemType().toString()) {
            case "Windows":
                (new File("C:\\libManager\\database\\")).mkdirs();
                filePath = "C:\\libManager\\database\\";
                logger.info("Detected that your Server is: Windows");
                break;
            case "Linux":
                (new File("/home/libManager/database/")).mkdirs();
                filePath = "/home/libManager/database/";
                logger.info("Detected that your Server is: Linux/Unix");
                break;
            case "MacOS":
                (new File("/Users/libManager/database/")).mkdirs();
                filePath = "/Users/libManager/database/";
                logger.info("Detected that your Server is: MacOS");
                break;
        }
        return filePath;
    }
}
