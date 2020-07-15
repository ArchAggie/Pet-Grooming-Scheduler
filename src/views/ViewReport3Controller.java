
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

import models.Groomer;
import utils.DBConnection;
import utils.Logger;

/**
 * Brian Parsons
 * Student ID: 001008912
 * C195 - Software II - Advanced Java Concepts
 * Performance Assessment
 */

public class ViewReport3Controller implements Initializable
{

    @FXML
    private AnchorPane viewReportsAnchorPane;
    @FXML
    private Button returnButton;
    @FXML
    private TableView<Groomer> reportTableSalesByGroomer;
    @FXML
    private TableColumn<Groomer, String> reportThreeColGroomer;
    @FXML
    private TableColumn<Groomer, Double> reportThreeColTotalSales;
    
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
    
    public static ObservableList<Groomer> handleReportSalesByGroomer()
    {
        ObservableList<Groomer> reportThree = FXCollections.observableArrayList();
        System.out.println("Creating Third Report");
        double groomerOneSales;
        double groomerTwoSales;
        
        try
        {
            Statement statementG1 = DBConnection.getConnection().createStatement();
            String queryG1 = "SELECT SUM(service_cost) totalSales FROM (SELECT groomer_id, service_cost FROM appointment WHERE groomer_id = 1) s";
            ResultSet resultsG1 = statementG1.executeQuery(queryG1);
            resultsG1.next();
            groomerOneSales = resultsG1.getDouble("totalSales");
            
            Statement statementG2 = DBConnection.getConnection().createStatement();
            String queryG2 = "SELECT SUM(service_cost) totalSales FROM (SELECT groomer_id, service_cost FROM appointment WHERE groomer_id = 2) s";
            ResultSet resultsG2 = statementG2.executeQuery(queryG2);
            resultsG2.next();
            groomerTwoSales = resultsG2.getDouble("totalSales");
            
            reportThree.addAll(new Groomer(1, groomerOneSales), new Groomer(2, groomerTwoSales));
            return reportThree;
        }
        catch(SQLException e)
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
        Logger.logReport3(LoginController.username);
        reportTableSalesByGroomer.setItems(handleReportSalesByGroomer());
        reportThreeColGroomer.setCellValueFactory(new PropertyValueFactory<>("GroomerIDReport"));
        reportThreeColTotalSales.setCellValueFactory(new PropertyValueFactory<>("GroomerSalesTotalReport"));
    }
}
