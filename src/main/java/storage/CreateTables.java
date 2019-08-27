package storage;

public class CreateTables {
    public static boolean Create(){

        if (ConnectionDataBase.getConnection() != null)
        {
            // создаем таблицы

        }
        else return false;

        return true;
    }
}
