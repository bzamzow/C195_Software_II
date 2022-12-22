package ed.wgu.zamzow.software_ii.controllers;

import ed.wgu.zamzow.software_ii.database.DBQuery;
import ed.wgu.zamzow.software_ii.database.DBWrite;
import ed.wgu.zamzow.software_ii.objects.Customer;
import ed.wgu.zamzow.software_ii.utils.Vars;
import ed.wgu.zamzow.software_ii.utils.appUtils;
import javafx.collections.FXCollections;
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


/**
 * Controller to show all customers
 *
 * @author Bret Zamzow
 */
public class ShowCustomersViewController {

    public TableView<Customer> tblCustomers;
    public TableColumn colName, colPhone, colAddress, colPCode, colDivision;
    public Button btnDel, btnMod;
    private ObservableList<Customer> allCustomers;
    private DBQuery dbQuery;

    /**
     * Method to initialize the form and setup the main parts
     */
    public void initialize() {
        dbQuery = new DBQuery();
        try {
            allCustomers = FXCollections.observableList(dbQuery.getCustomers());
            LoadData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Vars.lang = appUtils.getLanguange();
    }

    /**
     * Method to load data from the database into the form
     */
    private void LoadData() {
        colName.setCellValueFactory(new PropertyValueFactory("customer_name"));
        colPhone.setCellValueFactory(new PropertyValueFactory("phone"));
        colAddress.setCellValueFactory(new PropertyValueFactory("address"));
        colPCode.setCellValueFactory(new PropertyValueFactory("postal_code"));
        colDivision.setCellValueFactory(new PropertyValueFactory("division_id"));

        tblCustomers.setItems(allCustomers);
    }

    /**
     * Method to open the modify customer view based on the selected customer
     */
    @FXML
    private void ModifyCustomer() {
        Customer customer = tblCustomers.getSelectionModel().getSelectedItem();
        URL fxmlLocation = getClass().getClassLoader().getResource("mod_customer-view.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Parent mainRoot = null;
        try {
            mainRoot = loader.load();
            ModCustomerViewController controller = loader.getController();
            controller.setCustomer(customer);

            Stage stage = new Stage();

            stage.setScene(new Scene(mainRoot));
            stage.show();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage thisStage = (Stage) tblCustomers.getScene().getWindow();
        thisStage.close();

    }

    /**
     * Method to delete the selected customer if confirmed
     */
    @FXML
    private void DeleteCustomer() {
        Customer customer = tblCustomers.getSelectionModel().getSelectedItem();

        Alert confirm = new Alert(Alert.AlertType.WARNING,"Are you sure you want to delete " + customer.getCustomer_name(), ButtonType.YES, ButtonType.NO);
        confirm.setTitle("Delete?");
        confirm.showAndWait();

        if (confirm.getResult() == ButtonType.YES) {
            DBWrite dbWrite = new DBWrite();
            dbWrite.DeleteCustomerAppointments(customer.getCustomer_id());
            dbWrite.DeleteCustomer(customer);
            Stage thisStage = (Stage) tblCustomers.getScene().getWindow();
            thisStage.close();
        }
    }
}