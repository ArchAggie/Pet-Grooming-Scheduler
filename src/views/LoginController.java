
package views;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import models.GroomerDB;

/**
 * Brian Parsons
 * Student ID: 001008912
 * C195 - Software II - Advanced Java Concepts
 * Performance Assessment
 */

public class LoginController implements Initializable
{
    ResourceBundle rb;
    Locale groomerLocale;
    static final Logger groomerLog = Logger.getLogger("report_tracking.txt");
    
    @FXML
    private AnchorPane loginAnchorPane;
    @FXML
    private Label loginTitleLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private Label passwordLabel;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Button loginButton;
    
    public static String username;
    
    private String errorTitle;
    private String errorHeader;
    private String errorMessage;
    
    @FXML
    public void handleLogin(ActionEvent event) throws IOException
    {
        username = usernameTextField.getText();
        String password = passwordTextField.getText();
        boolean validUser = GroomerDB.login(username, password);
        
        if(validUser)
        {
            ((Node) (event.getSource())).getScene().getWindow().hide();
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/views/MainMenu.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Main Menu");
            stage.show();
            Stage viewPetsStage = (Stage) loginButton.getScene().getWindow();
            viewPetsStage.close();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(errorTitle);
            alert.setHeaderText(errorHeader);
            alert.setContentText(errorMessage);
            alert.showAndWait();
        }
    }
    
//    public static Locale setCurrentLocale()
//    {
//        return Locale.getDefault();
//    }
//    
//    Locale[] supportedLocales = 
//    {
//        Locale 
//        Locale.ENGLISH
//    };

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        Locale locale = Locale.getDefault();
        rb = ResourceBundle.getBundle("lang/login", locale);
        errorMessage = rb.getString("errortext");
        usernameLabel.setText(rb.getString("username"));
        passwordLabel.setText(rb.getString("password"));
        loginButton.setText(rb.getString("login"));
        
        errorHeader = rb.getString("errorheader");
        errorTitle = rb.getString("errortitle");
        errorMessage = rb.getString("errortext");
    }
}
