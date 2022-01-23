package com.guido.Controller;

import com.guido.Exceptions.CannotAcessDataBase;
import com.guido.Exceptions.InvalidCredentials;
import com.guido.Model.User;

import java.sql.SQLException;

public interface IController {
    User verifyLogin(String email, String pass) throws InvalidCredentials;
    void close() throws SQLException;
}
