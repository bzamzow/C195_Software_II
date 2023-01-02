package ed.wgu.zamzow.software_ii.controllers;

import ed.wgu.zamzow.software_ii.database.DBQuery;
import ed.wgu.zamzow.software_ii.database.DBWrite;
import ed.wgu.zamzow.software_ii.objects.Appointment;
import ed.wgu.zamzow.software_ii.objects.Contact;
import ed.wgu.zamzow.software_ii.objects.Customer;
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
 * Controller for modifying appointments
 *
 * @author Bret Zamzow
 */
public class ModAppointmentViewController {

    public TextField txtTitle;
    public TextField txtDesc;
    public TextField txtLoc;
    public TextField txtType;
    public ChoiceBox cbContact;
    public ChoiceBox cbCust;
    public DatePicker dateStart;
    public DatePicker dateEnd;
    public ChoiceBox cbSH, cbSM, cbEH, cbEM;
    public TextField txtAppointmentID;
    private DBQuery dbQuery;
    private List<Customer> customers;
    private ArrayList<Contact> contacts;

    private Appointment currentAppointment;
    private int index;

    /**
     * Method to initialize the form and set up the main parts of the form
     * <p>
     *     Use Lambda here on contacts and customers to provide the most efficient way to add contact and customer names to choice boxes.
     *     This lambda expression iterates through these two ArrayLists and provides the names to the controls while saving code and time.
     * </p>
     */
    public void initialize() {
        dbQuery = new DBQuery();

        try {
            contacts = dbQuery.getContacts();
            customers = dbQuery.getCustomers();

            contacts.forEach((contact) -> cbContact.getItems().add(contact.getContact_name()));
            customers.forEach((customer) -> cbCust.getItems().add(customer.getCustomer_name()));

            int startTime = 8 + appUtils.hourOffset();
            int endTime = 22 + appUtils.hourOffset();
            for (int i = startTime; i <= endTime; i++) {
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
     * Method to save the changes to the database and display in the application
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

            Appointment appointment = new Appointment();
            appointment.setAppointment_id(Integer.parseInt(txtAppointmentID.getText()));
            appointment.setTitle(title);
            appointment.setDesc(desc);
            appointment.setLoc(loc);
            appointment.setType(type);
            appointment.setStartDate(appUtils.localToGMT(appUtils.convertDateTime(startDate, sH, sM)));
            appointment.setEndDate(appUtils.localToGMT(appUtils.convertDateTime(endDate, eH, eM)));
            appointment.setCust_id(dbQuery.getCustomer(customer).getCustomer_id());
            appointment.setContact_id(dbQuery.getContact(contact).getContact_id());
            appointment.setUser_id(currentUser.getUser_id());
            appointment.setCreated_by(currentUser.getUser_name());
            appointment.setLast_updated_by(currentUser.getUser_name());
            appointment.setUser_name(currentUser.getUser_name());
            appointment.setCust_name(customer);
            appointment.setCont_name(contact);
            appointment.setCreate_date(new java.sql.Date(new Date(millis).getTime()));
            appointment.setLast_update(new Timestamp(millis));


            ArrayList<Appointment> conflictingAppointments = dbQuery.getConflicts(appointment);
            if (conflictingAppointments.size() == 0) {
                DBWrite dbWrite = new DBWrite();

                if (dbWrite.ModAppointment(appointment)) {
                    futureAppointments.set(index, appointment);
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

    /**
     * Method to set which appointment is being modified
     * @param currentAppointment
     * @param index
     */
    public void setAppointment(Appointment currentAppointment, int index) {
        this.currentAppointment = currentAppointment;
        this.index = index;
        LoadData();
    }

    /**
     * Method to load data from the database into the form
     */
    private void LoadData() {
        txtAppointmentID.setText(String.valueOf(currentAppointment.getAppointment_id()));
        txtTitle.setText(currentAppointment.getTitle());
        txtDesc.setText(currentAppointment.getDesc());
        txtLoc.setText(currentAppointment.getLoc());
        txtType.setText(currentAppointment.getType());
        dateStart.getEditor().setText(appUtils.splitDate(true,currentAppointment.getStartDate().toString()));
        dateEnd.getEditor().setText(appUtils.splitDate(true, currentAppointment.getEndDate().toString()));

        String startTime = appUtils.splitDate(false,currentAppointment.getStartDate().toString());
        String startHour = appUtils.splitTime(1,startTime);
        String startMin = appUtils.splitTime(2,startTime);

        String endTime = appUtils.splitDate(false, currentAppointment.getEndDate().toString());
        String endHour = appUtils.splitTime(1,endTime);
        String endMin = appUtils.splitTime(2,endTime);

        for (int i = 0; i < cbSH.getItems().size(); i++) {
            if (cbSH.getItems().get(i).toString().equals(startHour)) {
                cbSH.getSelectionModel().select(i);
            }
        }
        for (int i = 0; i < cbSM.getItems().size(); i++) {
            if (cbSM.getItems().get(i).toString().equals(startMin)) {
                cbSM.getSelectionModel().select(i);
            }
        }
        for (int i = 0; i < cbEH.getItems().size(); i++) {
            if (cbEH.getItems().get(i).toString().equals(endHour)) {
                cbEH.getSelectionModel().select(i);
            }
        }
        for (int i = 0; i < cbEM.getItems().size(); i++) {
            if (cbEM.getItems().get(i).toString().equals(endMin)) {
                cbEM.getSelectionModel().select(i);
            }
        }


        for (int i = 0; i < cbCust.getItems().size(); i++) {
            if (cbCust.getItems().get(i).toString().equals(currentAppointment.getCust_name())) {
                cbCust.getSelectionModel().select(i);
            }
        }
        for (int i = 0; i < cbContact.getItems().size(); i++) {
            if (cbContact.getItems().get(i).toString().equals(currentAppointment.getCont_name())) {
                cbContact.getSelectionModel().select(i);
            }
        }
    }
}