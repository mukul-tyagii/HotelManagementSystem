package com.mukul.model;


import com.mukul.service.DBService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    private final Connection conn;

    public RoomDAO() throws SQLException {
        conn = DBService.getInstance().getConnection();
    }

    public List<Room> getAllRooms() throws SQLException {
        List<Room> Rooms = new ArrayList<>();

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Room");

        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            boolean isReserved = rs.getBoolean("reserved");

            Room Room = new Room(id, name, isReserved);
            Rooms.add(Room);
        }

        return Rooms;
    }

    public void addRoom(Room Room) throws SQLException {
        String sql = "INSERT INTO Room (phone, reserved) VALUES (?, ?)";

        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, Room.getPhone());
        st.setBoolean(2, Room.isReserved());
        st.executeUpdate();
    }

    public void updateRoom(Room Room) throws SQLException {
        String sql = "UPDATE Room SET reserved = ?, phone = ? WHERE id = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setBoolean(1, Room.isReserved());
        st.setString(2, Room.getPhone());
        st.setInt(3, Room.getId());
        st.executeUpdate();
    }

    public void deleteRoom(Room Room) throws SQLException {
        String sql = "DELETE FROM Room WHERE id = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, Room.getId());
        st.executeUpdate();
    }

}