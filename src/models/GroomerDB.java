
package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utils.DBConnection;
import utils.Logger;

/**
 * Brian Parsons
 * Student ID: 001008912
 * C195 - Software II - Advanced Java Concepts
 * Performance Assessment
 */

public class GroomerDB
{
    public static Groomer currentUser;
    
    public static Groomer getCurrentUser()
    {
        return currentUser;
    }
    
    public static boolean login(String username, String password)
    {
        try
        {
            Statement statement = DBConnection.getConnection().createStatement();
            String query = "SELECT * FROM `groomer` WHERE groomer_name = '" + username + "' AND groomer_password = '" + password + "'";
            ResultSet results = statement.executeQuery(query);
            
            if(results.next())
            {
                currentUser = new Groomer();
                currentUser.setGroomerName(results.getString("groomer_name"));
                statement.close();
                Logger.log(username, true);
                return true;
            }
            else
            {
                Logger.log(username, false);
                return false;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
