package com.mukul.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBService {
    static DBService instance;
    Connection conn;

    DBService() throws SQLException {
        conn = DriverManager.getConnection("jdbc:sqlite:app.db");
        createAllTables();
    }

    public static synchronized DBService getInstance() throws SQLException {
        if (instance == null) instance = new DBService();
        return instance;
    }

    public Connection getConnection() {
        return conn;
    }

    void createAllTables() throws SQLException {
        createClientTable();
    }

    void createClientTable() throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate("CREATE TABLE IF NOT EXISTS Client(id INTEGER PRIMARY KEY, name TEXT, phone INTEGER)");
    }
}
