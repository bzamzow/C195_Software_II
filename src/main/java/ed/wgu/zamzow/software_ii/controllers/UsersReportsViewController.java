package ed.wgu.zamzow.software_ii.controllers;

import ed.wgu.zamzow.software_ii.database.DBQuery;
import ed.wgu.zamzow.software_ii.objects.Appointment;
import ed.wgu.zamzow.software_ii.objects.Contact;
import ed.wgu.zamzow.software_ii.objects.User;
import ed.wgu.zamzow.software_ii.utils.Vars;
import ed.wgu.zamzow.software_ii.utils.appUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.ArrayList;

public class UsersReportsViewController {

    public TableView<Appointment> tblUsersReport;
    public TableColumn colStart;
    public TableColumn colType;
    public TableColumn colID;

    public TableColumn colTitle;
    public TableColumn colDesc;
    public TableColumn colEnd;
    public ChoiceBox cmbUsers;
    public TableColumn colUser;

    private ObservableList<Appointment> reporting;
    private DBQuery dbQuery;
    private ArrayList<User> users;

    public void initialize() {
        dbQuery = new DBQuery();
        try {
            users = dbQuery.getUsers();
            users.forEach((u) -> {
                cmbUsers.getItems().add(u.getUser_name());
            });
            cmbUsers.getSelectionModel().select(0);
            reporting =  FXCollections.observableList(dbQuery.getSchedulePerUser(users.get(0).getUser_name()));

            cmbUsers.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                 @Override
                 public void changed(ObservableValue observableValue, Object o, Object t1) {
                     try {
                         reporting =  FXCollections.observableList(dbQuery.getSchedulePerUser(cmbUsers.getSelectionModel().getSelectedItem().toString()));
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
        colUser.setCellValueFactory(new PropertyValueFactory("user_name"));
        colType.setCellValueFactory(new PropertyValueFactory("type"));
        colStart.setCellValueFactory(new PropertyValueFactory("startDate"));
        colID.setCellValueFactory(new PropertyValueFactory("appointment_id"));
        colDesc.setCellValueFactory(new PropertyValueFactory("desc"));
        colEnd.setCellValueFactory(new PropertyValueFactory("endDate"));
        colTitle.setCellValueFactory(new PropertyValueFactory("title"));

        tblUsersReport.setItems(reporting);

        reporting.addListener((ListChangeListener<Appointment>) change -> {
        });
    }
}