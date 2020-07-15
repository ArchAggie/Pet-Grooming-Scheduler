
package views;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import models.Appointment;
import models.AppointmentDB;

/**
 * Brian Parsons
 * Student ID: 001008912
 * C195 - Software II - Advanced Java Concepts
 * Performance Assessment
 */

public class ViewAppointmentsController implements Initializable
{
    @FXML
    private Label viewAppointmentsLabel;
    @FXML
    private Label chooseMonthLabel;
    @FXML
    private AnchorPane viewAppointmentsAnchorPane;
    @FXML
    private ComboBox<String> monthComboBox;
    @FXML
    private TableView<Appointment> appointmentTable;
    @FXML
    private TableColumn<Appointment, Integer> colApptID;
    @FXML
    private TableColumn<Appointment, String> colPetName;
    @FXML
    private TableColumn<Appointment, String> colPetType;
    @FXML
    private TableColumn<Appointment, String> colGroomerName;
    @FXML
    private TableColumn<Appointment, String> colApptDescription;
    @FXML
    private TableColumn<Appointment, String> colApptDate;
    @FXML
    private TableColumn<Appointment, String> colApptStart;
    @FXML
    private TableColumn<Appointment, String> colApptType;
    @FXML
    private TableColumn<Appointment, String> colApptCost;
    @FXML
    private Button addApptButton;
    @FXML
    private Button modifyApptButton;
    @FXML
    private Button deleteApptButton;
    @FXML
    private Button returnButton;
    
    private Appointment selectedAppointment;
    
    private final ObservableList<String> allMonths = FXCollections.observableArrayList("January", "February", 
            "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
    
    private static String currentMonth = "January";
    
    @FXML
    public Appointment handleSelectAppointment(MouseEvent event)
    {
        selectedAppointment = appointmentTable.getSelectionModel().getSelectedItem();
        int id = selectedAppointment.getAppointmentID();
        return selectedAppointment;
    }
    
    @FXML
    public void handleAddAppointmentButton(ActionEvent event)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(AddPetController.class.getResource("/views/AddAppointment.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Add Appointment");
            stage.setScene(scene);
            stage.show();
            Stage viewApptsStage = (Stage) addApptButton.getScene().getWindow();
            viewApptsStage.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void handleModifyAppointmentButton(ActionEvent event)
    {
        selectedAppointment = appointmentTable.getSelectionModel().getSelectedItem();
        ModifyAppointmentController.selectedAppointment = selectedAppointment;
        if(selectedAppointment != null)
        {
            try
            {
                FXMLLoader loader = new FXMLLoader(AddPetController.class.getResource("/views/ModifyAppointment.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setTitle("Modify Appointment");
                stage.setScene(scene);
                stage.show();
                Stage viewApptsStage = (Stage) modifyApptButton.getScene().getWindow();
                viewApptsStage.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    @FXML
    public void handleDeleteAppointmentButton(ActionEvent event)
    {
        if (appointmentTable.getSelectionModel().getSelectedItem() != null)
        {
            selectedAppointment = appointmentTable.getSelectionModel().getSelectedItem();
        }
        else
        {
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete");
        alert.setHeaderText("Delete Appointment from Database");
        alert.setContentText("Delete Appointment: " + selectedAppointment.getAppointmentDescription() + " ?");
        
        // Lambda used to efficiently handle the alert for deleting an appointment
        alert.showAndWait().ifPresent(response -> 
        {
            if (response == ButtonType.OK)
            {
                AppointmentDB.deleteAppointment(selectedAppointment.getAppointmentID());
                handleTableView();
            }
        });
    }
    
    @FXML
    public void handleReturnToMenu(ActionEvent event)
    {
        try
        {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/views/MainMenu.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Main Menu");
            stage.show();
            Stage viewPetsStage = (Stage) returnButton.getScene().getWindow();
            viewPetsStage.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public void handleTableView()
    {
        currentMonth = monthComboBox.getSelectionModel().getSelectedItem();
        switch (monthComboBox.getSelectionModel().getSelectedItem())
        {
            case "January":
                appointmentTable.setItems(AppointmentDB.getJanAppointments());
                break;
            case "February":
                appointmentTable.setItems(AppointmentDB.getFebAppointments());
                break;
            case "March":
                appointmentTable.setItems(AppointmentDB.getMarAppointments());
                break;
            case "April":
                appointmentTable.setItems(AppointmentDB.getAprAppointments());
                break;
            case "May":
                appointmentTable.setItems(AppointmentDB.getMayAppointments());
                break;
            case "June":
                appointmentTable.setItems(AppointmentDB.getJunAppointments());
                break;
            case "July":
                appointmentTable.setItems(AppointmentDB.getJulAppointments());
                break;
            case "August":
                appointmentTable.setItems(AppointmentDB.getAugAppointments());
                break;
            case "September":
                appointmentTable.setItems(AppointmentDB.getSepAppointments());
                break;
            case "October":
                appointmentTable.setItems(AppointmentDB.getOctAppointments());
                break;
            case "November":
                appointmentTable.setItems(AppointmentDB.getNovAppointments());
                break;
            case "December":
                appointmentTable.setItems(AppointmentDB.getDecAppointments());
                break;
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
        monthComboBox.setItems(allMonths);
        monthComboBox.setValue(currentMonth);
        
        handleTableView();
        
        colApptID.setCellValueFactory(new PropertyValueFactory<>("AppointmentID"));
        colPetName.setCellValueFactory(new PropertyValueFactory<>("AppointmentPetName"));
        colPetType.setCellValueFactory(new PropertyValueFactory<>("AppointmentPetType"));
//        colGroomerName.setCellValueFactory(new PropertyValueFactory<>("AppointmentGroomerName"));
        colApptDescription.setCellValueFactory(new PropertyValueFactory<>("AppointmentDescription"));
        colApptDate.setCellValueFactory(new PropertyValueFactory<>("AppointmentDate"));
        colApptStart.setCellValueFactory(new PropertyValueFactory<>("AppointmentStart"));
        colApptType.setCellValueFactory(new PropertyValueFactory<>("AppointmentType"));
        colApptCost.setCellValueFactory(new PropertyValueFactory<>("AppointmentCost"));
        
//        colApptID.setCellValueFactory(cellData -> 
//        {
//            return cellData.getValue().getAppointmentIDProperty().asObject();
//        });
//        colPetName.setCellValueFactory(cellData -> 
//        {
//            return cellData.getValue().getAppointmentPetNameProperty();
//        });
//        colPetType.setCellValueFactory(cellData -> 
//        {
//            return cellData.getValue().getAppointmentPetTypeProperty();
//        });

        // Lambda expression to populate the Groomer column in the Appointment Table
        colGroomerName.setCellValueFactory(cellData -> 
        {
            return cellData.getValue().getAppointmentGroomerNameProperty();
        });
//        colApptDescription.setCellValueFactory(cellData -> 
//        {
//            return cellData.getValue().getAppointmentDescriptionProperty();
//        });
//        colApptDate.setCellValueFactory(cellData -> 
//        {
//            return cellData.getValue().getApptStartProperty();
//        });
//        colApptStart.setCellValueFactory(cellData -> 
//        {
//            return cellData.getValue().getApptStartProperty();
//        });
//        colApptType.setCellValueFactory(cellData -> 
//        {
//            return cellData.getValue().getAppointmentTypeProperty();
//        });
//        colApptCost.setCellValueFactory(cellData -> 
//        {
//            return cellData.getValue().getAppointmentCostProperty().asString();
//        });
    }
}
