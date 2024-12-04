package sio.veliko.Services;

import sio.veliko.Models.User;
import sio.veliko.Repositories.UserRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserService {
    private UserRepository userRepository;

    public UserService(){
        this.userRepository = new UserRepository();
    }

    public ArrayList<User> getAll() throws SQLException
    {
        return userRepository.getAll();
    }

    public void bloquerUser(int idUser) throws SQLException {
         userRepository.bloquerUser(idUser);
    }
    public void debloquerUser(int idUser) throws SQLException {
        userRepository.debloquerUser(idUser);
    }
}
