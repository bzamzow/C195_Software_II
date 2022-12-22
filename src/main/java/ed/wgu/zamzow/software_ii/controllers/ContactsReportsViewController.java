package ed.wgu.zamzow.software_ii.controllers;

import ed.wgu.zamzow.software_ii.database.DBQuery;
import ed.wgu.zamzow.software_ii.objects.Appointment;
import ed.wgu.zamzow.software_ii.objects.Contact;
import ed.wgu.zamzow.software_ii.objects.CustomerReport;
import ed.wgu.zamzow.software_ii.utils.Vars;
import ed.wgu.zamzow.software_ii.utils.appUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.ArrayList;

public class ContactsReportsViewController {

    public TableView<Appointment> tblContactReport;
    public TableColumn colCustomer;
    public TableColumn colStart;
    public TableColumn colType;
    public TableColumn colID;

    public TableColumn colTitle;
    public TableColumn colDesc;
    public TableColumn colEnd;
    public ChoiceBox cmbContacts;

    private ObservableList<Appointment> reporting;
    private DBQuery dbQuery;
    private ArrayList<Contact> contacts;

    public void initialize() {
        dbQuery = new DBQuery();
        try {
            contacts = dbQuery.getContacts();
            for (Contact contact : contacts) {
                cmbContacts.getItems().add(contact.getContact_name());
            }
            cmbContacts.getSelectionModel().select(0);
            reporting =  FXCollections.observableList(dbQuery.getSchedulePerContact(contacts.get(0).getContact_name()));

            cmbContacts.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                 @Override
                 public void changed(ObservableValue observableValue, Object o, Object t1) {
                     try {
                         reporting =  FXCollections.observableList(dbQuery.getSchedulePerContact(cmbContacts.getSelectionModel().getSelectedItem().toString()));
                         LoadData();
                     } catch (SQLException throwables) {
                         throwables.printStackTrace();
                     }
                 }
             });
            LoadData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Vars.lang = appUtils.getLanguange();
    }

    private void LoadData() {
        colCustomer.setCellValueFactory(new PropertyValueFactory("cust_id"));
        colType.setCellValueFactory(new PropertyValueFactory("type"));
        colStart.setCellValueFactory(new PropertyValueFactory("startDate"));
        colID.setCellValueFactory(new PropertyValueFactory("appointment_id"));
        colDesc.setCellValueFactory(new PropertyValueFactory("desc"));
        colEnd.setCellValueFactory(new PropertyValueFactory("endDate"));
        colTitle.setCellValueFactory(new PropertyValueFactory("title"));

        tblContactReport.setItems(reporting);

        reporting.addListener((ListChangeListener<Appointment>) change -> {
        });
    }
}