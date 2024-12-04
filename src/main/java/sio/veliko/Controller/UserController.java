package sio.veliko.Controller;

import sio.veliko.services.UserServices;

import java.sql.SQLException;

public class UserController {
    private UserServices userServices;

    public UserController() {
        this.userServices = new UserServices();
    }

    public Boolean verifierIdentifiants(String email, String password) throws SQLException {
        return userServices.verifierIdentifiants(email, password);
    }
}
