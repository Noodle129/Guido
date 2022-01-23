package com.guido.Controller;

import com.guido.Exceptions.InvalidCredentials;
import com.guido.JDBC.JDBCQueries;
import com.guido.Model.User;
import java.sql.SQLException;

public class LoginController implements ILoginController {
    JDBCQueries jdbc;
    public LoginController() {
        try {
            jdbc = new JDBCQueries();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User verifyLogin(String email, String pass) throws InvalidCredentials, SQLException {
        try {
            return jdbc.login_user(email, pass);
        } finally {
            jdbc.close();
        }
    }
}
