package storage;

import utils.PropReader;
import utils.StaticFields;

import java.io.*;

public class CreateDataBase {
    public static boolean Create(String name, String storageType, boolean createTable, boolean saveSettings) {

        new File(System.getProperty("user.home") + "/" + StaticFields.getNameProgram() + "/db").mkdir();
        File propFilePath = new File(System.getProperty("user.home") + "/" + StaticFields.getNameProgram() + "/db/" + name);

        if (storageType.equals("databaseSQLite")) {

            try {

                File dbFile = new File(propFilePath + "/" + name + ".db");
                InputStream inputStream = CreateDataBase.class.getClassLoader().getResourceAsStream("dataBase/emptyDataBase/SQLite/empty.db"); ///emptyDataBase/SQLite/

                propFilePath.mkdir();
                dbFile.createNewFile();

                OutputStream outputStream = new FileOutputStream(dbFile);

                int data;
                while ((data = inputStream.read()) != -1) {
                    outputStream.write(data);
                }

            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }


        } else {

            try {
                File dbFile_1 = new File(propFilePath + "/" + name + ".h2.db");
                File dbFile_2 = new File(propFilePath + "/" + name + ".trace.db");
                InputStream inputStream_1 = PropReader.class.getClassLoader().getResourceAsStream("dataBase/emptyDataBase/H2/empty.h2.db");
                InputStream inputStream_2 = PropReader.class.getClassLoader().getResourceAsStream("dataBase/emptyDataBase/H2/empty.trace.db");

                propFilePath.mkdir();
                dbFile_1.createNewFile();
                dbFile_2.createNewFile();

                OutputStream outputStream = new FileOutputStream(dbFile_1);
                int data;
                while ((data = inputStream_1.read()) != -1) {
                    outputStream.write(data);
                }

                outputStream = new FileOutputStream(dbFile_2);
                while ((data = inputStream_2.read()) != -1) {
                    outputStream.write(data);
                }

            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

        }


        if(saveSettings){
            PropReader.setVal("storageType", storageType);
            PropReader.setVal("user", StaticFields.getUserNameDb());
            PropReader.setVal("pass", StaticFields.getUserPassDb());

            if (storageType.equals("databaseSQLite"))
                PropReader.setVal("host", "jdbc:sqlite:" + System.getProperty("user.home") + "/" + StaticFields.getNameProgram() + "/db/" + name+ "/" + name + ".db");
            else
                PropReader.setVal("host", "jdbc:h2:" + System.getProperty("user.home") + "/" + StaticFields.getNameProgram() + "/db/" + name+ "/" + name);
        }

        if (createTable){
            CreateTables.Create();
        }

        return true;
    }
}
