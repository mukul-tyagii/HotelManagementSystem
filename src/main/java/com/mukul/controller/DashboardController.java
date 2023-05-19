package com.mukul.controller;

import com.mukul.model.ClientDAO;
import com.mukul.model.RoomDAO;
import com.mukul.view.ClientManagerView;
import com.mukul.view.DashboardView;
import com.mukul.view.RoomManagerView;

import javax.swing.*;
import java.sql.SQLException;

public class DashboardController {

    private final DashboardView view;

    DashboardController(DashboardView view) {
        this.view = view;
        try {
            ClientDAO clientDAO = new ClientDAO();
            ClientManagerView cmView = view.getClientManagerView();
            new ClientController(clientDAO, cmView);
            RoomDAO roomDAO = new RoomDAO();
            RoomManagerView view1 = view.getRoomManagerView();
            new RoomController(roomDAO, view1);
        } catch (SQLException e) {
            view.showMessage(e.getMessage());
        }
    }

    public void showView() {
        JFrame frame = new JFrame("");
        frame.setContentPane(view.getRoot());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
