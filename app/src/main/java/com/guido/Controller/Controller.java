package com.guido.Controller;

import com.guido.Exceptions.EmailNotAvalable;
import com.guido.Exceptions.InvalidCredentials;
import com.guido.JDBC.JDBCQueries;
import com.guido.Model.Location;
import com.guido.Model.Model;
import com.guido.Model.Trip;
import com.guido.Model.User;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class Controller implements IController, Serializable {
    public JDBCQueries jdbc;
    public Model model;

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

    @Override
    public void registerUser(String email, String pass, String name) throws EmailNotAvalable {
        jdbc.register_user(name, pass, email);
    }

    public void setModel(User user) {
        this.model = new Model(user, jdbc.get_locations(), jdbc.get_trips());
    }


    public void close() throws SQLException {
        jdbc.close();
    }
}
