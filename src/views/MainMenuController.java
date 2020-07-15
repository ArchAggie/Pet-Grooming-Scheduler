
package views;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import models.Appointment;
import models.AppointmentDB;
import models.Groomer;
import models.Pet;
import models.PetDB;

/**
 * Brian Parsons
 * Student ID: 001008912
 * C195 - Software II - Advanced Java Concepts
 * Performance Assessment
 */

public class MainMenuController implements Initializable
{
    @FXML
    private AnchorPane mainMenuAnchorPane;
    @FXML
    private Label mainMenuTitleLabel;
    @FXML
    private Button viewAppointmentsButton;
    @FXML
    private Button viewPetsButton;
    @FXML
    private Button viewReport1Button;
    @FXML
    private Button viewReport2Button;
    @FXML
    private Button viewReport3Button;
    @FXML
    private Button viewLogButton;
    
    private String apptIn60Title;
    private String apptIn60HeaderYes;
    private String apptIn60MessageYes;
    private String apptIn60HeaderNo;
    private String apptIn60MessageNo;
    
    private static Groomer currentUser;
    
    public static Groomer getCurrentUser()
    {
        return currentUser;
    }

    @FXML
    public void handleViewAppointments(ActionEvent event)
    {
        try
        {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/views/ViewAppointments.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("View Appointments");
            stage.show();
            Stage viewPetsStage = (Stage) viewAppointmentsButton.getScene().getWindow();
            viewPetsStage.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleViewPets(ActionEvent event)
    {
        try
        {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/views/ViewPets.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("View Pets");
            stage.show();
            Stage viewPetsStage = (Stage) viewPetsButton.getScene().getWindow();
            viewPetsStage.close();
            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleViewReport1(ActionEvent event)
    {
        try
        {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/views/ViewReport1.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("View Reports");
            stage.show();
            Stage viewReport1Stage = (Stage) viewReport1Button.getScene().getWindow();
            viewReport1Stage.close();
            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void handleViewReport2(ActionEvent event)
    {
        try
        {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/views/ViewReport2.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("View Reports");
            stage.show();
            Stage viewReport2Stage = (Stage) viewReport2Button.getScene().getWindow();
            viewReport2Stage.close();
            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void handleViewReport3(ActionEvent event)
    {
        try
        {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/views/ViewReport3.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("View Reports");
            stage.show();
            Stage viewReport3Stage = (Stage) viewReport3Button.getScene().getWindow();
            viewReport3Stage.close();
            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleViewLog(ActionEvent event)
    {
        File file = new File("report_tracking.txt");
        if(file.exists())
        {
            if(Desktop.isDesktopSupported())
            {
                try 
                {
                    Desktop.getDesktop().open(file);
                } 
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        Appointment appointment = AppointmentDB.appointmentIn60Min();
        System.out.println("Appointment(s) (within 60 minutes of now) is " + appointment);
        
        rb = ResourceBundle.getBundle("lang/login");
        apptIn60Title       = rb.getString("apptIn60Title");
        apptIn60HeaderYes   = rb.getString("apptIn60HeaderYes");
        apptIn60MessageYes  = rb.getString("apptIn60MessageYes");
        apptIn60HeaderNo    = rb.getString("apptIn60HeaderNo");
        apptIn60MessageNo   = rb.getString("apptIn60MessageNo");
        
        // Give alert if an appointment is within 60 min of logging in to system
        if(appointment != null)
        {
            Pet pet = PetDB.getPet(appointment.getAppointmentPetID());
            String message1 = String.format(apptIn60MessageYes, 
                    appointment.getAppointmentType(), 
                    pet.getPetName(), 
                    appointment.get60Min());
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle(apptIn60Title);
            alert1.setHeaderText(apptIn60HeaderYes);
            alert1.setContentText(message1);
            alert1.showAndWait();
        }
        // Give alert that no appointments are within 60 min of logging in to system
        else
        {
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle(apptIn60Title);
            alert2.setHeaderText(apptIn60HeaderNo);
            alert2.setContentText(apptIn60MessageNo);
            alert2.showAndWait();
        }
    }
}
