
package models;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.StringProperty;

/**
 * Brian Parsons
 * Student ID: 001008912
 * C195 - Software II - Advanced Java Concepts
 * Performance Assessment
 */

public final class Appointment
{
    private final IntegerProperty   appointmentID           = new SimpleIntegerProperty();
    private final IntegerProperty   appointmentPetID        = new SimpleIntegerProperty();
    private final IntegerProperty   appointmentGroomerID    = new SimpleIntegerProperty();
    private final StringProperty    appointmentDescription  = new SimpleStringProperty();
    private final StringProperty    appointmentPetName      = new SimpleStringProperty();
    private final StringProperty    appointmentPetType      = new SimpleStringProperty();
    private final StringProperty    appointmentGroomerName  = new SimpleStringProperty();
    private final StringProperty    appointmentType         = new SimpleStringProperty();
    private final StringProperty    appointmentDate         = new SimpleStringProperty();
    private final StringProperty    appointmentStart        = new SimpleStringProperty();
    private final StringProperty    appointmentEnd          = new SimpleStringProperty();
    private final DoubleProperty    appointmentCost         = new SimpleDoubleProperty();
    private final StringProperty    appointmentLocation     = new SimpleStringProperty();
    
    private int appointmentTypeIDReport;
    private int appointmentCountReport;
    
    // Empty Constructor
    public Appointment()
    {
        
    }
    
    // ReportOne Constructor
    public Appointment(int apptTIDReport, int apptCountReport)
    {
        appointmentTypeIDReport = apptTIDReport;
        appointmentCountReport = apptCountReport;
    }

    /**
     * @return the appointmentTypeIDReport
     */
    public int getAppointmentTypeIDReport()
    {
        return appointmentTypeIDReport;
    }

    /**
     * @return the appointmentCountReport
     */
    public int getAppointmentCountReport()
    {
        return appointmentCountReport;
    }

    /**
     * @param appointmentTypeIDReport the appointmentTypeIDReport to set
     */
    public void setAppointmentTypeIDReport(int appointmentTypeIDReport)
    {
        this.appointmentTypeIDReport = appointmentTypeIDReport;
    }

    /**
     * @param appointmentCountReport the appointmentCountReport to set
     */
    public void setAppointmentCountReport(int appointmentCountReport)
    {
        this.appointmentCountReport = appointmentCountReport;
    }
    
    // Appointments Table Constructor
    public Appointment(int apptID, int apptPID, int apptGID, String apptPetName, String apptPetType, String apptGroomerName, 
            String apptDescription, String apptDate, String apptStart, String apptType, double apptCost, String apptLocation)
    {
        setAppointmentID(apptID);
        setAppointmentPetID(apptPID);
        setAppointmentGroomerID(apptGID);
        setAppointmentPetName(apptPetName);
        setAppointmentPetType(apptPetType);
        setAppointmentGroomerName(apptGroomerName);
        setAppointmentDescription(apptDescription);
        setAppointmentDate(apptDate);
        setAppointmentStart(apptStart);
        setAppointmentType(apptType);
        setAppointmentCost(apptCost);
        setAppointmentLocation(apptLocation);
    }
    
    // General Constructor
    public Appointment(int apptID, int apptPID, int apptGID, String apptType, String apptDate, String apptStart, 
            String apptEnd, double apptCost, String apptLocation)
    {
        setAppointmentID(apptID);
        setAppointmentPetID(apptPID);
        setAppointmentGroomerID(apptGID);
        setAppointmentType(apptType);
        setAppointmentDate(apptDate);
        setAppointmentStart(apptStart);
        setAppointmentEnd(apptEnd);
        setAppointmentCost(apptCost);
        setAppointmentLocation(apptLocation);
    }
    
    /**
     * @return the appointmentID
     */
    public int getAppointmentID()
    {
        return appointmentID.get();
    }
    
    /**
     * @return the appointmentPetID
     */
    public int getAppointmentPetID()
    {
        return appointmentPetID.get();
    }
    
    /**
     * @return the appointmentGroomerID
     */
    public int getAppointmentGroomerID()
    {
        return appointmentGroomerID.get();
    }
    
    /**
     * @return the appointmentGroomerID
     */
    public String getAppointmentGroomerName()
    {
        return appointmentGroomerName.get();
    }
    
    /**
     * @return the appointmentGroomerID
     */
    public String getAppointmentDescription()
    {
        return appointmentDescription.get();
    }
    
    /**
     * @return the appointmentType
     */
    public String getAppointmentType()
    {
        return appointmentType.get();
    }
    
    /**
     * @return the appointmentDate
     */
    public String getAppointmentDate()
    {
        return getApptDateProperty().get();
    }
    
    /**
     * @return the appointmentStart
     */
    public String getAppointmentStart()
    {
        return getApptStartProperty().get();
    }
    
    /**
     * @return the appointmentEnd
     */
    public String getAppointmentEnd()
    {
        return appointmentEnd.get();
    }
    
    /**
     * @return the appointmentCost
     */
    public double getAppointmentCost()
    {
        return appointmentCost.get();
    }
    
    /**
     * @return the appointmentLocation
     */
    public String getAppointmentLocation()
    {
        return appointmentLocation.get();
    }
    
    /**
     * @return the appointmentLocation
     */
    public String getAppointmentPetName()
    {
        return appointmentPetName.get();
    }
    
    /**
     * @return the appointmentLocation
     */
    public String getAppointmentPetType()
    {
        return appointmentPetType.get();
    }
    
    
    
    public void setAppointmentID(int appointmentID)
    {
        this.appointmentID.set(appointmentID);
    }
    
    public void setAppointmentPetID(int appointmentPetID)
    {
        this.appointmentPetID.set(appointmentPetID);
    }
    
    public void setAppointmentGroomerID(int appointmentGroomerID)
    {
        this.appointmentGroomerID.set(appointmentGroomerID);
    }
    
    public void setAppointmentDescription(String appointmentDescription)
    {
        this.appointmentDescription.set(appointmentDescription);
    }
    
    public void setAppointmentType(String appointmentType)
    {
        this.appointmentType.set(appointmentType);
    }
    
    public void setAppointmentDate(String appointmentDate)
    {
        this.appointmentDate.set(appointmentDate);
    }
    
    public void setAppointmentStart(String appointmentStart)
    {
        this.appointmentStart.set(appointmentStart);
    }
    
    public void setAppointmentEnd(String appointmentEnd)
    {
        this.appointmentEnd.set(appointmentEnd);
    }
    
    public void setAppointmentCost(double appointmentCos)
    {
        this.appointmentCost.set(appointmentCos);
    }
    
    public void setAppointmentLocation(String appointmentLocation)
    {
        this.appointmentLocation.set(appointmentLocation);
    }
    
    public void setAppointmentPetName(String appointmentPetName)
    {
        this.appointmentPetName.set(appointmentPetName);
    }
    
    public void setAppointmentPetType(String appointmentPetType)
    {
        this.appointmentPetType.set(appointmentPetType);
    }
    
    public void setAppointmentGroomerName(String appointmentGroomerName)
    {
        this.appointmentGroomerName.set(appointmentGroomerName);
    }
    
    public IntegerProperty getAppointmentIDProperty()
    {
        return this.appointmentID;
    }
    
    public IntegerProperty getAppointmentPetIDProperty()
    {
        return this.appointmentPetID;
    }
    
    public IntegerProperty getAppointmentGroomerIDProperty()
    {
        return this.appointmentGroomerID;
    }
    
    public StringProperty getAppointmentDescriptionProperty()
    {
        return this.appointmentDescription;
    }
    
    public StringProperty getAppointmentTypeProperty()
    {
        return this.appointmentType;
    }
    
    public StringProperty getApptStartProperty()
    {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"); 
 	LocalDateTime ldt = LocalDateTime.parse(this.appointmentStart.getValue(), df);
        ZonedDateTime zdt = ldt.atZone(ZoneId.of("UTC"));
        ZoneId zid = ZoneId.systemDefault();
        ZonedDateTime utcDate = zdt.withZoneSameInstant(zid);
        DateTimeFormatter df1 = DateTimeFormatter.ofPattern("HH:mm");
        StringProperty date = new SimpleStringProperty(df1.format(utcDate.toLocalDateTime()));
        return date;
    }
    
    public StringProperty getApptDateProperty()
    {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"); 
 	LocalDateTime ldt = LocalDateTime.parse(this.appointmentDate.getValue(), df);
        ZonedDateTime zdt = ldt.atZone(ZoneId.of("UTC"));
        ZoneId zid = ZoneId.systemDefault();
        ZonedDateTime utcDate = zdt.withZoneSameInstant(zid);
        DateTimeFormatter df1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        StringProperty date = new SimpleStringProperty(df1.format(utcDate.toLocalDateTime()));
        return date;
    }
    
    public StringProperty getApptEndProperty()
    {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"); 
 	LocalDateTime ldt = LocalDateTime.parse(this.appointmentEnd.getValue(), df);
        ZonedDateTime zdt = ldt.atZone(ZoneId.of("UTC"));
        ZoneId zid = ZoneId.systemDefault();
        ZonedDateTime utcDate = zdt.withZoneSameInstant(zid); 
        StringProperty date = new SimpleStringProperty(utcDate.toLocalDateTime().toString());
        return date;
    }
    
    public StringProperty getAppointmentLocationProperty()
    {
        return this.appointmentLocation;
    }
    
    public StringProperty getAppointmentPetNameProperty()
    {
        return this.appointmentPetName;
    }
    
    public StringProperty getAppointmentPetTypeProperty()
    {
        return this.appointmentPetType;
    }
    
    public StringProperty getAppointmentGroomerNameProperty()
    {
        return this.appointmentGroomerName;
    }
    
    public DoubleProperty getAppointmentCostProperty()
    {
        return this.appointmentCost;
    }
    
    public LocalDate getDateOnly()
    {
        Timestamp ts = Timestamp.valueOf(this.appointmentStart.get());
        ZonedDateTime zdt;
        ZoneId zid;
        LocalDate ld;
        
        switch (this.appointmentLocation.get()) {
            case "New York":
                zid = ZoneId.of("America/New_York");
                break;
            case "Phoenix":
                zid = ZoneId.of("America/Phoenix");
                break;
            default:
                zid = ZoneId.systemDefault();
                break;
        }
        zdt = ts.toLocalDateTime().atZone(zid);
        ld = zdt.toLocalDate();
        return ld;
    }
    
    public String getTimeOnly()
    {
        Timestamp ts = Timestamp.valueOf(this.appointmentStart.get());
        ZonedDateTime zdt;
        ZoneId zid;
        LocalTime lt = null;
        
        switch (this.appointmentLocation.get()) {
            case "New York City":
                zid = ZoneId.of("America/New_York");
                zdt = ts.toLocalDateTime().atZone(zid);
                lt = zdt.toLocalTime().minusHours(5);
                break;
            case "Mexico City":
                zid = ZoneId.of("America/Mexico_City");
                zdt = ts.toLocalDateTime().atZone(zid);
                lt = zdt.toLocalTime().minusHours(6);
                break;
        }
        int rawH = Integer.parseInt(lt.toString().split(":")[0]);
        if(rawH > 12)
        {
            rawH -= 12;
        }
        String ampm;
        if(rawH < 9 || rawH == 12)
        {
            ampm = "PM";
        }
        else
        {
            ampm = "AM";
        }
        String time = rawH + ":00 " + ampm;
        return time;
    }
    
    public String get60Min()
    {
        String fullPattern = "yyyy-MM-dd HH:mm:ss.S";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(fullPattern);
        LocalDateTime ldt = LocalDateTime.parse(this.appointmentStart.getValue(), dtf);
        ZonedDateTime zdt = ldt.atZone(ZoneId.of("UTC"));
        ZoneId zid = ZoneId.systemDefault();
        ZonedDateTime utcDate = zdt.withZoneSameInstant(zid);
        String timePattern = "HH:mm";
        DateTimeFormatter tFormatter = DateTimeFormatter.ofPattern(timePattern);
        LocalTime lt = LocalTime.parse(utcDate.toString().substring(11, 16), tFormatter);
        return lt.toString();
    }
}
