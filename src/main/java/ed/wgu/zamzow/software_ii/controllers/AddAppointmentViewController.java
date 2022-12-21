package ed.wgu.zamzow.software_ii.controllers;

import ed.wgu.zamzow.software_ii.database.DBQuery;
import ed.wgu.zamzow.software_ii.database.DBWrite;
import ed.wgu.zamzow.software_ii.objects.*;
import ed.wgu.zamzow.software_ii.utils.Vars;
import ed.wgu.zamzow.software_ii.utils.appUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static ed.wgu.zamzow.software_ii.utils.Vars.*;

public class AddAppointmentViewController {

    public TextField txtTitle;
    public TextField txtDesc;
    public TextField txtLoc;
    public TextField txtType;
    public ChoiceBox cbContact;
    public ChoiceBox cbCust;
    public DatePicker dateStart;
    public DatePicker dateEnd;
    public ChoiceBox cbSH, cbSM, cbEH, cbEM;
    private DBQuery dbQuery;
    private List<Customer> customers;
    private ArrayList<Contact> contacts;

    public void initialize() {
        dbQuery = new DBQuery();

        try {
            contacts = dbQuery.getContacts();
            customers = dbQuery.getCustomers();

            for (Contact contact : contacts) {
                cbContact.getItems().add(contact.getContact_name());
            }

            for (Customer customer : customers) {
                cbCust.getItems().add(customer.getCustomer_name());
            }

            for (int i = (8 + appUtils.hourOffset()); i <= (22 + appUtils.hourOffset()); i++) {
                cbSH.getItems().add(String.valueOf(i));
                cbEH.getItems().add(String.valueOf(i));
            }

            for (int i = 0; i <= 45; i+=15) {
                if (i == 0) {
                    cbSM.getItems().add("00");
                    cbEM.getItems().add("00");
                } else {
                    cbSM.getItems().add(String.valueOf(i));
                    cbEM.getItems().add(String.valueOf(i));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Vars.lang = appUtils.getLanguange();
    }

    @FXML
    public void save() throws SQLException {
        long millis=System.currentTimeMillis();

        String title = txtTitle.getText();
        String desc = txtDesc.getText();
        String loc = txtLoc.getText();
        String type = txtType.getText();
        String startDate = dateStart.getEditor().getText();
        String sH = cbSH.getSelectionModel().getSelectedItem().toString();
        String sM = cbSM.getSelectionModel().getSelectedItem().toString();
        String endDate = dateEnd.getEditor().getText();
        String eH = cbEH.getSelectionModel().getSelectedItem().toString();
        String eM = cbEM.getSelectionModel().getSelectedItem().toString();
        String contact = cbContact.getSelectionModel().getSelectedItem().toString();
        String customer = cbCust.getSelectionModel().getSelectedItem().toString();

        Appointment appointment = new Appointment();
        appointment.setTitle(title);
        appointment.setDesc(desc);
        appointment.setLoc(loc);
        appointment.setType(type);
        appointment.setStartDate(appUtils.localToGMT(appUtils.convertDateTime(startDate,sH, sM)));
        appointment.setEndDate(appUtils.localToGMT(appUtils.convertDateTime(endDate, eH, eM)));
        appointment.setCust_id(dbQuery.getCustomer(customer).getCustomer_id());
        appointment.setContact_id(dbQuery.getContact(contact).getContact_id());
        appointment.setUser_id(currentUser.getUser_id());
        appointment.setCreated_by(currentUser.getUser_name());
        appointment.setLast_updated_by(currentUser.getUser_name());
        appointment.setCreate_date(new java.sql.Date(new Date(millis).getTime()));
        appointment.setLast_update(new Timestamp(millis));
        appointment.setUser_name(currentUser.getUser_name());
        appointment.setCust_name(customer);
        appointment.setCont_name(contact);

        ArrayList<Appointment> conflictingAppointments = dbQuery.getConflicts(appointment);
        for (Appointment conflict : conflictingAppointments) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(conflict.getTitle());
            alert.show();
        }
        if (conflictingAppointments.size() == 0) {
            DBWrite dbWrite = new DBWrite();

            if (dbWrite.AddAppointment(appointment)) {
                futureAppointments.add(appointment);
                Stage stage = (Stage) cbSH.getScene().getWindow();
                stage.close();
            } else {
                if (dbError == null) {
                    appUtils.ShowDBWriteError("adding", "appointment", "The record already exists");
                } else {
                    appUtils.ShowDBWriteError("adding", "appointment", dbError);
                }
            }
        }

    }

}