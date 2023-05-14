package com.mukul.model;

public class AuthModel {

    private final String username;
    private final String password;

    public AuthModel() {
        this.username = "mukul";
        this.password = "mukul";
    }

    public boolean authenticate(String username, String password) {
        return username.equals(this.username) && password.equals(this.password);
    }

}
