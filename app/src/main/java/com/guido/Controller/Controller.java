package com.guido.Controller;

import com.guido.Exceptions.InvalidCredentials;
import com.guido.JDBC.JDBCQueries;
import com.guido.Model.User;

import java.io.Serializable;
import java.sql.SQLException;

public class Controller implements IController, Serializable {
    public JDBCQueries jdbc;

    public Controller() {
        try {
            jdbc = new JDBCQueries();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User verifyLogin(String email, String pass) throws InvalidCredentials {
        return jdbc.login_user(email, pass);
    }

    public void close() throws SQLException {
        jdbc.close();
    }
}
