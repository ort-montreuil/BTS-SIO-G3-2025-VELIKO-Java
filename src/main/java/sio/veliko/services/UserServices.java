package sio.veliko.services;

import sio.veliko.repository.UserRepository;

import java.sql.SQLException;

public class UserServices {
    private UserRepository velikoRepository;

    public UserServices() {
        this.velikoRepository = new UserRepository();
    }

    public Boolean verifierIdentifiants(String email, String password) throws SQLException {
        return velikoRepository.verifierIdentifiants(email, password);
    }
}