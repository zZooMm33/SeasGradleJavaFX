package utils;


import java.io.*;
import java.util.Properties;

/**
 * Используется для считывания строк из config.properties
 */
public class PropReader
{

    /**
     * Вернет строку по ключу из config.properties
     *
     * @param key Ключ в config.properties
     * @return Вернет значение по ключу
     */
    public static String getVal(String key){
        InputStream inputStream = null;
        Properties prop = new Properties();
        File propFile = new File(System.getProperty("user.home") + "/" + StaticFields.getNameProgram() + "/" + StaticFields.getPropFileName());

        if (propFile.exists()){
            try {
                inputStream = new FileInputStream(propFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }
        else{
            inputStream = PropReader.class.getClassLoader().getResourceAsStream(StaticFields.getPropFileName());
        }

        try
        {
            prop.load(inputStream);
            inputStream.close();
            return prop.getProperty(key);

        } catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Сохранит новое значение по ключу в config.properties
     *
     * @param key Ключ в config.properties
     * @param val Новое значение
     * @return Вернет значение по ключу
     */
    public static boolean setVal(String key, String val){

        Properties prop = new Properties();
        InputStream inputStream = null;
        File propFilePath = new File(System.getProperty("user.home") + "/" + StaticFields.getNameProgram());
        File propFile = new File(propFilePath + "/" + StaticFields.getPropFileName());

        if (propFile.exists()){
            try {
                inputStream = new FileInputStream(propFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return false;
            }
        }
        else{
            inputStream = PropReader.class.getClassLoader().getResourceAsStream(StaticFields.getPropFileName());
            try {
                propFilePath.mkdir();
                propFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        try
        {
            prop.load(inputStream);
            inputStream.close();
            prop.setProperty(key, val);
            prop.store(new FileOutputStream(propFile), null);
            return true;

        } catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
