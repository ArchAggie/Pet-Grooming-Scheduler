
package models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Brian Parsons
 * Student ID: 001008912
 * C195 - Software II - Advanced Java Concepts
 * Performance Assessment
 */

public final class Groomer
{
    private final SimpleIntegerProperty groomerID       = new SimpleIntegerProperty();
    private final SimpleStringProperty  groomerName     = new SimpleStringProperty();
    private final SimpleStringProperty  groomerPassword = new SimpleStringProperty();
    
    private int     groomerIDReport;
    private double  groomerSalesTotalReport;
    
    public Groomer ()
    {
        
    }
    
    // ReportThree Constructor
    public Groomer (int gIDReport, double gSalesTotalReport)
    {
        groomerIDReport = gIDReport;
        groomerSalesTotalReport = gSalesTotalReport;
    }

    /**
     * @return the groomerIDReport
     */
    public int getGroomerIDReport()
    {
        return groomerIDReport;
    }

    /**
     * @return the groomerSalesTotalReport
     */
    public double getGroomerSalesTotalReport()
    {
        return groomerSalesTotalReport;
    }

    /**
     * @param groomerIDReport the groomerIDReport to set
     */
    public void setGroomerIDReport(int groomerIDReport)
    {
        this.groomerIDReport = groomerIDReport;
    }

    /**
     * @param groomerSalesTotalReport the groomerSalesTotalReport to set
     */
    public void setGroomerSalesTotalReport(double groomerSalesTotalReport)
    {
        this.groomerSalesTotalReport = groomerSalesTotalReport;
    }
    
    public Groomer(int gID, String gName, String gPass)
    {
        setGroomerID(gID);
        setGroomerName(gName);
        setGroomerPassword(gPass);
    }
    
    public int getGroomerID()
    {
        return groomerID.get();
    }
    
    public String getGroomerName()
    {
        return groomerName.get();
    }
    
    public String getGroomerPassword()
    {
        return groomerPassword.get();
    }
    
    public void setGroomerID(int groomerID)
    {
        this.groomerID.set(groomerID);
    }
    
    public void setGroomerName(String groomerName)
    {
        this.groomerName.set(groomerName);
    }
    
    public void setGroomerPassword(String groomerPassword)
    {
        this.groomerPassword.set(groomerPassword);
    }
}
