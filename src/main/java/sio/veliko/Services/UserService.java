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

    public Boolean verifierIdentifiants(String email, String password) throws SQLException {
        return userRepository.verifierIdentifiants(email, password);
    }
    public ArrayList<User> getAll() throws SQLException
    {
        return userRepository.getAll();
    }

    public void  bloquerUser(int idUser) throws SQLException {
         userRepository.bloquerUser(idUser);
    }

    public void debloquerUser(int idUser) throws SQLException {
        userRepository.debloquerUser(idUser);
    }
    public void supprimerUser(int idUser) throws SQLException {
        userRepository.supprimerUser(idUser);
    }
    public void forcerMdpChange(int idUser) throws SQLException {
        userRepository.forcerMdpChange(idUser);
    }
}
