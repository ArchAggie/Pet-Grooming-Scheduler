
package views;

import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import models.Pet;
import models.PetDB;
import models.AppointmentDB;

/**
 * Brian Parsons
 * Student ID: 001008912
 * C195 - Software II - Advanced Java Concepts
 * Performance Assessment
 */

public class AddAppointmentController implements Initializable
{
    private int id;
    @FXML
    private AnchorPane addAppointmentAnchorPane;
    @FXML
    private Label addAppointmentLabel;
    @FXML
    private Label petNameLabel;
    @FXML
    private ComboBox<Pet> petNameComboBox;
    @FXML
    private Label petTypeLabel;
    @FXML
    private Label petTypeText;
    @FXML
    private Label groomerLabel;
    @FXML
    private ComboBox<String> groomerComboBox;
    @FXML
    private Label apptTypeLabel;
    @FXML
    private ComboBox<String> apptTypeComboBox;
    @FXML
    private Label apptCostLabel;
    @FXML
    private TextField apptCostTextField;
    @FXML
    private Label apptDescriptionLabel;
    @FXML
    private TextField apptDescriptionTextField;
    @FXML
    private Label apptDateLabel;
    @FXML
    private DatePicker apptDatePicker;
    @FXML
    private Label apptStartLabel;
    @FXML
    private ComboBox<String> apptStartComboBox;
    @FXML
    private Label apptDurationLabel;
    @FXML
    private Label apptDurationText;
    @FXML
    private Label apptLocationLabel;
    @FXML
    private Label apptLocationText;
    @FXML
    private Button addAppointmentButton;
    @FXML
    private Button cancelAppointmentButton;
    
    private final ObservableList<String> groomers = FXCollections.observableArrayList("admin", "test");
    
    private final ObservableList<String> appointmentTypes = FXCollections.observableArrayList("shampoo", "nails", "trim");
    
    private final ObservableList<String> apptStartTimes = FXCollections.observableArrayList("10:00", "11:00", "12:00", 
                        "13:00", "14:00", "15:00");
    private final ObservableList<String> errors = FXCollections.observableArrayList();
    
    @FXML
    public boolean handleAddAppointment(ActionEvent event)
    {
        errors.clear();
        int petNameInt      = petNameComboBox.getValue().getPetID();
        String petName      = petNameComboBox.getSelectionModel().getSelectedItem().getPetName();
        int groomerID       = groomerComboBox.getSelectionModel().getSelectedIndex() + 1;
        String groomerName  = groomerComboBox.getSelectionModel().getSelectedItem();
        int apptTypeID      = apptTypeComboBox.getSelectionModel().getSelectedIndex() + 1;
        String apptType     = apptTypeComboBox.getSelectionModel().getSelectedItem();
        String apptCost     = apptCostTextField.getText();
        String apptDesc     = apptDescriptionTextField.getText();
        LocalDate apptDate  = apptDatePicker.getValue();
        String apptTime     = apptStartComboBox.getSelectionModel().getSelectedItem();
        String apptLocation = apptLocationText.getText();
        
        if(AppointmentDB.addOverlappingAppointment(-1, petNameInt, groomerID, apptLocation, apptDate, apptTime))
        {
            errors.add("Overlapping Appointments");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Modify Appointment Error");
            alert.setContentText(displayErrors());
            alert.show();
            return false;
        }
        if (!validateCost(apptCost) || !validateDescription(apptDesc) || !validateApptDate(apptDate))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Add Appointment Error");
            alert.setContentText(displayErrors());
            alert.show();
            return false;
        }
        else
        {
            System.out.println("The appointment you are trying to add has been successully validated");
            
            boolean b = AppointmentDB.addAppointment(petNameInt, petName, groomerID, groomerName, apptTypeID, apptType, 
                    Double.parseDouble(apptCostTextField.getText()), apptDesc, apptDate, apptTime, apptLocation);
            if(b)
            {
                try
                {
                    FXMLLoader loader = new FXMLLoader(AddPetController.class.getResource("/views/ViewAppointments.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("View Reports");
                    stage.show();
                    Stage addPetStage = (Stage) addAppointmentButton.getScene().getWindow();
                    addPetStage.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            return b;
        }
    }
    
    @FXML
    public void handleCancelAddAppointment(ActionEvent event)
    {
        try
        {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/views/ViewAppointments.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("View Appointments");
            stage.show();
            Stage addApptStage = (Stage) cancelAppointmentButton.getScene().getWindow();
            addApptStage.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void handleLocation()
    {
        String chosenGroomer = groomerComboBox.getSelectionModel().getSelectedItem();
        if(chosenGroomer == null)
        {
            apptLocationText.setText("No Groomer currently chosen");
            return;
        }
        switch (chosenGroomer)
        {
            case "admin":
                apptLocationText.setText("New York City");
                break;
            case "test":
                apptLocationText.setText("Mexico City");
                break;
            default:
                apptLocationText.setText("No Groomer currently chosen");
                break;
        }
    }
    
    @FXML
    public void handlePetType()
    {
        Pet pet = petNameComboBox.getSelectionModel().getSelectedItem();
        if(pet == null)
        {
            petTypeText.setText("No Pet currently chosen");
            return;
        }
        switch (pet.getPetType())
        {
            case "dog":
                petTypeText.setText("Dog");
                break;
            case "cat":
                petTypeText.setText("Cat");
                break;
            case "hamster":
                petTypeText.setText("Hamster");
                break;
            default:
                petTypeText.setText("No Pet currently chosen");
                break;
        }
    }
    
    public boolean validateCost(String cost)
    {
        if(cost.isEmpty())
        {
            errors.add("A valid appointment cost must be entered.");
            return false;
        }
        else if (!cost.matches("[0-9.]*$"))
        {
            errors.add("The appointment cost may only have numbers and decimals.");
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public boolean validateDescription(String desc)
    {
        if(desc.isEmpty())
        {
            errors.add("A valid appointment cost must be entered.");
            return false;
        }
        else if (!desc.matches("[a-zA-Z ]*$"))
        {
            errors.add("The appointment cost may only have numbers and decimals.");
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public boolean validateApptDate(LocalDate apptDate)
    {
        if(apptDate == null)
        {
            errors.add("An Appointment Date must be selected");
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public String displayErrors()
    {
        String s = "";
        if(errors.size() > 0)
        {
            for(String err : errors)
            {
                s = s.concat(err);
            }
            return s;
        }
        else
        {
            s = "Database Error";
            return s;
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
        petNameComboBox.setItems(PetDB.getAllPets());
        petNameComboBox.getSelectionModel().selectFirst();
        
        groomerComboBox.setItems(groomers);
        groomerComboBox.getSelectionModel().selectFirst();
        
        apptTypeComboBox.setItems(appointmentTypes);
        apptTypeComboBox.getSelectionModel().selectFirst();
        
        apptStartComboBox.setItems(apptStartTimes);
        apptStartComboBox.getSelectionModel().selectFirst();
        
        handlePetType();
        handleLocation();
        
        // Lambda expression to disable dates/days of the week in the GUI DatePicker
        apptDatePicker.setDayCellFactory(picker -> 
        {
            return new DateCell()
            {
                @Override
                public void updateItem(LocalDate date, boolean empty)
                {
                    super.updateItem(date, empty);
                    setDisable(
                        empty || 
                        date.getDayOfWeek() == DayOfWeek.MONDAY ||
                        date.isBefore(LocalDate.now()) || 
                        (date.getMonth() == Month.JANUARY && date.getDayOfMonth() == 1) || 
                        (date.getMonth() == Month.JULY && date.getDayOfMonth() == 4));
                    if(date.getDayOfWeek() == DayOfWeek.MONDAY || 
                            date.isBefore(LocalDate.now()) || 
                            (date.getMonth() == Month.JANUARY && date.getDayOfMonth() == 1) || 
                            (date.getMonth() == Month.JULY && date.getDayOfMonth() == 4))
                    {
                        setStyle("-fx-background-color: #ffc4c4;");
                    }
                }
            };
        });
    }
}
