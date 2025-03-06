package sio.veliko.Repositories;

import sio.veliko.Models.User;
import sio.veliko.Tools.DataSourceProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GraphiqueRepository {

    private Connection connection;

    public GraphiqueRepository() {
        this.connection = DataSourceProvider.getCnx();
    }

    public HashMap<String,Integer> getDatasGraphique1() throws SQLException {
        HashMap<String,Integer> datas = new HashMap<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT name, capacity FROM station ORDER BY capacity DESC LIMIT 5; "
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            datas.put(resultSet.getString("name"), resultSet.getInt("capacity"));
        }
        preparedStatement.close();
        resultSet.close();
        return datas;
    }


    public ArrayList<User> getLesMeilleursUsers() throws SQLException {
        ArrayList<User> lesMeilleurs = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("call lesMeilleursClients");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next())
        {
            lesMeilleurs.add(new User(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3)));
        }
        preparedStatement.close();
        resultSet.close();

        return lesMeilleurs;
    }

    public int nbTotalStations() throws SQLException {
        int total=0 ;
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT Count(*) as totalStations FROM `station`; ");
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            total = resultSet.getInt("totalStations");
        }
        preparedStatement.close();
        resultSet.close();
        return total;
    }
    public int nbTotalCapacite() throws SQLException {
        int total=0 ;
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT SUM(capacity) as totalCapacite FROM `station`; ");
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            total = resultSet.getInt("totalCapacite");
        }
        preparedStatement.close();
        resultSet.close();
        return total;
    }




}
