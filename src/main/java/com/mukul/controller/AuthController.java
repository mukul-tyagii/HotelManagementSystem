package com.mukul.controller;

import com.mukul.model.AuthModel;
import com.mukul.model.ClientDAO;
import com.mukul.view.AuthView;
import com.mukul.view.ClientManagerView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

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

                try {
                    ClientDAO dao = new ClientDAO();
                    ClientManagerView view = new ClientManagerView();
                    new ClientController(dao, view).showView();
                } catch (SQLException ex) {
                    view.showMessage(ex.getMessage());
                }

            } else {
                view.showMessage("Invalid username or password. Please try again.");
            }
        }
    }

}
