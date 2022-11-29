package com.epam.rd.autocode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionSource {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/Employee-Manager";

    //  Database credentials
    private static final String USER = "postgres";
    private static final String PASS = "postgres";

    private static final ConnectionSource instance = new ConnectionSource();

    public static ConnectionSource instance() {
        return instance;
    }

    public Connection createConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    private ConnectionSource() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
