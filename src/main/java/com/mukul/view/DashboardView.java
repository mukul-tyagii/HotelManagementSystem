package com.mukul.view;

import javax.swing.*;

public class DashboardView {
    private JPanel root;
    private ClientManagerView clientManagerView;
    private RoomManagerView roomManagerView;


    public JPanel getRoot() {
        return root;
    }

    public ClientManagerView getClientManagerView() {
        return clientManagerView;
    }

    public RoomManagerView getRoomManagerView() {
        return roomManagerView;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(root, message);
    }
}
