package ed.wgu.zamzow.software_ii.controllers;

import ed.wgu.zamzow.software_ii.database.DBQuery;
import ed.wgu.zamzow.software_ii.objects.CustomerReport;
import ed.wgu.zamzow.software_ii.utils.Vars;
import ed.wgu.zamzow.software_ii.utils.appUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.SQLException;

/**
 * Controller for viewing the customer reports
 *
 * @author Bret Zamzow
 */

public class CustomerReportsViewController {

    public TableView<CustomerReport> tblCustomerReport;
    public TableColumn colCustomer;
    public TableColumn colType;
    public TableColumn colCount;

    private ObservableList<CustomerReport> reporting;
    private DBQuery dbQuery;

    /**
     * Method to initialize the form and setup the main parts of the form
     */
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

    /**
     * Method to load data from the database into the table for viewing
     */
    private void LoadData() {
        colCustomer.setCellValueFactory(new PropertyValueFactory("customerName"));
        colType.setCellValueFactory(new PropertyValueFactory("appointmentType"));
        colCount.setCellValueFactory(new PropertyValueFactory("count"));
        tblCustomerReport.setItems(reporting);
    }
}