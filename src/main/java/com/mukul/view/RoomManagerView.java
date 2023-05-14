package com.mukul.view;

import com.mukul.model.Room;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.EventObject;
import java.util.Vector;

public class RoomManagerView {
    private final DefaultTableModel roomTableModel;
    private JButton refreshButton;
    private JTable roomTable;
    private JTextField phoneTextField;
    private JTextField IDTextField;
    private JButton insertButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JPanel rootPanel;
    private JCheckBox reservedCB;


    public RoomManagerView() {
        roomTableModel = new DefaultTableModel();
        roomTableModel.addColumn("ID");
        roomTableModel.addColumn("Phone");
        roomTableModel.addColumn("Reserved");
        roomTable.setModel(roomTableModel);
        roomTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        roomTable.putClientProperty("terminateEditOnFocusLost", true);

        roomTable.getSelectionModel().addListSelectionListener(e -> {
            int i = e.getLastIndex();
            if (i >= roomTableModel.getRowCount()) return;
            IDTextField.setText(String.valueOf((int) roomTableModel.getValueAt(i, 0)));
            phoneTextField.setText((String) roomTableModel.getValueAt(i, 1));
            reservedCB.setSelected((boolean) roomTableModel.getValueAt(i, 2));
        });

        roomTable.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(new JTextField()) {
            public boolean isCellEditable(EventObject e) {
                return false;
            }
        });

        roomTable.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new JTextField()) {
            public boolean isCellEditable(EventObject e) {
                return false;
            }
        });
    }

    public int getId() {
        try {
            return Integer.parseInt(IDTextField.getText());
        } catch (Exception e) {
            return -1;
        }
    }

    public boolean isReserved() {
        return reservedCB.isSelected();
    }

    public String getPhone() {
        return phoneTextField.getText();
    }

    public DefaultTableModel getRoomTableModel() {
        return roomTableModel;
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

    public void addRoomTableModelListener(TableModelListener listener) {
        roomTable.getModel().addTableModelListener(listener);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(rootPanel, message);
    }

    public Container getRootPanel() {
        return rootPanel;
    }

    public void refreshTable(Room[] rooms) {
        roomTableModel.setRowCount(0);
        for (Room room : rooms) {
            Vector<Object> row = new Vector<>();
            row.add(room.getId());
            row.add(room.getPhone());
            row.add(room.isReserved());
            roomTableModel.addRow(row);
        }
    }
}
