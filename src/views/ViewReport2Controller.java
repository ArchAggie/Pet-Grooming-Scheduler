
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

import models.LocationDisplay;
import utils.DBConnection;
import utils.Logger;

/**
 * Brian Parsons
 * Student ID: 001008912
 * C195 - Software II - Advanced Java Concepts
 * Performance Assessment
 */

public class ViewReport2Controller implements Initializable {

    @FXML
    private AnchorPane viewReportsAnchorPane;
    @FXML
    private Button returnButton;
    @FXML
    private TableView<LocationDisplay> reportTableApptByCity;
    @FXML
    private TableColumn<LocationDisplay, String> reportTwoColCityName;
    @FXML
    private TableColumn<LocationDisplay, Integer> reportTwoColCount;

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
    
    public static ObservableList<LocationDisplay> handleReportApptByCity()
    {
        ObservableList<LocationDisplay> reportTwo = FXCollections.observableArrayList();
        System.out.println("Creating Second Report");
        try
        {
            Statement statementNewYork = DBConnection.getConnection().createStatement();
            String queryNewYork = "SELECT COUNT(*) FROM appointment WHERE service_location = 'New York City'";
            ResultSet resultsNewYork = statementNewYork.executeQuery(queryNewYork);
            resultsNewYork.next();
            int reportNewYork = resultsNewYork.getInt(1);
            statementNewYork.close();
            
            Statement statementMexico = DBConnection.getConnection().createStatement();
            String queryMexico = "SELECT COUNT(*) FROM appointment WHERE service_location = 'Mexico City'";
            ResultSet resultsMexico = statementMexico.executeQuery(queryMexico);
            resultsMexico.next();
            int reportMexico = resultsMexico.getInt(1);
            statementMexico.close();
            
            reportTwo.addAll(new LocationDisplay("New York City", reportNewYork), new LocationDisplay("Mexico City", reportMexico));
            return reportTwo;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        Logger.logReport2(LoginController.username);
        reportTableApptByCity.setItems(handleReportApptByCity());
        reportTwoColCityName.setCellValueFactory(new PropertyValueFactory<>("location"));
        reportTwoColCount.setCellValueFactory(new PropertyValueFactory<>("count"));
    }
}
