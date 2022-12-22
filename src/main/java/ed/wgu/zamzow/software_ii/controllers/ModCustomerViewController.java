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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import static ed.wgu.zamzow.software_ii.utils.Vars.dbError;

/**
 * Controller to modify the selected customer
 *
 * @author Bret Zamzow
 */
public class ModCustomerViewController {

    public ChoiceBox chDivisions;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtPCode;
    public TextField txtPhone;
    public TextField txtCustID;
    public ChoiceBox cbCountry;
    private ArrayList<Division> divisions;
    private Customer currentCust;
    private ArrayList<Country> countries;
    private DBQuery dbQuery;

    /**
     * Method to initialize the form and setup the main parts
     */
    public void initialize() {
        dbQuery = new DBQuery();
        try {
            divisions = dbQuery.getDivisions();
            countries = dbQuery.getCountries();
            for (Division division : divisions) {
                chDivisions.getItems().add(division.getDivision());
            }
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
                        int i = 0;
                        for (Division division : divisions) {
                            chDivisions.getItems().add(division.getDivision());
                            if (division.getDivision_id() == currentCust.getDivision_id()) {
                                chDivisions.getSelectionModel().select(i);
                            }
                            i++;
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
     * Method to set which customer is being modified
     * @param currentCust
     */
    public void setCustomer(Customer currentCust) {
        this.currentCust = currentCust;

        LoadData();
    }

    /**
     * Method to load data into the form
     */
    private void LoadData() {
        txtCustID.setText(String.valueOf(currentCust.getCustomer_id()));
        txtAddress.setText(currentCust.getAddress());
        txtName.setText(currentCust.getCustomer_name());
        txtPhone.setText(currentCust.getPhone());
        txtPCode.setText(currentCust.getPostal_code());
        try {
            Division division = dbQuery.getDivision(currentCust.getDivision_id());
            cbCountry.getSelectionModel().select(division.getCountry_id() - 1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method to save the changes into the database
     */
    @FXML
    public void save() {
        long millis=System.currentTimeMillis();

        currentCust.setAddress(txtAddress.getText());
        currentCust.setCustomer_name(txtName.getText());
        currentCust.setPostal_code(txtPCode.getText());
        currentCust.setPhone(txtPhone.getText());

        String divName = chDivisions.getSelectionModel().getSelectedItem().toString();
        divisions.forEach((division -> {
            if (division.getDivision().equals(divName)) {
                currentCust.setDivision_id(division.getDivision_id());
            }
        }));

        currentCust.setCreated_by(Vars.currentUser.getUser_name());
        currentCust.setLast_updated_by(Vars.currentUser.getUser_name());

        currentCust.setCreate_date(new Date(millis));
        currentCust.setLast_update(new Timestamp(millis));

        DBWrite dbWrite = new DBWrite();
        if (dbWrite.ModCustomer(currentCust)) {
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