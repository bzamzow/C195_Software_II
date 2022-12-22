package ed.wgu.zamzow.software_ii.controllers;

import ed.wgu.zamzow.software_ii.database.DBQuery;
import ed.wgu.zamzow.software_ii.database.DBWrite;
import ed.wgu.zamzow.software_ii.objects.Appointment;
import ed.wgu.zamzow.software_ii.objects.Contact;
import ed.wgu.zamzow.software_ii.objects.CustomerReport;
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
import java.util.ArrayList;

import static ed.wgu.zamzow.software_ii.utils.Vars.futureAppointments;

public class CustomerReportsViewController {

    public TableView<CustomerReport> tblCustomerReport;
    public TableColumn colCustomer;
    public TableColumn colType;
    public TableColumn colCount;

    private ObservableList<CustomerReport> reporting;
    private DBQuery dbQuery;

    public void initialize() {
        dbQuery = new DBQuery();
        try {
            reporting =  FXCollections.observableList(dbQuery.getAppointmentsCustomerMonth());
            LoadData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Vars.lang = appUtils.getLanguange();
    }

    private void LoadData() {
        colCustomer.setCellValueFactory(new PropertyValueFactory("customerName"));
        colType.setCellValueFactory(new PropertyValueFactory("appointmentType"));
        colCount.setCellValueFactory(new PropertyValueFactory("count"));
        tblCustomerReport.setItems(reporting);
    }
}