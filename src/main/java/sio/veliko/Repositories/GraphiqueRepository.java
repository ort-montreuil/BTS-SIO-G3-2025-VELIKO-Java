package sio.veliko.Repositories;

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

    /*
    public ArrayList<> getLesMeilleursUsers() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT user.id, user.nom, user.prenom, COUNT(reservation.id_user_id) AS nbResa FROM user INNER JOIN reservation ON user.id = reservation.id_user_id GROUP BY user.id, user.nom, user.prenom HAVING nbResa = ( SELECT MAX(nbResa) FROM ( SELECT COUNT(*) AS nbResa FROM reservation GROUP BY reservation.id_user_id ) AS topResa ); ");
        ResultSet resultSet = preparedStatement.executeQuery();

        preparedStatement.close();
        resultSet.close();

    }

     */

}
