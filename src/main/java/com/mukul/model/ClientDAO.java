package com.mukul.model;


import com.mukul.service.DBService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
    private final Connection conn;

    public ClientDAO() throws SQLException {
        conn = DBService.getInstance().getConnection();
    }

    public List<Client> getAllClients() throws SQLException {
        List<Client> clients = new ArrayList<>();

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM client");

        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String phone = rs.getString("phone");

            Client client = new Client(id, name, phone);
            clients.add(client);
        }

        return clients;
    }

    public void addClient(Client client) throws SQLException {
        String sql = "INSERT INTO client (name, phone) VALUES (?, ?)";

        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, client.getName());
        st.setString(2, client.getPhone());
        st.executeUpdate();
    }

    public void updateClient(Client client) throws SQLException {
        String sql = "UPDATE client SET name = ?, phone = ? WHERE id = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, client.getName());
        st.setString(2, client.getPhone());
        st.setInt(3, client.getId());
        st.executeUpdate();
    }

    public void deleteClient(Client client) throws SQLException {
        String sql = "DELETE FROM client WHERE id = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, client.getId());
        st.executeUpdate();
    }

}