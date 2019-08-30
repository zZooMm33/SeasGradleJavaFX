package storage.Oceans;

import storage.ConnectionDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OceansDataBase implements OceansDAO{


    @Override
    public List<Oceans> getOceans() {

        try {
            List<Oceans> oceansList = new ArrayList<Oceans>();
            ResultSet resultSet = ConnectionDataBase.getConnection().createStatement().executeQuery("SELECT * FROM Oceans");
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String description = resultSet.getString(3);

                oceansList.add(new Oceans(id, name, description));
            }

            return oceansList;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean addOceans(Oceans oceans) {

        Connection connection = ConnectionDataBase.getConnection();

        try {
            // Сброс автофиксации
            connection.setAutoCommit(false);
            // Первая транзакция
            PreparedStatement addOcean = connection.prepareStatement(
                    "INSERT INTO Oceans (Name, Description) VALUES (?, ?);");
            addOcean.setString(1, oceans.getName());
            addOcean.setString(2, oceans.getDescription());
            addOcean.executeUpdate();

            // Завершение транзакции
            connection.commit();
            // Восстановление по умолчанию
            connection.setAutoCommit(true);

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            try
            {
                ConnectionDataBase.getConnection().rollback();
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public boolean changeOceans(Oceans oceans) {
        Connection connection = ConnectionDataBase.getConnection();

        try {
            // Сброс автофиксации
            connection.setAutoCommit(false);
            // Первая транзакция
            PreparedStatement changeOcean = connection.prepareStatement(
                    "UPDATE Oceans SET Name = ?, Description = ? WHERE Id = ?;");
            changeOcean.setString(1, oceans.getName());
            changeOcean.setString(2, oceans.getDescription());
            changeOcean.setInt(3, oceans.getId());
            changeOcean.executeUpdate();

            // Завершение транзакции
            connection.commit();
            // Восстановление по умолчанию
            connection.setAutoCommit(true);

            return true;

        } catch (SQLException e) {
            e.printStackTrace();

            try
            {
                ConnectionDataBase.getConnection().rollback();
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public boolean deleteOceans(int id) {

        try
        {
            return ConnectionDataBase.getConnection().createStatement().execute("DELETE FROM Oceans WHERE Id = " + id + ";");
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
