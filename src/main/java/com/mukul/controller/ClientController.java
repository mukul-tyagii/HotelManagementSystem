package com.mukul.controller;

import com.mukul.model.Client;
import com.mukul.model.ClientDAO;
import com.mukul.view.ClientManagerView;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
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
        view.addClientTableModelListener(new TableUpdateListener());
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

    class TableUpdateListener implements TableModelListener {

        @Override
        public void tableChanged(TableModelEvent e) {
            if (e.getType() == TableModelEvent.UPDATE) {
                int row = e.getFirstRow();
                DefaultTableModel model = view.getClientTableModel();
                int id = (int) model.getValueAt(row, 0);
                String name = (String) model.getValueAt(row, 1);
                String phone = (String) model.getValueAt(row, 2);
                try {
                    dao.updateClient(new Client(id, name, phone));
                } catch (SQLException ex) {
                    view.showMessage(ex.getMessage());
                }
            }
        }
    }

}
