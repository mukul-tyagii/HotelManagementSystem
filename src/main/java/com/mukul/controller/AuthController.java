package com.mukul.controller;

import com.mukul.model.AuthModel;
import com.mukul.view.AuthView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthController {

    private final AuthModel model;
    private final AuthView view;

    public AuthController(AuthModel model, AuthView view) {
        this.model = model;
        this.view = view;

        view.addLoginListener(new LoginListener());
    }

    public void showView() {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(view.getRootPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = view.getUsername();
            String password = view.getPassword();

            boolean isAuthenticated = model.authenticate(username, password);

            if (isAuthenticated) {
                view.showMessage("Login successful!");
                // Perform further actions after successful login
            } else {
                view.showMessage("Invalid username or password. Please try again.");
            }
        }
    }

}
