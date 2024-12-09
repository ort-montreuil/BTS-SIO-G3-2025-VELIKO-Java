package sio.veliko.repository;


import sio.veliko.Tools.DataSourceProvider;
import sio.veliko.Tools.MdpHasher;
import sio.veliko.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    private Connection connection;
    private PreparedStatement ps;
    private ResultSet rs;
    private User user;

    public UserRepository()
    {
        this.connection = DataSourceProvider.getCnx();
    }

    public Boolean verifierIdentifiants(String email, String enteredPassword) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT email, password FROM user WHERE roles = '[\"ROLE_ADMIN\"]' AND email = ?");;
        ps.setString(1, email);
        boolean result = false;
        ResultSet rs = ps.executeQuery();
        MdpHasher  passwordHasher = new MdpHasher();
        while (rs.next()) {
            if (passwordHasher.verifyPassword(rs.getString("password"), enteredPassword) && rs.getString("email").equals(email)) {
                result = true;
            }

        }
        ps.close();
        rs.close();
        return result;
    }

    public void forcerChangerMdp (String email) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("update user set last_password_change = 1 where user.email = ?");
        ps.setString(1, email);
        ps.executeUpdate();
        ps.close();
    }
}
