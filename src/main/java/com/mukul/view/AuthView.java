package com.mukul.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AuthView {

    private JTextField usernameTextField;
    private JPasswordField passwordPasswordField;
    private JButton loginButton;
    private JPanel rootPanel;

    public String getUsername() {
        return usernameTextField.getText();
    }

    public String getPassword() {
        return passwordPasswordField.getText();
    }

    public void addLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(rootPanel, message);
    }

    public Container getRootPanel() {
        return rootPanel;
    }
}
