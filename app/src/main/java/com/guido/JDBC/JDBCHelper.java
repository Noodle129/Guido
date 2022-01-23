package com.guido.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCHelper {
    private static Connection connection;

    static {
        try {
            Class.forName(JDBConstants.DRIVER_NAME);
        }
        catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection( JDBConstants.URL, JDBConstants.USERNAME, JDBConstants.PASSWORD );
        return connection;
    }

    public static void closeConnection(Connection con) throws SQLException {
        if (con != null)
            con.close();
    }

    public static void closePrepaerdStatement(PreparedStatement stmt) throws SQLException {
        if (stmt != null)
            stmt.close();
    }

    public static void closeResultSet(ResultSet rs) throws SQLException {
        if (rs != null)
            rs.close();
    }

    public static void printSQLExcep(SQLException e) {
        System.err.println("Error: " + e.getErrorCode());
        System.err.println("State: " + e.getSQLState());
        System.err.println("Message: " + e.getMessage());
    }
}