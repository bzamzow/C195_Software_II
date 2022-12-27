package ed.wgu.zamzow.software_ii.controllers;

import ed.wgu.zamzow.software_ii.database.DBQuery;
import ed.wgu.zamzow.software_ii.database.DBWrite;
import ed.wgu.zamzow.software_ii.objects.*;
import ed.wgu.zamzow.software_ii.utils.Vars;
import ed.wgu.zamzow.software_ii.utils.appUtils;
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

/**
 * Controller for adding appointments
 *
 * @author Bret Zamzow
 */

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

    /**
     * Method to initialize the form, setting up the main parts
     */
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

    /**
     * Method to save the appointment to the database and display in the application
     * @throws SQLException
     */
    @FXML
    public void save() throws SQLException {
        long millis=System.currentTimeMillis();

        boolean hasError = false;
        String title = null;
        String desc = null;
        String loc = null;
        String type = null;
        String startDate = null;
        String sH = null;
        String sM = null;
        String endDate = null;
        String eH = null;
        String eM = null;
        String contact = null;
        String customer = null;
        if (txtTitle.getText() != null && !txtTitle.getText().isBlank()) {
            title = txtTitle.getText();
            if (txtDesc.getText() != null && !txtTitle.getText().isBlank()) {
                desc = txtDesc.getText();
                if (txtLoc.getText() != null && !txtLoc.getText().isBlank()) {
                    loc = txtLoc.getText();
                    if (txtType.getText() != null && !txtType.getText().isBlank()) {
                        type = txtType.getText();
                        if (dateStart.getEditor().getText() != null && !dateStart.getEditor().getText().isBlank()) {
                            startDate = dateStart.getEditor().getText();
                            if (dateEnd.getEditor().getText() != null && !dateEnd.getEditor().getText().isBlank()) {
                                endDate = dateEnd.getEditor().getText();
                            } else {
                                hasError = true;
                            }
                        } else {
                            hasError = true;
                        }
                    } else {
                        hasError = true;
                    }
                } else {
                    hasError = true;
                }
            } else {
                hasError = true;
            }
        } else {
            hasError = true;
        }

        sH = cbSH.getSelectionModel().getSelectedItem().toString();
        sM = cbSM.getSelectionModel().getSelectedItem().toString();
        eH = cbEH.getSelectionModel().getSelectedItem().toString();
        eM = cbEM.getSelectionModel().getSelectedItem().toString();
        contact = cbContact.getSelectionModel().getSelectedItem().toString();
        customer = cbCust.getSelectionModel().getSelectedItem().toString();

        if (!hasError) {
            Appointment localTime = new Appointment();
            Appointment appointment = new Appointment();
            localTime.setTitle(title);
            appointment.setTitle(title);
            localTime.setDesc(desc);
            appointment.setDesc(desc);
            localTime.setLoc(loc);
            appointment.setLoc(loc);
            localTime.setType(type);
            appointment.setType(type);
            localTime.setStartDate(appUtils.convertDateTime(startDate, sH, sM));
            localTime.setEndDate(appUtils.convertDateTime(endDate, eH, eM));
            appointment.setStartDate(appUtils.localToGMT(appUtils.convertDateTime(startDate, sH, sM)));
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
            localTime.setCust_id(dbQuery.getCustomer(customer).getCustomer_id());
            localTime.setContact_id(dbQuery.getContact(contact).getContact_id());
            localTime.setUser_id(currentUser.getUser_id());
            localTime.setCreated_by(currentUser.getUser_name());
            localTime.setLast_updated_by(currentUser.getUser_name());
            localTime.setCreate_date(new java.sql.Date(new Date(millis).getTime()));
            localTime.setLast_update(new Timestamp(millis));
            localTime.setUser_name(currentUser.getUser_name());
            localTime.setCust_name(customer);
            localTime.setCont_name(contact);

            ArrayList<Appointment> conflictingAppointments = dbQuery.getConflicts(appointment);
            for (Appointment conflict : conflictingAppointments) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("This appointment conflicts with: " + conflict.getTitle() + " please select a new appointment time");
                alert.show();
            }
            if (conflictingAppointments.size() == 0) {
                DBWrite dbWrite = new DBWrite();

                if (dbWrite.AddAppointment(appointment)) {
                    futureAppointments.add(localTime);
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
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing Fields");
            alert.setContentText("All fields are required");
            alert.show();
        }

    }

}