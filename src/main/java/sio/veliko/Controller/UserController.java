package sio.veliko.Controller;

import sio.veliko.Models.User;
import sio.veliko.Services.UserService;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserController {

    UserService userService;

    public UserController(){
        this.userService = new UserService();
    }

    public Boolean verifierIdentifiants(String email, String enteredPassword) throws SQLException {
        return userService.verifierIdentifiants(email,enteredPassword);
    }
    public ArrayList<User> getAll() throws SQLException
    {
         return userService.getAll();
    }

    public void bloquerUser(int idUser) throws SQLException {
        userService.bloquerUser(idUser);
    }
    public void debloquerUser(int idUser) throws SQLException {
        userService.debloquerUser(idUser);
    }
    public void supprimerUser(int idUser) throws SQLException {
        userService.supprimerUser(idUser);
    }

    public void forcerMdpChange(int idUser) throws SQLException {
        userService.forcerMdpChange(idUser);
    }
}
