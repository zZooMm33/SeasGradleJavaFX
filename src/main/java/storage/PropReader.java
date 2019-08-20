package storage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Используется для считывания строк из config.properties
 */
public class PropReader
{

    private static final String PROP_FILE_NAME = "config.properties";

    /**
     * Вернет строку по ключу из config.properties
     *
     * @param key Ключ в config.properties
     * @return Вернет значение по ключу
     */
    public static String getVal(String key)
    {

        Properties prop = new Properties();

        InputStream inputStream = PropReader.class.getClassLoader().getResourceAsStream(PROP_FILE_NAME);

        if (inputStream != null)
        {
            try
            {
                prop.load(inputStream);
                return prop.getProperty(key);

            } catch (IOException e)
            {
                e.printStackTrace();
                return null;
            }
        }

        return null;
    }

    /**
     * Сохранит новое значение по ключу в config.properties
     *
     * @param key Ключ в config.properties
     * @param val Новое значение
     * @return Вернет значение по ключу
     */
    public static boolean setVal(String key, String val)
    {

        Properties prop = new Properties();

        InputStream inputStream = PropReader.class.getClassLoader().getResourceAsStream(PROP_FILE_NAME);

        if (inputStream != null)
        {
            try
            {
                prop.load(inputStream);
                prop.setProperty(key, val);

            } catch (IOException e)
            {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }
}
