package com.guido.Controller;

public class LoginController implements ILoginController {
    public LoginController() {
    }

    @Override
    public boolean verifyLogin(String email, String pass) {
        return (email.equals("email") && pass.equals("pass"));
    }
}
