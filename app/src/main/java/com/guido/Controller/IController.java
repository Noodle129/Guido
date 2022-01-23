package com.guido.Controller;

import com.guido.Exceptions.CannotAcessDataBase;
import com.guido.Exceptions.InvalidCredentials;
import com.guido.Model.User;

import java.sql.SQLException;

public interface ILoginController {
    User verifyLogin(String email, String pass) throws InvalidCredentials, SQLException;
}
