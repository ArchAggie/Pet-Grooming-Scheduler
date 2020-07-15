
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Brian Parsons
 * Student ID: 001008912
 * C195 - Software II - Advanced Java Concepts
 * Performance Assessment
 */

public class DBConnection
{
    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//3.227.166.251/U06A5A";
    
    // JDBC URL
    private static final String jdbcURL = protocol + vendorName + ipAddress;
    
    // Driver and Connection Interface Reference
    private static final String MYSQLJDBCDriver = "com.mysql.jdbc.Driver";
    private static Connection conn = null;
    
    // DB Username
    private static final String username = "U06A5A";
    // DB Password
    private static final String password = "53688705354";
    
    public static Connection startConnection()
    {
        try
        {
            Class.forName(MYSQLJDBCDriver);
            conn = (Connection)DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connection Established!");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        catch (SQLException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        
        return conn;
    }
    
    public static void endConnection()
    {
        try
        {
            conn.close();
            System.out.println("Connection Terminated!");
        }
        catch (SQLException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public static java.sql.Connection getConnection()
    {
        return conn;
    }
}
