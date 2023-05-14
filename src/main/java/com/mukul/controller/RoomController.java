package com.mukul.controller;

import com.mukul.model.Room;
import com.mukul.model.RoomDAO;
import com.mukul.view.RoomManagerView;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class RoomController {
    private final RoomDAO dao;
    private final RoomManagerView view;

    public RoomController(RoomDAO dao, RoomManagerView view) {
        this.dao = dao;
        this.view = view;

        refreshTable();

        view.addInsertListener(new InsertListener());
        view.addDeleteListener(new DeleteListener());
        view.addUpdateListener(new UpdateListener());
        view.addRefreshListener(new RefreshListener());
        view.addRoomTableModelListener(new TableUpdateListener());
    }

    private void refreshTable() {
        try {
            List<Room> Rooms = dao.getAllRooms();
            view.refreshTable(Rooms.toArray(new Room[0]));
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
                String phone = view.getPhone();
                boolean reserved = view.isReserved();
                dao.addRoom(new Room(id, phone, reserved));
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
                String phone = view.getPhone();
                boolean reserved = view.isReserved();
                dao.updateRoom(new Room(id, phone, reserved));
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
                boolean reserved = view.isReserved();
                String phone = view.getPhone();
                dao.deleteRoom(new Room(id, phone, reserved));
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
                DefaultTableModel model = view.getRoomTableModel();
                int id = (int) model.getValueAt(row, 0);
                String phone = (String) model.getValueAt(row, 1);
                boolean reserved = (boolean) model.getValueAt(row, 2);
                try {
                    dao.updateRoom(new Room(id, phone, reserved));
                } catch (SQLException ex) {
                    view.showMessage(ex.getMessage());
                }
            }
        }
    }

}
