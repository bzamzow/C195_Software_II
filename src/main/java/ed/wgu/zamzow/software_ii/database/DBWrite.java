package ed.wgu.zamzow.software_ii.database;

import ed.wgu.zamzow.software_ii.objects.Appointment;
import ed.wgu.zamzow.software_ii.objects.Customer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import static ed.wgu.zamzow.software_ii.utils.Vars.*;

/**
 * Class that contains all database write functions
 *
 * @author Bret Zamzow
 */
public class DBWrite {

    private PreparedStatement stmt;

    /**
     * Method to instantiate the object and connect to the database
     */
    public DBWrite() {
        try {
            con = DBConnect.ConnectToDB();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Method to add a new customer
     * @param newCustomer
     * @return
     */
    public boolean AddCustomer(Customer newCustomer) {
        boolean didWrite = false;

        try {
            String query = "INSERT INTO customers (customer_name,address,postal_code,phone,created_by,create_date,last_update,last_updated_by,division_id) " +
                    "VALUES(?,?,?,?,?,?,?,?,?)";
            stmt = con.prepareStatement(query);
            stmt.setString(1,newCustomer.getCustomer_name());
            stmt.setString(2,newCustomer.getAddress());
            stmt.setString(3,newCustomer.getPostal_code());
            stmt.setString(4,newCustomer.getPhone());
            stmt.setString(5,newCustomer.getCreated_by());
            stmt.setDate(6,new java.sql.Date(newCustomer.getCreate_date().getTime()));
            stmt.setTimestamp(7,newCustomer.getLast_update());
            stmt.setString(8,newCustomer.getLast_updated_by());
            stmt.setInt(9, newCustomer.getDivision_id());
            if (stmt.executeUpdate() == 1) {
                didWrite = true;
            } else {
                didWrite = false;
            }
        } catch (SQLException e) {
            didWrite = false;
            dbError = e.getMessage();
        }
        return didWrite;
    }

    /**
     * Method to modify a customer
     * @param currentCust
     * @return
     */
    public boolean ModCustomer(Customer currentCust) {
        boolean didWrite = false;

        try {
            String query = "UPDATE customers set customer_name = '" + currentCust.getCustomer_name() +
                "',address = '" + currentCust.getAddress() + "',postal_code = '" + currentCust.getPostal_code() +
                "',phone = '" + currentCust.getPhone() + "',last_update = '" + currentCust.getLast_update() +
                "',last_updated_by = '" + currentCust.getLast_updated_by() + "',division_id = '" + currentCust.getDivision_id() +
                "' WHERE customer_id = '" + currentCust.getCustomer_id() + "';";
            stmt = con.prepareStatement(query);
            if (stmt.executeUpdate() == 1) {
                didWrite = true;
            } else {
                didWrite = false;
            }
        } catch (SQLException e) {
            didWrite = false;
            dbError = e.getMessage();
        }
        return didWrite;
    }

    /**
     * Method to delete a customer
     * @param currentCust
     * @return
     */
    public boolean DeleteCustomer(Customer currentCust) {
        boolean didWrite = false;

        try {
            String query = "DELETE FROM customers WHERE customer_id = '" + currentCust.getCustomer_id() + "';";
            stmt = con.prepareStatement(query);
            if (stmt.executeUpdate() == 1) {
                didWrite = true;
            } else {
                didWrite = false;
            }
        } catch (SQLException e) {
            didWrite = false;
            dbError = e.getMessage();
        }
        return didWrite;
    }

    /**
     * Method to add an appointment
     * @param appointment
     * @return
     */
    public boolean AddAppointment(Appointment appointment) {
        boolean didWrite = false;

        try {
            String query = "INSERT INTO appointments (title,description,location,type,start,end,create_date,created_by,last_update, last_updated_by, customer_id, user_id,contact_id) " +
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            stmt = con.prepareStatement(query);
            stmt.setString(1,appointment.getTitle());
            stmt.setString(2,appointment.getDesc());
            stmt.setString(3,appointment.getLoc());
            stmt.setString(4,appointment.getType());
            stmt.setTimestamp(5,appointment.getStartDate());
            stmt.setTimestamp(6,appointment.getEndDate());
            stmt.setTimestamp(7,new Timestamp(System.currentTimeMillis()));
            stmt.setString(8,appointment.getCreated_by());
            stmt.setTimestamp(9,appointment.getLast_update());
            stmt.setString(10,appointment.getLast_updated_by());
            stmt.setInt(11,appointment.getCust_id());
            stmt.setInt(12, appointment.getUser_id());
            stmt.setInt(13, appointment.getContact_id());
            if (stmt.executeUpdate() == 1) {
                didWrite = true;
            } else {
                didWrite = false;
            }
        } catch (SQLException e) {
            didWrite = false;
            dbError = e.getMessage();
        }
        return didWrite;
    }

    /**
     * Method to modify an appointment
     * @param currentAppointment
     * @return
     */
    public boolean ModAppointment(Appointment currentAppointment) {
        boolean didWrite = false;

        try {
            String query = "UPDATE appointments set title = '" + currentAppointment.getTitle() +
                    "',description = '" + currentAppointment.getDesc() + "',location = '" + currentAppointment.getLoc() +
                    "',type = '" + currentAppointment.getType() + "',start = '" + currentAppointment.getStartDate() +
                    "',end = '" + currentAppointment.getEndDate() + "',last_update = '" + new Timestamp(System.currentTimeMillis()) +
                    "',last_updated_by = '" + currentUser.getUser_name() + "',customer_id = '" + currentAppointment.getCust_id() +
                    "',contact_id ='" + currentAppointment.getContact_id() + "',user_id = '" + currentAppointment.getUser_id() +
                    "' WHERE appointment_id = " + currentAppointment.getAppointment_id() + ";";
            stmt = con.prepareStatement(query);
            if (stmt.executeUpdate() == 1) {
                didWrite = true;
            } else {
                didWrite = false;
            }
        } catch (SQLException e) {
            didWrite = false;
            dbError = e.getMessage();
        }
        return didWrite;
    }

    /**
     * Method to delete an appointment
     * @param currentAppointment
     * @return
     */
    public boolean DeleteAppointment(Appointment currentAppointment) {
        boolean didWrite = false;

        try {
            String query = "DELETE FROM appointments WHERE appointment_id = '" + currentAppointment.getAppointment_id() + "';";
            stmt = con.prepareStatement(query);
            if (stmt.executeUpdate() == 1) {
                didWrite = true;
            } else {
                didWrite = false;
            }
        } catch (SQLException e) {
            didWrite = false;
            dbError = e.getMessage();
        }
        return didWrite;
    }

    /**
     * Method to delete a customers appointments. This only gets used if deleting a customer
     * @param customer_id
     * @return
     */
    public boolean DeleteCustomerAppointments(int customer_id) {
        boolean didWrite = false;

        try {
            String query = "DELETE FROM appointments WHERE customer_id = " + customer_id + ";";
            stmt = con.prepareStatement(query);
            if (stmt.executeUpdate() == 1) {
                didWrite = true;
            } else {
                didWrite = false;
            }
        } catch (SQLException e) {
            didWrite = false;
            dbError = e.getMessage();
        }
        return didWrite;
    }
}
