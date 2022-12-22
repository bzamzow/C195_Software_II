package ed.wgu.zamzow.software_ii;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * This application allows users to create scheduling and view reports
 * for customers as a part of the consulting that is offered by their firm
 * Users can add, modify, and delete customers as well as appointments
 * @version 1.0
 * @author Bret Zamzow
 * JAVADOC location src/main/resources/JAVADOC
 */


public class MainApplication extends Application {

    /**
     * Method to start the login process
     * @param stage
     */
    @Override
    public void start(Stage stage) {
        Locale locale = Locale.getDefault();
        URL fxmlLocation = getClass().getClassLoader().getResource("login-view.fxml");
        Parent mainRoot = null;
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("UIResources", locale);

            mainRoot = FXMLLoader.load(Objects.requireNonNull(fxmlLocation),bundle);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage = new Stage();
        stage.setTitle("Log-in");
        stage.setScene(new Scene(mainRoot));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}