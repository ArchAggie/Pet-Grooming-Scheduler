
package views;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import models.Appointment;
import utils.DBConnection;
import utils.Logger;

/**
 * Brian Parsons
 * Student ID: 001008912
 * C195 - Software II - Advanced Java Concepts
 * Performance Assessment
 */

public class ViewReport1Controller implements Initializable
{
    @FXML
    private AnchorPane viewReportsAnchorPane;
    @FXML
    private Button returnButton;
    @FXML
    private TableView<Appointment> reportTableApptByType;
    @FXML
    private TableColumn<String, String> reportOneColApptType;
    @FXML
    private TableColumn<Appointment, Integer> reportOneColCount;
    
    
    
    public static ObservableList<Appointment> handleReportApptByType()
    {
        ObservableList<Appointment> reportOne = FXCollections.observableArrayList();
        
        System.out.println("Creating First Report");
        
        try
        {
            Statement statement = DBConnection.getConnection().createStatement();
            String queryOne = "SELECT servicetype_id, COUNT(*) as 'Count' FROM appointment "
                    + "GROUP BY servicetype_id ORDER BY servicetype_id";
            ResultSet resultsOne = statement.executeQuery(queryOne);
            
            while(resultsOne.next())
            {
                Appointment report = new Appointment(
                        resultsOne.getInt("servicetype_id"), 
                        resultsOne.getInt("Count"));
                reportOne.add(report);
            }
            
            statement.close();
            return reportOne;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    @FXML
    public void handleReturn(ActionEvent event)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(AddPetController.class.getResource("/views/MainMenu.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Main Menu");
            stage.setScene(scene);
            stage.show();
            Stage viewMainMenuStage = (Stage) returnButton.getScene().getWindow();
            viewMainMenuStage.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
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
        Logger.logReport1(LoginController.username);
        reportTableApptByType.setItems(handleReportApptByType());
        reportOneColApptType.setCellValueFactory(new PropertyValueFactory<>("AppointmentTypeIDReport"));
        reportOneColCount.setCellValueFactory(new PropertyValueFactory<>("AppointmentCountReport"));
    }
}
