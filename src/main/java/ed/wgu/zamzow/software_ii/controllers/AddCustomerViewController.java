package ed.wgu.zamzow.software_ii.controllers;

import ed.wgu.zamzow.software_ii.database.DBQuery;
import ed.wgu.zamzow.software_ii.database.DBWrite;
import ed.wgu.zamzow.software_ii.objects.Country;
import ed.wgu.zamzow.software_ii.objects.Customer;
import ed.wgu.zamzow.software_ii.objects.Division;
import ed.wgu.zamzow.software_ii.utils.Vars;
import ed.wgu.zamzow.software_ii.utils.appUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Timestamp;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import static ed.wgu.zamzow.software_ii.utils.Vars.dbError;

/**
 * Controller for adding customers
 *
 * @author Bret Zamzow
 */

public class AddCustomerViewController {

    public ChoiceBox chDivisions;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtPCode;
    public TextField txtPhone;
    public ChoiceBox cbCountry;
    private ArrayList<Division> divisions;
    private ArrayList<Country> countries;

    /**
     * Method to initialize the form and setup the main parts
     */
    public void initialize() {
        DBQuery dbQuery = new DBQuery();
        try {
            divisions = dbQuery.getDivisions();
            countries = dbQuery.getCountries();
            for (Country country : countries) {
                cbCountry.getItems().add(country.getCountry());
            }
            cbCountry.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observableValue, Object o, Object t1) {
                    Country country = null;
                    try {
                        country = dbQuery.getCountry(cbCountry.getSelectionModel().getSelectedItem().toString());
                        divisions = dbQuery.getDivisions(country.getCountry_id());
                        chDivisions.getItems().clear();
                        for (Division division : divisions) {
                            chDivisions.getItems().add(division.getDivision());
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Vars.lang = appUtils.getLanguange();
    }

    /**
     * Method to save the customer to the database
     */
    @FXML
    public void save() {
        long millis=System.currentTimeMillis();

        Customer newCustomer = new Customer();

        newCustomer.setAddress(txtAddress.getText());
        newCustomer.setCustomer_name(txtName.getText());
        newCustomer.setPostal_code(txtPCode.getText());
        newCustomer.setPhone(txtPhone.getText());

        String divName = chDivisions.getSelectionModel().getSelectedItem().toString();
        for (Division division : divisions) {
            if (division.getDivision().equals(divName)) {
                newCustomer.setDivision_id(division.getDivision_id());
            }
        }

        newCustomer.setCreated_by(Vars.currentUser.getUser_name());
        newCustomer.setLast_updated_by(Vars.currentUser.getUser_name());

        newCustomer.setCreate_date(new Date(millis));
        newCustomer.setLast_update(new Timestamp(millis));

        DBWrite dbWrite = new DBWrite();
        if (dbWrite.AddCustomer(newCustomer)) {
            Stage stage = (Stage) txtName.getScene().getWindow();
            stage.close();
        } else {
            if (dbError == null) {
                appUtils.ShowDBWriteError("adding", "customer", "The record already exists");
            } else {
                appUtils.ShowDBWriteError("adding", "customer", dbError);
            }
        }
    }

}