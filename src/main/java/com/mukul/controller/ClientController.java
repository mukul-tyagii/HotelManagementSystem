package com.mukul.controller;

import com.mukul.model.Client;
import com.mukul.model.ClientDAO;
import com.mukul.view.ClientManagerView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class ClientController {
    private final ClientDAO dao;
    private final ClientManagerView view;

    ClientController(ClientDAO dao, ClientManagerView view) {
        this.dao = dao;
        this.view = view;

        refreshTable();

        view.addInsertListener(new InsertListener());
        view.addDeleteListener(new DeleteListener());
        view.addUpdateListener(new UpdateListener());
        view.addRefreshListener(new RefreshListener());
    }

    private void refreshTable() {
        try {
            List<Client> clients = dao.getAllClients();
            view.refreshTable(clients.toArray(new Client[0]));
        } catch (SQLException ex) {
            view.showMessage(ex.getMessage());
        }
    }

    public void showView() {
        JFrame frame = new JFrame("");
        frame.setContentPane(view.getRootPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    class InsertListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int id = view.getId();
                String name = view.getName();
                String phone = view.getPhone();
                dao.addClient(new Client(id, name, phone));
            } catch (SQLException ex) {
                view.showMessage(ex.getMessage());
            }
        }
    }

    class UpdateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int id = view.getId();
                String name = view.getName();
                String phone = view.getPhone();
                dao.updateClient(new Client(id, name, phone));
            } catch (SQLException ex) {
                view.showMessage(ex.getMessage());
            }
        }
    }

    class DeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int id = view.getId();
                String name = view.getName();
                String phone = view.getPhone();
                dao.deleteClient(new Client(id, name, phone));
            } catch (SQLException ex) {
                view.showMessage(ex.getMessage());
            }
        }
    }

    class RefreshListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            refreshTable();
        }
    }

}
