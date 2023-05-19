package com.mukul.controller;

import com.mukul.model.AuthModel;
import com.mukul.view.AuthView;
import com.mukul.view.DashboardView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthController {

    private final AuthModel model;
    private final AuthView view;

    private JFrame frame;

    public AuthController(AuthModel model, AuthView view) {
        this.model = model;
        this.view = view;

        view.addLoginListener(new LoginListener());
    }

    public void showView() {
        frame = new JFrame("");
        frame.setContentPane(view.getRootPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
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
                frame.setVisible(false);
                DashboardView view1 = new DashboardView();
                new DashboardController(view1).showView();
            } else {
                view.showMessage("Invalid username or password. Please try again.");
            }
        }
    }

}
