package sio.veliko.Repositories;

import sio.veliko.Models.User;
import sio.veliko.Tools.DataSourceProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static javafx.scene.input.KeyCode.O;

public class UserRepository implements RepositoryInterface<User,Integer>{


    private Connection connection;

    public UserRepository()
    {
        this.connection = DataSourceProvider.getCnx();
    }


    @Override
    public void create(User obj) throws SQLException {

    }

    @Override
    public void update(User obj) throws SQLException {

    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public User get(Integer integer) {
        return null;
    }

    @Override
    public ArrayList<User> getAll() throws SQLException {
        ArrayList<User> user = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id , nom , prenom , email , is_blocked FROM user WHERE roles = '[]'; ");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next())
        {
            User users = new User(resultSet.getInt("id"), resultSet.getString("nom"), resultSet.getString("prenom"), resultSet.getString("email"), resultSet.getString("is_blocked") );
            user.add(users);
        }
        return user;
    }

    public void bloquerUser(int idUser) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET is_blocked = true WHERE id = ?");
       preparedStatement.setInt(1,idUser);
       preparedStatement.executeUpdate();
       preparedStatement.close();
    }
    public void debloquerUser(int idUser) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET is_blocked = false WHERE id = ?");
        preparedStatement.setInt(1,idUser);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void  supprimerUser(int idUser) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user\n" +
                "SET email = CONCAT('anonymous', id, '@veliko.lan'), \n" +
                "    nom = 'anonymous', \n" +
                "    prenom = 'anonymous',\n" +
                "    token = NULL\n" +
                "WHERE id = ?;\n");
        preparedStatement.setInt(1, idUser);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

}
