package storage;

import utils.PropReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Класс для подключения к БД
 */
public class ConnectionDataBase {
    /**
     * Синглтон для подключения к БД
     */
    static private Connection connection = null;

    /**
     * Создает подключение к Бд
     *
     * @return Connection or null
     */
    public static Connection getConnection()
    {
        try
        {
            if (connection == null)
            {
                if (PropReader.getVal("storageType").equals("databaseH2")){
                    String host = PropReader.getVal("host"),
                            pass = PropReader.getVal("pass"),
                            user = PropReader.getVal("user");

                    Class.forName("org.h2.Driver");

                    if (pass.equals("null") || pass.equals(""))
                    {
                        pass = null;
                    }

                    connection = DriverManager.getConnection(host, user, pass);
                }
                else if (PropReader.getVal("storageType").equals("databaseSQLite")){
                    String host = PropReader.getVal("host");

                    Class.forName("org.sqlite.JDBC");

                    connection = DriverManager.getConnection(host);
                }
                else{
                    // txt file
                }
            }

            return connection;
        } catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
            return null;
        }

    }

    public static boolean disconnect(){

        if (connection == null) return true;

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        connection = null;
        return true;
    }
}
