package com.mukul.view;

import com.mukul.model.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ClientManagerView {
    private final DefaultTableModel clientTableModel;
    private JPanel rootPanel;
    private JTable clientTable;
    private JTextField nameTextField;
    private JTextField IDTextField;
    private JTextField phoneTextField;
    private JButton refreshButton;
    private JButton insertButton;
    private JButton updateButton;
    private JButton deleteButton;

    public ClientManagerView() {
        clientTableModel = new DefaultTableModel();
        clientTableModel.addColumn("ID");
        clientTableModel.addColumn("Name");
        clientTableModel.addColumn("Phone");
        clientTable.setModel(clientTableModel);
    }

    public int getId() {
        try {
            return Integer.parseInt(IDTextField.getText());
        } catch (Exception e) {
            return -1;
        }
    }

    public String getName() {
        return nameTextField.getText();
    }

    public String getPhone() {
        return phoneTextField.getText();
    }

    public void addInsertListener(ActionListener listener) {
        insertButton.addActionListener(listener);
    }

    public void addUpdateListener(ActionListener listener) {
        updateButton.addActionListener(listener);
    }

    public void addDeleteListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }

    public void addRefreshListener(ActionListener listener) {
        refreshButton.addActionListener(listener);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(rootPanel, message);
    }

    public Container getRootPanel() {
        return rootPanel;
    }

    public void refreshTable(Client[] clients) {
        clientTableModel.setRowCount(0);
        for (Client client : clients) {
            Vector<Object> row = new Vector<>();
            row.add(client.getId());
            row.add(client.getName());
            row.add(client.getPhone());
            clientTableModel.addRow(row);
        }
    }

}
