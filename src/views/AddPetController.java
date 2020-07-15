
package views;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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

import models.PetDB;

/**
 * Brian Parsons
 * Student ID: 001008912
 * C195 - Software II - Advanced Java Concepts
 * Performance Assessment
 */

public class AddPetController implements Initializable
{
    @FXML
    private AnchorPane addPetAnchorPane;
    @FXML
    private Label addPetLabel;
    @FXML
    private Label petNameLabel;
    @FXML
    private TextField petNameTextField;
    @FXML
    private Label petTypeLabel;
    @FXML
    private ComboBox<String> petTypeComboBox;
    @FXML
    private Label petBirthdateLabel;
    @FXML
    private DatePicker petBirthdateDatePicker;
    @FXML
    private Label petOwnerNameLabel;
    @FXML
    private TextField petOwnerNameTextField;
    @FXML
    private Label petAddressLabel;
    @FXML
    private TextField petAddressTextField;
    @FXML
    private Label petCityLabel;
    @FXML
    private ComboBox<String> petCityComboBox;
    @FXML
    private Label petZipCodeLabel;
    @FXML
    private TextField petZipCodeTextField;
    @FXML
    private Label petCountryLabel;
    @FXML
    private Label petCountryText;
    @FXML
    private Label petPhoneLabel;
    @FXML
    private TextField petPhoneTextField;
    @FXML
    private Button addPetButton;
    @FXML
    private Button cancelButton;
    
    private final ObservableList<String> cities = FXCollections.observableArrayList("New York City", "Mexico City");
    
    private final ObservableList<String> petTypes = FXCollections.observableArrayList("dog", "cat", "hamster");
    
    private final ObservableList<String> errors = FXCollections.observableArrayList();
    
    @FXML
    public void setCountry()
    {
        String chosenCity = petCityComboBox.getSelectionModel().getSelectedItem();
        switch (chosenCity)
        {
            case "New York City":
                petCountryText.setText("USA");
                break;
            case "Mexico City":
                petCountryText.setText("Mexico");
                break;
            default:
                petCountryText.setText("No city currently chosen");
                break;
        }
    }
    
    @FXML
    public boolean handleAddPet(ActionEvent event)
    {
        errors.clear();
        String petName = petNameTextField.getText();
        int petTypeID = petTypeComboBox.getSelectionModel().getSelectedIndex() + 1;
        String petStreetAddress = petAddressTextField.getText();
        int petCityID = petCityComboBox.getSelectionModel().getSelectedIndex() + 1;
        String ownerName = petOwnerNameTextField.getText();
        String petZip = petZipCodeTextField.getText();
        String petPhone = petPhoneTextField.getText();
        LocalDate petBDay = petBirthdateDatePicker.getValue();
        
        if (!validateName(petName) || !validatePetType(petTypeID) || !validatePetBDay(petBDay) || !validateOwner(ownerName) || 
                !validateAddress(petStreetAddress) || !validateCity(petCityID) || !validateZipCode(petZip) || !validatePhone(petPhone))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Add Pet Error");
            alert.setContentText(displayErrors());
            alert.show();
            return false;
        }
        else
        {
            System.out.println("The pet you are trying to add has been successully validated");
            boolean b = PetDB.addPet(petName, petTypeID, petBDay, ownerName, petStreetAddress, petCityID, petZip, petPhone);
            if(b)
            {
                try
                {
                    FXMLLoader loader = new FXMLLoader(AddPetController.class.getResource("/views/ViewPets.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                    Stage addPetStage = (Stage) addPetButton.getScene().getWindow();
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
    public void handleCancelAddPet(ActionEvent event)
    {
        try
        {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/views/ViewPets.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Add Pet");
            stage.show();
            Stage addPetStage = (Stage) cancelButton.getScene().getWindow();
            addPetStage.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public boolean validateName(String name)
    {
        if(name.isEmpty())
        {
            errors.add("A Pet Name must be entered.");
            return false;
        }
        else if(!name.matches("[a-zA-Z ']*$"))
        {
            errors.add("The Pet Name may only contain uppercase and lowercase letters.");
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public boolean validatePetType(int index)
    {
        if(index < 0)
        {
            errors.add("A Pet Type must be selected.");
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public boolean validatePetBDay(LocalDate ld)
    {
        if(ld.isAfter(LocalDate.now()))
        {
            errors.add("The Pet Birthday can't be a future date.");
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public boolean validateOwner(String name)
    {
        if(name.isEmpty())
        {
            errors.add("An Owner Name must be entered.");
            return false;
        }
        else if(!name.matches("[a-zA-Z ]*$"))
        {
            errors.add("The Owner Name may only contain uppercase and lowercase letters.");
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public boolean validateAddress(String address)
    {
        if(address.isEmpty())
        {
            errors.add("A Pet Address must be entered.");
            return false;
        }
        else if(!address.matches("[a-zA-Z0-9 .]*$"))
        {
            errors.add("The Pet Address may only contain alphanumeric characters.");
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public boolean validateCity(int index)
    {
        if(index < 0)
        {
            errors.add("A City must be selected.");
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public boolean validateZipCode(String zip)
    {
        if(zip.isEmpty())
        {
            errors.add("A zipcode must be entered.");
            return false;
        }
        else if(!zip.matches("[0-9]{5}"))
        {
            errors.add("The zipcode may only contain numbers between 0 and 9.");
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public boolean validatePhone(String phone)
    {
        if(phone.isEmpty())
        {
            errors.add("A phone number must be entered.");
            return false;
        }
        else if(!phone.matches("[0-9]{3}-[0-9]{3}-[0-9]{4}"))
        {
            errors.add("The phone number may only contain numbers between 0 and 9 and hyphens (-),"
                    + " and must be written in the following format:  xxx-xxx-xxxx");
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
        petTypeComboBox.setItems(petTypes);
        petTypeComboBox.getSelectionModel().selectFirst();
        
        petCityComboBox.setItems(cities);
        petCityComboBox.getSelectionModel().selectFirst();
        
        // Lambda expression to disable dates after the current date in the GUI DatePicker
        petBirthdateDatePicker.setDayCellFactory(picker -> 
        {
            return new DateCell()
            {
                @Override
                public void updateItem(LocalDate date, boolean empty)
                {
                    super.updateItem(date, empty);
                    setDisable(
                        empty || 
                        date.isAfter(LocalDate.now()));
                    if(date.isAfter(LocalDate.now()))
                    {
                        setStyle("-fx-background-color: #ffc4c4;");
                    }
                }
            };
        });
    }
}
