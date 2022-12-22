package ed.wgu.zamzow.software_ii.controllers;

import ed.wgu.zamzow.software_ii.database.DBQuery;
import ed.wgu.zamzow.software_ii.utils.Logging;
import ed.wgu.zamzow.software_ii.utils.Vars;
import ed.wgu.zamzow.software_ii.utils.appUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;
import java.time.*;
import java.util.ResourceBundle;

/**
 * Controller for the login form that allows users to login to the application
 *
 * @author Bret Zamzow
 */

public class LoginViewController {
    public PasswordField txtPass;
    public Button btnSignIn;
    @FXML
    private Label lblZone;
    @FXML
    private TextField txtUName;

    /**
     * Method to initialize the form and setup the main parts of the form
     */
    public void initialize() {
        ZoneId zoneId = ZoneId.systemDefault();
        lblZone.setText(zoneId.toString());
        Vars.lang = appUtils.getLanguange();
    }

    /**
     * Method to sign in and check the login information against the database. If the sign in is successful, this form will open the main view
     */
    @FXML
    protected void signIn() {
        if (!txtPass.getText().isEmpty() || !txtPass.getText().isBlank()) {
            if(!txtUName.getText().isEmpty() || !txtUName.getText().isBlank()) {
                DBQuery dbQuery = new DBQuery();
                try {
                    if (dbQuery.signIn(txtUName.getText(), txtPass.getText())) {
                        Logging.AddToLog(new Timestamp(System.currentTimeMillis()).toString(), "Successful Login", "Logged in successfully" );
                        ResourceBundle bundle = ResourceBundle.getBundle("UIResources");
                        URL fxmlLocation = getClass().getClassLoader().getResource("main-view.fxml");
                        Parent mainRoot = null;
                        try {
                            mainRoot = FXMLLoader.load(Objects.requireNonNull(fxmlLocation), bundle);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        Stage stage = new Stage();
                        stage.setScene(new Scene(mainRoot));
                        stage.show();

                        Stage thisStage = (Stage) btnSignIn.getScene().getWindow();
                        thisStage.close();
                    }
                } catch (SQLException e) {
                    Logging.AddToLog(new Timestamp(System.currentTimeMillis()).toString(), "Unsuccessful Login", e.getMessage() );

                    throw new RuntimeException(e);
                }
            }
        }
    }
}