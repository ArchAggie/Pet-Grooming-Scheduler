
package views;

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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import models.Pet;
import models.PetDB;

/**
 * Brian Parsons
 * Student ID: 001008912
 * C195 - Software II - Advanced Java Concepts
 * Performance Assessment
 */

public class ViewPetsController implements Initializable
{
    @FXML
    private AnchorPane viewPetsAnchorPane;
    @FXML
    private Label viewPetsLabel;
    @FXML
    private TableView<Pet> petTable;
    @FXML
    private TableColumn<Pet, Integer> colPetID;
    @FXML
    private TableColumn<Pet, String> colPetName;
    @FXML
    private TableColumn<Pet, String> colPetType;
    @FXML
    private TableColumn<Pet, String> colPetOwner;
    @FXML
    private TableColumn<Pet, String> colPetAddress;
    @FXML
    private TableColumn<Pet, String> colPetCity;
    @FXML
    private TableColumn<Pet, String> colPetZip;
    @FXML
    private TableColumn<Pet, String> colPetPhone;
    @FXML
    private TableColumn<Pet, String> colPetBirthDate;
    @FXML
    private Button addButton;
    @FXML
    private Button modifyButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button returnButton;
    
    private Pet selectedPet;
    
    public Pet handleSelectPet(MouseEvent event)
    {
        selectedPet = petTable.getSelectionModel().getSelectedItem();
        int id = selectedPet.getPetID();
        return selectedPet;
    }
    
    @FXML
    public void handleAddPetButton(ActionEvent event)
    {
        try
        {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/views/AddPet.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Add Pet");
            stage.show();
            Stage viewPetsStage = (Stage) addButton.getScene().getWindow();
            viewPetsStage.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void handleModifyPetButton(ActionEvent event)
    {
        selectedPet = petTable.getSelectionModel().getSelectedItem();
        ModifyPetController.selectedPet = selectedPet;
        if(selectedPet != null)
        {
            try
            {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/views/ModifyPet.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Modify Pet");
                stage.show();
                Stage viewPetsStage = (Stage) modifyButton.getScene().getWindow();
                viewPetsStage.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            return;
        }
    }

    @FXML
    public void handleDeletePetButton(ActionEvent event)
    {
        if (petTable.getSelectionModel().getSelectedItem() != null)
        {
            selectedPet = petTable.getSelectionModel().getSelectedItem();
        }
        else
        {
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete");
        alert.setHeaderText("Delete Pet Records from Database");
        alert.setContentText("Delete Pet: " + selectedPet.getPetName() + " ?");
        // Lambda to efficiently handle the alert to delete a pet
        alert.showAndWait().ifPresent((response -> 
        {
            if (response == ButtonType.OK)
            {
                PetDB.deletePet(selectedPet.getPetID());
                petTable.setItems(PetDB.getAllPets());
            }
        }));
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
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        petTable.refresh();
        petTable.setItems(PetDB.getAllPets());
        colPetID.setCellValueFactory(new PropertyValueFactory<>("PetID"));
        colPetName.setCellValueFactory(new PropertyValueFactory<>("PetName"));
        colPetType.setCellValueFactory(new PropertyValueFactory<>("PetType"));
        colPetOwner.setCellValueFactory(new PropertyValueFactory<>("PetOwnerName"));
        colPetAddress.setCellValueFactory(new PropertyValueFactory<>("PetAddress"));
        colPetCity.setCellValueFactory(new PropertyValueFactory<>("PetCity"));
        colPetZip.setCellValueFactory(new PropertyValueFactory<>("PetZip"));
        colPetPhone.setCellValueFactory(new PropertyValueFactory<>("PetPhone"));
        colPetBirthDate.setCellValueFactory(new PropertyValueFactory<>("PetBirthdate"));
    }
}
