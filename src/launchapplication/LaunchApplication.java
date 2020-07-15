
package launchapplication;

import java.util.Locale;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import utils.DBConnection;

/**
 * Brian Parsons
 * Student ID: 001008912
 * C195 - Software II - Advanced Java Concepts
 * Performance Assessment
 */

public class LaunchApplication extends Application
{
    private static Stage stage;
    
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        LaunchApplication.stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("User Login");
        stage.show();
    }
    
    public static Stage getStage()
    {
        return stage;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Locale locale = Locale.getDefault();
        Locale.setDefault(locale);
        DBConnection.startConnection();
        launch(args);
        DBConnection.endConnection();
    }
}