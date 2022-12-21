package ed.wgu.zamzow.software_ii.controllers;

import ed.wgu.zamzow.software_ii.database.DBQuery;
import ed.wgu.zamzow.software_ii.database.DBWrite;
import ed.wgu.zamzow.software_ii.objects.Appointment;
import ed.wgu.zamzow.software_ii.utils.Vars;
import ed.wgu.zamzow.software_ii.utils.appUtils;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import static ed.wgu.zamzow.software_ii.utils.Vars.futureAppointments;

public class ShowAppointmentsViewController {

    public Button btnDel, btnMod;
    public TableView<Appointment> tblWeeklyAppointments, tblMonthlyAppointments;
    public TableColumn colTitle, colMTitle;
    public TableColumn colDesc, colMDesc;
    public TableColumn colLoc, colMLoc;
    public TableColumn colType, colMType;
    public TableColumn colStart, colMStart;
    public TableColumn colEnd, colMEnd;
    public TableColumn colCust, colMCust;
    public TableColumn colUser, colMUser;
    public TableColumn colContact, colMContact;
    public TableColumn colID, colMID;
    private ObservableList<Appointment> monthlyAppointments;
    private ObservableList<Appointment> weeklyAppointments;
    private DBQuery dbQuery;

    public void initialize() {
        dbQuery = new DBQuery();
        try {

            weeklyAppointments =  FXCollections.observableList(dbQuery.getWeeklyAppointments());
            monthlyAppointments = FXCollections.observableList(dbQuery.getMonthlyAppointments());
            LoadData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Vars.lang = appUtils.getLanguange();
    }

    private void LoadData() {
        colID.setCellValueFactory(new PropertyValueFactory("appointment_id"));
        colTitle.setCellValueFactory(new PropertyValueFactory("title"));
        colDesc.setCellValueFactory(new PropertyValueFactory("desc"));
        colLoc.setCellValueFactory(new PropertyValueFactory("loc"));
        colType.setCellValueFactory(new PropertyValueFactory("type"));
        colStart.setCellValueFactory(new PropertyValueFactory("startDate"));
        colEnd.setCellValueFactory(new PropertyValueFactory("endDate"));
        colCust.setCellValueFactory(new PropertyValueFactory("cust_name"));
        colContact.setCellValueFactory(new PropertyValueFactory("cont_name"));
        colUser.setCellValueFactory(new PropertyValueFactory("user_name"));

        colMID.setCellValueFactory(new PropertyValueFactory("appointment_id"));
        colMTitle.setCellValueFactory(new PropertyValueFactory("title"));
        colMDesc.setCellValueFactory(new PropertyValueFactory("desc"));
        colMLoc.setCellValueFactory(new PropertyValueFactory("loc"));
        colMType.setCellValueFactory(new PropertyValueFactory("type"));
        colMStart.setCellValueFactory(new PropertyValueFactory("startDate"));
        colMEnd.setCellValueFactory(new PropertyValueFactory("endDate"));
        colMCust.setCellValueFactory(new PropertyValueFactory("cust_name"));
        colMContact.setCellValueFactory(new PropertyValueFactory("cont_name"));
        colMUser.setCellValueFactory(new PropertyValueFactory("user_name"));

        tblWeeklyAppointments.setItems(weeklyAppointments);
        tblMonthlyAppointments.setItems(monthlyAppointments);
        weeklyAppointments.addListener((ListChangeListener<? super Appointment>) change -> tblWeeklyAppointments.refresh());
        monthlyAppointments.addListener((ListChangeListener<? super Appointment>) change -> tblMonthlyAppointments.refresh());

    }

    @FXML
    private void ModifyAppointment() {
        Appointment appointment = tblWeeklyAppointments.getSelectionModel().getSelectedItem();
        URL fxmlLocation = getClass().getClassLoader().getResource("mod_appointment-view.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Parent mainRoot = null;
        try {
            mainRoot = loader.load();
            ModAppointmentViewController controller = loader.getController();
            controller.setAppointment(appointment, tblWeeklyAppointments.getSelectionModel().getSelectedIndex());

            Stage stage = new Stage();

            stage.setScene(new Scene(mainRoot));
            stage.show();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void DeleteAppointment() {
        Appointment appointment = tblWeeklyAppointments.getSelectionModel().getSelectedItem();

        Alert confirm = new Alert(Alert.AlertType.WARNING,"Are you sure you want to delete " + appointment.getTitle(), ButtonType.YES, ButtonType.NO);
        confirm.setTitle("Delete?");
        confirm.showAndWait();

        if (confirm.getResult() == ButtonType.YES) {
            DBWrite dbWrite = new DBWrite();
            dbWrite.DeleteAppointment(appointment);
            futureAppointments.remove(appointment);
        }
    }
}