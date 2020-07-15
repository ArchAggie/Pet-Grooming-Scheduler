
package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZonedDateTime;

/**
 * Brian Parsons
 * Student ID: 001008912
 * C195 - Software II - Advanced Java Concepts
 * Performance Assessment
 */

public class Logger
{
    private static final String FILENAME = "report_tracking.txt";

    public static String log(String username)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Logger()
    {
        
    }
    
    public static void log (String username, boolean success)
    {
        try
        {
            FileWriter fw = new FileWriter(FILENAME, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println(ZonedDateTime.now() + " " + username + (success ? " Success" : " Failure"));
            pw.flush();
            pw.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public static void logReport1 (String username)
    {
        try
        {
            FileWriter fw = new FileWriter(FILENAME, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println(ZonedDateTime.now() + " Groomer '" + username + "' accessed the ApptByType Report");
            pw.flush();
            pw.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public static void logReport2 (String username)
    {
        try
        {
            FileWriter fw = new FileWriter(FILENAME, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println(ZonedDateTime.now() + " Groomer '" + username + "' accessed the ApptByCity Report");
            pw.flush();
            pw.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public static void logReport3 (String username)
    {
        try
        {
            FileWriter fw = new FileWriter(FILENAME, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println(ZonedDateTime.now() + " Groomer '" + username + "' accessed the SalesByGroomer Report");
            pw.flush();
            pw.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
