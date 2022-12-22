package ed.wgu.zamzow.software_ii.controllers;

import ed.wgu.zamzow.software_ii.database.DBQuery;
import ed.wgu.zamzow.software_ii.objects.Appointment;
import ed.wgu.zamzow.software_ii.utils.appUtils;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

import static ed.wgu.zamzow.software_ii.utils.Vars.appointments;
import static ed.wgu.zamzow.software_ii.utils.Vars.futureAppointments;

/**
 * Controller for the main screen of the application. This is the overall main portion that allows the user to manipulate data and add information
 *
 * @author Bret Zamzow
 */

public class MainController {
    public MenuItem mFClose;
    public MenuItem mCAdd;
    public MenuItem mCModify;
    public MenuItem mCDelete;
    public MenuItem mAAdd;
    public MenuItem mAModify;
    public MenuItem mADelete;
    public MenuItem mAView;
    public TableView<Appointment> tblUpcoming;
    public TableColumn colTitle, colDesc, colLoc, colType, colStart, colEnd, colCust, colUser, colContact;
    public MenuItem rprtCustomer;
    public MenuItem rprtContact;
    public MenuItem rprtUser;

    /**
     * Method to initialize the form and setup the main parts of the form
     */
    public void initialize() {
        DBQuery dbQuery = new DBQuery();
        try {
            futureAppointments = FXCollections.observableList(dbQuery.getFutureAppointments());

            appointments = FXCollections.observableList(dbQuery.getAppointments());

            colTitle.setCellValueFactory(new PropertyValueFactory("title"));
            colDesc.setCellValueFactory(new PropertyValueFactory("desc"));
            colLoc.setCellValueFactory(new PropertyValueFactory("loc"));
            colType.setCellValueFactory(new PropertyValueFactory("type"));
            colStart.setCellValueFactory(new PropertyValueFactory("startDate"));
            colEnd.setCellValueFactory(new PropertyValueFactory("endDate"));
            colCust.setCellValueFactory(new PropertyValueFactory("cust_name"));
            colContact.setCellValueFactory(new PropertyValueFactory("cont_name"));
            colUser.setCellValueFactory(new PropertyValueFactory("user_name"));


            tblUpcoming.setItems(futureAppointments);

            futureAppointments.addListener((ListChangeListener<Appointment>) change -> {
            });

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Check15Mins();
    }

    /**
     * Method to close the application
     */
    @FXML
    public void close() {
        Platform.exit();
    }

    /**
     * Method to see if any appointments are coming up within 15 minutes and alert the user if they are
     */
    private void Check15Mins() {
        AtomicBoolean appointmentSoon = new AtomicBoolean(false);
        futureAppointments.forEach( (n) -> {
            if (appUtils.isAppointmentStartingSoon(n)) {
                appointmentSoon.set(true);
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Appoint Starting");
                alert.setContentText(n.getTitle() + " is starting in less than 15 minutes");
                alert.show();
            }
        });
        if (!appointmentSoon.get()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Appointments");
            alert.setContentText("No appointments starting soon");
            alert.show();
        }
    }

    /**
     * Method to open the add customers form
     */
    @FXML
    public void calAdd() {
        ResourceBundle bundle = ResourceBundle.getBundle("UIResources");
        URL fxmlLocation = getClass().getClassLoader().getResource("add_customer-view.fxml");
        Parent mainRoot = null;
        try {
            mainRoot = FXMLLoader.load(Objects.requireNonNull(fxmlLocation), bundle);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(mainRoot));
        stage.show();
    }

    /**
     * Method to open the show customers view form
     */
    @FXML
    public void calMod() {
        ResourceBundle bundle = ResourceBundle.getBundle("UIResources");
        URL fxmlLocation = getClass().getClassLoader().getResource("show_customers-view.fxml");
        Parent mainRoot = null;
        try {
            mainRoot = FXMLLoader.load(Objects.requireNonNull(fxmlLocation), bundle);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(mainRoot));
        stage.show();
    }

    /**
     * Method open the add appointment view
     */
    @FXML
    public void aAdd() {
        ResourceBundle bundle = ResourceBundle.getBundle("UIResources");
        URL fxmlLocation = getClass().getClassLoader().getResource("add_appointment-view.fxml");
        Parent mainRoot = null;
        try {
            mainRoot = FXMLLoader.load(Objects.requireNonNull(fxmlLocation), bundle);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(mainRoot));
        stage.show();
    }

    /**
     * Method to open the modify or show appointments views depending on if any appointments are selected
     */
    @FXML
    public void aMod() {
        ResourceBundle bundle = ResourceBundle.getBundle("UIResources");
        URL fxmlLocation;

        if (tblUpcoming.getSelectionModel().getSelectedItem() != null) {
            fxmlLocation=getClass().getClassLoader().getResource("mod_appointment-view.fxml");

        } else {
            fxmlLocation=getClass().getClassLoader().getResource("show_appointments-view.fxml");
        }
        Parent mainRoot = null;
        try {
            mainRoot = FXMLLoader.load(Objects.requireNonNull(fxmlLocation), bundle);
            if (tblUpcoming.getSelectionModel().getSelectedItem() != null) {
                FXMLLoader loader = new FXMLLoader(fxmlLocation);
                mainRoot = loader.load();
                ModAppointmentViewController controller = loader.getController();
                controller.setAppointment(tblUpcoming.getSelectionModel().getSelectedItem(), tblUpcoming.getSelectionModel().getSelectedIndex());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(mainRoot));
        stage.show();
    }

    /**
     * Method to open the show appointments view
     */
    @FXML
    public void aView() {
        ResourceBundle bundle = ResourceBundle.getBundle("UIResources");
        URL fxmlLocation = getClass().getClassLoader().getResource("show_appointments-view.fxml");
        Parent mainRoot = null;
        try {
            mainRoot = FXMLLoader.load(Objects.requireNonNull(fxmlLocation), bundle);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(mainRoot));
        stage.show();
    }

    /**
     * Method to open the log viewer
     */
    @FXML
    public void viewLogs() {
        ResourceBundle bundle = ResourceBundle.getBundle("UIResources");
        URL fxmlLocation = getClass().getClassLoader().getResource("log-view.fxml");
        Parent mainRoot = null;
        try {
            mainRoot = FXMLLoader.load(Objects.requireNonNull(fxmlLocation), bundle);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(mainRoot));
        stage.show();
    }

    /**
     * Method to open the readme viewer
     */
    @FXML
    public void viewReadMe() {
        ResourceBundle bundle = ResourceBundle.getBundle("UIResources");
        URL fxmlLocation = getClass().getClassLoader().getResource("readme-view.fxml");
        Parent mainRoot = null;
        try {
            mainRoot = FXMLLoader.load(Objects.requireNonNull(fxmlLocation), bundle);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(mainRoot));
        stage.show();
    }


    /**
     * Method to open the view report for customers
     */
    @FXML
    public void viewReportCustomer() {
        ResourceBundle bundle = ResourceBundle.getBundle("UIResources");
        URL fxmlLocation = getClass().getClassLoader().getResource("report_customers-view.fxml");
        Parent mainRoot = null;
        try {
            mainRoot = FXMLLoader.load(Objects.requireNonNull(fxmlLocation), bundle);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(mainRoot));
        stage.show();
    }

    /**
     * Method to open the view report for contacts
     */
    @FXML
    public void viewReportContact() {
        ResourceBundle bundle = ResourceBundle.getBundle("UIResources");
        URL fxmlLocation = getClass().getClassLoader().getResource("report_contacts-view.fxml");
        Parent mainRoot = null;
        try {
            mainRoot = FXMLLoader.load(Objects.requireNonNull(fxmlLocation), bundle);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(mainRoot));
        stage.show();
    }

    /**
     * Method to open the view report for users
     */
    @FXML
    public void viewReportUser() {
        ResourceBundle bundle = ResourceBundle.getBundle("UIResources");
        URL fxmlLocation = getClass().getClassLoader().getResource("report_users-view.fxml");
        Parent mainRoot = null;
        try {
            mainRoot = FXMLLoader.load(Objects.requireNonNull(fxmlLocation), bundle);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(mainRoot));
        stage.show();
    }
}