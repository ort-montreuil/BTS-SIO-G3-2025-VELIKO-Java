package sio.veliko.Repositories;

import sio.veliko.Tools.DataSourceProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

}
