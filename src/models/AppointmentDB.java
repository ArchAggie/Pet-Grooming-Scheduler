
package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import utils.DBConnection;

/**
 * Brian Parsons
 * Student ID: 001008912
 * C195 - Software II - Advanced Java Concepts
 * Performance Assessment
 */

public class AppointmentDB
{
    private static final ZoneId zID = ZoneId.systemDefault();
    
    private static final Appointment currentAppointment = new Appointment();
    
    public static Appointment getAppointment()
    {
        return currentAppointment;
    }
    
    public static ObservableList<Appointment> getAllAppointments()
    {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        
        System.out.println("Retrieving all appointments from database");
        allAppointments.clear();
        
        try
        {
            Statement statement = DBConnection.getConnection().createStatement();
            String query = "SELECT `appointment`.`appointment_id`, `appointment`.`pet_id`, `appointment`.`groomer_id`, `pet`.`pet_name`, `pettype`.`pettype_description`, "
                    + "`groomer`.`groomer_name`, `appointment`.`appointment_description`, `appointment`.`start_datetime`, "
                    + "`servicetype`.`servicetype_description`, `appointment`.`service_cost`, `appointment`.`service_location` "
                    + "FROM appointment, pet, pettype, groomer, servicetype WHERE `appointment`.`pet_id` = `pet`.`pet_id` AND "
                    + "`appointment`.`groomer_id` = `groomer`.`groomer_id` AND `appointment`.`servicetype_id` = `servicetype`.`servicetype_id` "
                    + "AND `pet`.`pettype_id` = `pettype`.`pettype_id` ORDER BY `appointment`.`appointment_id`";
            ResultSet results = statement.executeQuery(query);
            
            while(results.next())
            {
                Appointment appointment = new Appointment(
                        results.getInt("appointment_id"), 
                        results.getInt("pet_id"), 
                        results.getInt("groomer_id"), 
                        results.getString("pet_name"), 
                        results.getString("pettype_description"), 
                        results.getString("groomer_name"), 
                        results.getString("appointment_description"), 
                        results.getString("start_datetime"), 
                        results.getString("start_datetime"), 
                        results.getString("servicetype_description"), 
                        results.getDouble("service_cost"),
                        results.getString("service_location"));
                allAppointments.add(appointment);
                System.out.println("Appointment ID: " + results.getInt("appointment_id"));
            }
            return allAppointments;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public static ObservableList<Appointment> getJanAppointments()
    {
        ObservableList<Appointment> appointments = getAllAppointments();
        ObservableList<Appointment> janAppointments = appointments.filtered(a -> 
        {
            if(a.getDateOnly().getMonth() == Month.JANUARY)
                return true;
            else
                return false;
        });
        return janAppointments;
    }
    
    public static ObservableList<Appointment> getFebAppointments()
    {
        ObservableList<Appointment> appointments = getAllAppointments();
        ObservableList<Appointment> febAppointments = appointments.filtered(a -> 
        {
            if(a.getDateOnly().getMonth() == Month.FEBRUARY)
                return true;
            else
                return false;
        });
        return febAppointments;
    }
    
    public static ObservableList<Appointment> getMarAppointments()
    {
        ObservableList<Appointment> appointments = getAllAppointments();
        ObservableList<Appointment> marAppointments = appointments.filtered(a -> 
        {
            if(a.getDateOnly().getMonth() == Month.MARCH)
                return true;
            else
                return false;
        });
        return marAppointments;
    }
    
    public static ObservableList<Appointment> getAprAppointments()
    {
        ObservableList<Appointment> appointments = getAllAppointments();
        ObservableList<Appointment> aprAppointments = appointments.filtered(a -> 
        {
            if(a.getDateOnly().getMonth() == Month.APRIL)
                return true;
            else
                return false;
        });
        return aprAppointments;
    }
    
    public static ObservableList<Appointment> getMayAppointments()
    {
        ObservableList<Appointment> appointments = getAllAppointments();
        ObservableList<Appointment> mayAppointments = appointments.filtered(a -> 
        {
            if(a.getDateOnly().getMonth() == Month.MAY)
                return true;
            else
                return false;
        });
        return mayAppointments;
    }
    
    public static ObservableList<Appointment> getJunAppointments()
    {
        ObservableList<Appointment> appointments = getAllAppointments();
        ObservableList<Appointment> junAppointments = appointments.filtered(a -> 
        {
            if(a.getDateOnly().getMonth() == Month.JUNE)
                return true;
            else
                return false;
        });
        return junAppointments;
    }
    
    public static ObservableList<Appointment> getJulAppointments()
    {
        ObservableList<Appointment> appointments = getAllAppointments();
        ObservableList<Appointment> julAppointments = appointments.filtered(a -> 
        {
            if(a.getDateOnly().getMonth() == Month.JULY)
                return true;
            else
                return false;
        });
        return julAppointments;
    }
    
    public static ObservableList<Appointment> getAugAppointments()
    {
        ObservableList<Appointment> appointments = getAllAppointments();
        ObservableList<Appointment> augAppointments = appointments.filtered(a -> 
        {
            if(a.getDateOnly().getMonth() == Month.AUGUST)
                return true;
            else
                return false;
        });
        return augAppointments;
    }
    
    public static ObservableList<Appointment> getSepAppointments()
    {
        ObservableList<Appointment> appointments = getAllAppointments();
        ObservableList<Appointment> sepAppointments = appointments.filtered(a -> 
        {
            if(a.getDateOnly().getMonth() == Month.SEPTEMBER)
                return true;
            else
                return false;
        });
        return sepAppointments;
    }
    
    public static ObservableList<Appointment> getOctAppointments()
    {
        ObservableList<Appointment> appointments = getAllAppointments();
        ObservableList<Appointment> octAppointments = appointments.filtered(a -> 
        {
            if(a.getDateOnly().getMonth() == Month.OCTOBER)
                return true;
            else
                return false;
        });
        return octAppointments;
    }
    
    public static ObservableList<Appointment> getNovAppointments()
    {
        ObservableList<Appointment> appointments = getAllAppointments();
        ObservableList<Appointment> novAppointments = appointments.filtered(a -> 
        {
            if(a.getDateOnly().getMonth() == Month.NOVEMBER)
                return true;
            else
                return false;
        });
        return novAppointments;
    }
    
    public static ObservableList<Appointment> getDecAppointments()
    {
        ObservableList<Appointment> appointments = getAllAppointments();
        ObservableList<Appointment> decAppointments = appointments.filtered(a -> 
        {
            if(a.getDateOnly().getMonth() == Month.DECEMBER)
                return true;
            else
                return false;
        });
        return decAppointments;
    }
    
    public static Appointment appointmentIn60Min()
    {
        Appointment appointment;
        LocalDateTime now   = LocalDateTime.now();
        ZoneId zid          = ZoneId.systemDefault();
        ZonedDateTime zdt   = now.atZone(zid);
        LocalDateTime ldt   = zdt.withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();
        LocalDateTime ldt2  = ldt.plusHours(1);
        String user         = GroomerDB.getCurrentUser().getGroomerName();
        try
        {
            Statement statement = DBConnection.getConnection().createStatement();
            String query = "SELECT * FROM `appointment`, `groomer` WHERE `start_datetime` BETWEEN '" + ldt + "' AND '" 
                    + ldt2 + "' AND `groomer_name` = '" + user + "'";
            ResultSet results = statement.executeQuery(query);
            System.out.println(query);
            System.out.println(results);
            if(results.next())
            {
                // int apptID, int apptPID, int apptGID, String apptType, String apptDate, String apptStart, 
                // String apptEnd, double apptCost, String apptLocation
                appointment = new Appointment(
                        results.getInt("appointment_id"), 
                        results.getInt("pet_id"), 
                        results.getInt("groomer_id"), 
                        results.getString("servicetype_id"), 
                        results.getString("groomer_name"), 
                        results.getString("start_datetime"), 
                        results.getString("end_datetime"), 
                        results.getDouble("service_cost"), 
                        results.getString("service_location"));
                return appointment;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    public static boolean addAppointment(int petID, String petName, int groomerID, String groomerName, int apptTypeID, 
            String apptType, double apptCost, String apptDesc, LocalDate apptDate, String apptTime, String apptLocation)
    {
        String tsStart = createTimeStamp(apptDate, apptTime, apptLocation, true);
        String tsEnd = createTimeStamp(apptDate, apptTime, apptLocation, false);
        int apptID = 0;
        
        try
        {
            Statement statement = DBConnection.getConnection().createStatement();
            ResultSet results = statement.executeQuery("SELECT MAX(appointment_id) FROM appointment");
            if (results.next())
            {
                apptID = results.getInt(1);
                apptID++;
            }
            
            String query = "INSERT INTO `appointment` SET `appointment_id` = " + apptID + ", `pet_id` = " + petID + ", "
                    + "`groomer_id` = " + groomerID + ", `servicetype_id` = " + apptTypeID + ", `start_datetime` = '" + tsStart + "', "
                    + "`end_datetime` = '" + tsEnd + "', `service_cost` = " + apptCost + ", `appointment_description` = '" + apptDesc + "', "
                    + "`service_location` = '" + apptLocation + "', `appointment_status` = 1, `created_at` = NOW(), `created_by` = '', "
                    + "`updated_at` = NOW(), `updated_by` = ''";
            int update = statement.executeUpdate(query);
            if(update == 1)
            {
                return true;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }
    
    public static boolean modifyAppointment(int apptID, int petID, String petName, int groomerID, String groomerName, int apptTypeID, 
            String apptType, double apptCost, String apptDesc, LocalDate apptDate, String apptTime, String apptLocation)
    {
        String tsStart = createTimeStamp(apptDate, apptTime, apptLocation, true);
        String tsEnd = createTimeStamp(apptDate, apptTime, apptLocation, false);
        
        try
        {
            Statement statement = DBConnection.getConnection().createStatement();
            String query = "UPDATE `appointment` SET `pet_id` = " + petID + ", `groomer_id` = " + groomerID + ", "
                    + "`servicetype_id` = " + apptTypeID + ", `start_datetime` = '" + tsStart + "', "
                    + "`end_datetime` = '" + tsEnd + "', `service_cost` = " + apptCost + ", "
                    + "`appointment_description` = '" + apptDesc + "', `service_location` = '" + apptLocation + "', "
                    + "`updated_at` = NOW() WHERE `appointment_id` = " + apptID;
            
            int update = statement.executeUpdate(query);
            if(update == 1)
            {
                return true;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }
    
    public static boolean addOverlappingAppointment(int id, int petID, int groomerID, String location, LocalDate date, String time)
    {
        String start = createTimeStamp(date, time, location, true);
        try
        {
            Statement statement = DBConnection.getConnection().createStatement();
            String samePet      = "SELECT * FROM `appointment` WHERE `pet_id` = " + petID + " AND `start_datetime` = '" + start + "'";
            String sameGroomer  = "SELECT * FROM `appointment` WHERE `groomer_id` = " + groomerID + " AND `start_datetime` = '" + start + "'";
            
            ResultSet results1 = statement.executeQuery(samePet);
            if(results1.next())
            {
                statement.close();
                return true;
            }
            
            ResultSet results3 = statement.executeQuery(sameGroomer);
            if(results3.next())
            {
                statement.close();
                return true;
            }
            else
            {
                statement.close();
                return false;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return true;
        }
    }
    
    public static boolean modifyOverlappingAppointment(int id, int petID, int groomerID, String location, LocalDate date, String time)
    {
        String start = createTimeStamp(date, time, location, true);
        try
        {
            Statement statement = DBConnection.getConnection().createStatement();
            String samePet      = "SELECT * FROM `appointment` WHERE `pet_id` = " + petID + " AND `appointment_id` <> " + id + " AND `start_datetime` = '" + start + "'";
            String sameGroomer  = "SELECT * FROM `appointment` WHERE `groomer_id` = " + groomerID + " AND `appointment_id` <> " + id + " AND `start_datetime` = '" + start + "'";
            
            ResultSet results1 = statement.executeQuery(samePet);
            if(results1.next())
            {
                statement.close();
                return true;
            }
            
            ResultSet results3 = statement.executeQuery(sameGroomer);
            if(results3.next())
            {
                statement.close();
                return true;
            }
            else
            {
                statement.close();
                return false;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return true;
        }
    }
    
    public static boolean deleteAppointment(int id)
    {
        try
        {
            Statement statement = DBConnection.getConnection().createStatement();
            String query = "DELETE FROM `appointment` WHERE `appointment_id` = " + id;
            int update = statement.executeUpdate(query);
            if(update == 1)
            {
                return true;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }
    
    public static String createTimeStamp(LocalDate date, String time, String location, boolean startMode)
    {
        DateTimeFormatter df1 = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime lt = LocalTime.parse(time, df1);
        LocalDateTime ldt = LocalDateTime.of(date, lt);
        ZoneId zid = ZoneId.systemDefault();
        
//        if(location.equals("New York City"))
//        {
//            zid = ZoneId.of("America/New_York");
//        }
//        else
//        {
//            zid = ZoneId.of("America/Mexico_City");
//        }
        
        ZonedDateTime zdt = ldt.atZone(zid);
        ZonedDateTime utcDate = zdt.withZoneSameInstant(ZoneId.of("UTC"));
        ldt = utcDate.toLocalDateTime();
        Timestamp ts = Timestamp.valueOf(ldt);
        return ts.toString();
    }
}
