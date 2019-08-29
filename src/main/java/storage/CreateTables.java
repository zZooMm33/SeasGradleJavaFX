package storage;

import utils.PropReader;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTables {
    public static boolean Create(){

        if (ConnectionDataBase.getConnection() != null)
        {
            InputStream inputStream = null;
            if (PropReader.getVal("storageType").equals("databaseSQLite")){
                inputStream = PropReader.class.getClassLoader().getResourceAsStream("dataBase/sql/createTablesSQLiteDataBase.sql");
            }
            else {
                inputStream = PropReader.class.getClassLoader().getResourceAsStream("dataBase/sql/createTablesH2DataBase.sql");
            }

            try {
                String sql = "";
                int i;
                while((i=inputStream.read())!= -1){
                    sql += (char)i;
                }


                try {

                    ConnectionDataBase.getConnection().createStatement().executeUpdate(sql);
                    return true;
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }

            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        else return false;

    }
}
