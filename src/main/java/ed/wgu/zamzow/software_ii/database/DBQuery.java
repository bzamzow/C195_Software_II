package ed.wgu.zamzow.software_ii.database;

import ed.wgu.zamzow.software_ii.objects.*;
import ed.wgu.zamzow.software_ii.utils.Logging;
import ed.wgu.zamzow.software_ii.utils.Vars;
import ed.wgu.zamzow.software_ii.utils.appUtils;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static ed.wgu.zamzow.software_ii.utils.Vars.con;

public class DBQuery {

    private Statement stmt;
    private ResultSet rs;
    private ResourceBundle bundle;

    public DBQuery() {
        try {
            con = DBConnect.ConnectToDB();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        bundle = ResourceBundle.getBundle("UIResources");

    }

    public boolean signIn(String username, String password) throws SQLException {
        boolean signIn = false;
        User user = getUser(username);
        if (user.getUser_name() != null) {
            if (user.getPassword().equals(password)) {
                signIn = true;
                Vars.currentUser = user;
            } else {
                signIn = false;

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(bundle.getString("unsuccess"));
                alert.setContentText(bundle.getString("invalid") + " " + bundle.getString("pass"));
                alert.show();
                Logging.AddToLog(new Timestamp(System.currentTimeMillis()).toString(), "Unsuccessful Login", "Invalid Password" );

            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(bundle.getString("unsuccess"));
            alert.setContentText(bundle.getString("invalid") + " " + bundle.getString("username"));
            alert.show();
            Logging.AddToLog(new Timestamp(System.currentTimeMillis()).toString(), "Unsuccessful Login", "Invalid Username" );
        }
        return signIn;
    }

    public List<Customer> getCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM customers");
        while (rs.next()) {
            Customer cust = new Customer();
            cust.setCustomer_id(rs.getInt("customer_id"));
            cust.setCustomer_name(rs.getString("customer_name"));
            cust.setCreate_date(rs.getDate("create_date"));
            cust.setPhone(rs.getString("phone"));
            cust.setAddress(rs.getString("address"));
            cust.setPostal_code(rs.getString("postal_code"));
            cust.setDivision_id(rs.getInt("division_id"));
            cust.setCreated_by(rs.getString("created_by"));
            cust.setLast_update(rs.getTimestamp("last_update"));
            cust.setLast_updated_by(rs.getString("last_updated_by"));
            customers.add(cust);
        }
        return customers;
    }

    public Customer getCustomer(String customer_name) throws SQLException {
        Customer cust = null;
        stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM customers where customer_name = '" + customer_name + "'");
        while (rs.next()) {
            cust = new Customer();
            cust.setCustomer_id(rs.getInt("customer_id"));
            cust.setCustomer_name(rs.getString("customer_name"));
            cust.setCreate_date(rs.getDate("create_date"));
            cust.setPhone(rs.getString("phone"));
            cust.setAddress(rs.getString("address"));
            cust.setPostal_code(rs.getString("postal_code"));
            cust.setDivision_id(rs.getInt("division_id"));
            cust.setCreated_by(rs.getString("created_by"));
            cust.setLast_update(rs.getTimestamp("last_update"));
            cust.setLast_updated_by(rs.getString("last_updated_by"));

        }
        return cust;
    }

    public User getUser(String username) throws SQLException {
        stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM users WHERE user_name = '" + username + "'");
        User user = new User();
        while (rs.next()) {

            user.setUser_id(rs.getInt("user_id"));
            user.setUser_name(rs.getString("user_name"));
            user.setCreate_date(rs.getDate("create_date"));
            user.setPassword(rs.getString("password"));
            user.setCreated_by(rs.getString("created_by"));
            user.setLast_update(rs.getTimestamp("last_update"));
            user.setLast_updated_by(rs.getString("last_updated_by"));
        }

        return user;
    }

    public User getUser(int user_id) throws SQLException {
        stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM users WHERE user_id = " + user_id);
        User user = new User();
        while (rs.next()) {

            user.setUser_id(rs.getInt("user_id"));
            user.setUser_name(rs.getString("user_name"));
            user.setCreate_date(rs.getDate("create_date"));
            user.setPassword(rs.getString("password"));
            user.setCreated_by(rs.getString("created_by"));
            user.setLast_update(rs.getTimestamp("last_update"));
            user.setLast_updated_by(rs.getString("last_updated_by"));
        }

        return user;
    }

    public ArrayList<Division> getDivisions() throws SQLException {
        stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM `first-level divisions`");
        ArrayList<Division> divisions = new ArrayList<>();
        while(rs.next()) {
            Division division = new Division();
            division.setDivision_id(rs.getInt("division_id"));
            division.setDivision(rs.getString("division"));
            divisions.add(division);
        }
        return divisions;
    }

    public ArrayList<Division> getDivisions(int country_id) throws SQLException {
        stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM `first-level divisions` WHERE country_id = " + country_id);
        ArrayList<Division> divisions = new ArrayList<>();
        while(rs.next()) {
            Division division = new Division();
            division.setDivision_id(rs.getInt("division_id"));
            division.setDivision(rs.getString("division"));
            divisions.add(division);
        }
        return divisions;
    }

    public Division getDivision(int division_id) throws SQLException {
        stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM `first-level divisions` WHERE division_id = " + division_id);
        Division division = new Division();
        while(rs.next()) {
            division.setDivision_id(rs.getInt("division_id"));
            division.setDivision(rs.getString("division"));
            division.setCountry_id(rs.getInt("country_id"));
        }
        return division;
    }

    public ArrayList<Country> getCountries() throws SQLException {
        stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM countries");
        ArrayList<Country> countries = new ArrayList<>();
        while(rs.next()) {
            Country country = new Country();
            country.setCountry_id(rs.getInt("country_id"));
            country.setCountry(rs.getString("country"));
            countries.add(country);
        }
        return countries;
    }

    public Country getCountry(String country) throws SQLException {
        stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM countries WHERE country = '" + country + "'");
        Country c = new Country();
        while(rs.next()) {

            c.setCountry_id(rs.getInt("country_id"));
            c.setCountry(rs.getString("country"));

        }
        return c;
    }

    public ArrayList<Appointment> getAppointments() throws SQLException {
        stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM appointments INNER JOIN users ON appointments.user_id=users.user_id " +
                "INNER JOIN customers ON appointments.customer_id=customers.customer_id INNER JOIN contacts ON " +
                "appointments.contact_id=contacts.contact_id");
        ArrayList<Appointment> appointments = new ArrayList<>();
        while(rs.next()) {
            Appointment appointment = new Appointment();
            appointment.setAppointment_id(rs.getInt("appointment_id"));
            appointment.setTitle(rs.getString("title"));
            appointment.setDesc(rs.getString("description"));
            appointment.setLoc(rs.getString("location"));
            appointment.setType(rs.getString("type"));
            appointment.setStartDate(appUtils.GMTTolocal(rs.getTimestamp("start")));
            appointment.setEndDate(appUtils.GMTTolocal(rs.getTimestamp("end")));
            appointment.setCreate_date(rs.getDate("create_date"));
            appointment.setCreated_by(rs.getString("created_by"));
            appointment.setLast_update(rs.getTimestamp("last_updated"));
            appointment.setLast_updated_by(rs.getString("last_updated_by"));
            appointment.setCust_id(rs.getInt("customer_id"));
            appointment.setUser_id(rs.getInt("user_id"));
            appointment.setContact_id(rs.getInt("contact_id"));
            appointment.setCust_name(rs.getString("customer_name"));
            appointment.setCont_name(rs.getString("contact_name"));
            appointment.setUser_name(rs.getString("user_name"));
            appointments.add(appointment);
        }
        return appointments;
    }

    public ArrayList<Appointment> getFutureAppointments() throws SQLException {
        stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM appointments INNER JOIN users ON appointments.user_id=users.user_id " +
                "INNER JOIN customers ON appointments.customer_id=customers.customer_id INNER JOIN contacts ON " +
                "appointments.contact_id=contacts.contact_id WHERE appointments.start > NOW()");
        ArrayList<Appointment> appointments = new ArrayList<>();
        while(rs.next()) {
            Appointment appointment = new Appointment();
            appointment.setAppointment_id(rs.getInt("appointment_id"));
            appointment.setTitle(rs.getString("title"));
            appointment.setDesc(rs.getString("description"));
            appointment.setLoc(rs.getString("location"));
            appointment.setType(rs.getString("type"));
            appointment.setStartDate(appUtils.GMTTolocal(rs.getTimestamp("start")));
            appointment.setEndDate(appUtils.GMTTolocal(rs.getTimestamp("end")));
            appointment.setCreate_date(rs.getDate("create_date"));
            appointment.setCreated_by(rs.getString("created_by"));
            appointment.setLast_update(rs.getTimestamp("last_updated"));
            appointment.setLast_updated_by(rs.getString("last_updated_by"));
            appointment.setCust_id(rs.getInt("customer_id"));
            appointment.setUser_id(rs.getInt("user_id"));
            appointment.setContact_id(rs.getInt("contact_id"));
            appointment.setCust_name(rs.getString("customer_name"));
            appointment.setCont_name(rs.getString("contact_name"));
            appointment.setUser_name(rs.getString("user_name"));

            appointments.add(appointment);
        }
        return appointments;
    }

    public ArrayList<Appointment> getWeeklyAppointments() throws SQLException {
        stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM appointments INNER JOIN users ON appointments.user_id=users.user_id INNER JOIN customers ON appointments.customer_id=customers.customer_id INNER JOIN contacts ON appointments.contact_id=contacts.contact_id WHERE MONTH(appointments.start) = MONTH(CURRENT_DATE()) AND YEAR(appointments.Start) = YEAR(CURRENT_DATE())");
        ArrayList<Appointment> appointments = new ArrayList<>();
        while(rs.next()) {
            Appointment appointment = new Appointment();
            appointment.setAppointment_id(rs.getInt("appointment_id"));
            appointment.setTitle(rs.getString("title"));
            appointment.setDesc(rs.getString("description"));
            appointment.setLoc(rs.getString("location"));
            appointment.setType(rs.getString("type"));
            appointment.setStartDate(appUtils.GMTTolocal(rs.getTimestamp("start")));
            appointment.setEndDate(appUtils.GMTTolocal(rs.getTimestamp("end")));
            appointment.setCreate_date(rs.getDate("create_date"));
            appointment.setCreated_by(rs.getString("created_by"));
            appointment.setLast_update(rs.getTimestamp("last_updated"));
            appointment.setLast_updated_by(rs.getString("last_updated_by"));
            appointment.setCust_id(rs.getInt("customer_id"));
            appointment.setUser_id(rs.getInt("user_id"));
            appointment.setContact_id(rs.getInt("contact_id"));
            appointment.setCust_name(rs.getString("customer_name"));
            appointment.setCont_name(rs.getString("contact_name"));
            appointment.setUser_name(rs.getString("user_name"));

            appointments.add(appointment);
        }
        return appointments;
    }

    public ArrayList<Appointment> getMonthlyAppointments() throws SQLException {
        stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM appointments INNER JOIN users ON appointments.user_id=users.user_id INNER JOIN customers ON appointments.customer_id=customers.customer_id INNER JOIN contacts ON appointments.contact_id=contacts.contact_id WHERE MONTH(appointments.start) = MONTH(CURRENT_DATE()) AND YEAR(appointments.Start) = YEAR(CURRENT_DATE())");
        ArrayList<Appointment> appointments = new ArrayList<>();
        while(rs.next()) {
            Appointment appointment = new Appointment();
            appointment.setAppointment_id(rs.getInt("appointment_id"));
            appointment.setTitle(rs.getString("title"));
            appointment.setDesc(rs.getString("description"));
            appointment.setLoc(rs.getString("location"));
            appointment.setType(rs.getString("type"));
            appointment.setStartDate(appUtils.GMTTolocal(rs.getTimestamp("start")));
            appointment.setEndDate(appUtils.GMTTolocal(rs.getTimestamp("end")));
            appointment.setCreate_date(rs.getDate("create_date"));
            appointment.setCreated_by(rs.getString("created_by"));
            appointment.setLast_update(rs.getTimestamp("last_updated"));
            appointment.setLast_updated_by(rs.getString("last_updated_by"));
            appointment.setCust_id(rs.getInt("customer_id"));
            appointment.setUser_id(rs.getInt("user_id"));
            appointment.setContact_id(rs.getInt("contact_id"));
            appointment.setCust_name(rs.getString("customer_name"));
            appointment.setCont_name(rs.getString("contact_name"));
            appointment.setUser_name(rs.getString("user_name"));

            appointments.add(appointment);
        }
        return appointments;
    }

    public ArrayList<Appointment> getConflicts(Appointment checkingAppointment) throws SQLException {
        stmt = con.createStatement();

        PreparedStatement ps = con.prepareStatement("SELECT * FROM appointments INNER JOIN users ON appointments.user_id=users.user_id " +
                "INNER JOIN customers ON appointments.customer_id=customers.customer_id INNER JOIN contacts ON " +
                "appointments.contact_id=contacts.contact_id WHERE appointments.start > NOW() AND ( ? BETWEEN appointments.start AND appointments.end) AND customers.customer_id = ?");
        ps.setTimestamp(1, checkingAppointment.getStartDate());
        ps.setInt(2, checkingAppointment.getCust_id());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(ps.toString());
        alert.show();
        ResultSet rs = ps.executeQuery();
        ArrayList<Appointment> appointments = new ArrayList<>();
        while(rs.next()) {
            Appointment appointment = new Appointment();
            appointment.setAppointment_id(rs.getInt("appointment_id"));
            appointment.setTitle(rs.getString("title"));
            appointment.setDesc(rs.getString("description"));
            appointment.setLoc(rs.getString("location"));
            appointment.setType(rs.getString("type"));
            appointment.setStartDate(appUtils.GMTTolocal(rs.getTimestamp("start")));
            appointment.setEndDate(appUtils.GMTTolocal(rs.getTimestamp("end")));
            appointment.setCreate_date(rs.getDate("create_date"));
            appointment.setCreated_by(rs.getString("created_by"));
            appointment.setLast_update(rs.getTimestamp("last_updated"));
            appointment.setLast_updated_by(rs.getString("last_updated_by"));
            appointment.setCust_id(rs.getInt("customer_id"));
            appointment.setUser_id(rs.getInt("user_id"));
            appointment.setContact_id(rs.getInt("contact_id"));
            appointment.setCust_name(rs.getString("customer_name"));
            appointment.setCont_name(rs.getString("contact_name"));
            appointment.setUser_name(rs.getString("user_name"));

            appointments.add(appointment);
        }
        return appointments;
    }

    public Contact getContact(String contact_name) throws SQLException {
        Contact cont = null;
        stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM contacts where contact_name = '" + contact_name + "'");
        while (rs.next()) {
            cont = new Contact();
            cont.setContact_id(rs.getInt("contact_id"));
            cont.setContact_name(rs.getString("contact_name"));
            cont.setEmail(rs.getString("email"));

        }
        return cont;
    }

    public Contact getContact(int contact_id) throws SQLException {
        Contact cont = null;
        stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM contacts where contact_id = " + contact_id);
        while (rs.next()) {
            cont = new Contact();
            cont.setContact_id(rs.getInt("contact_id"));
            cont.setContact_name(rs.getString("contact_name"));
            cont.setEmail(rs.getString("email"));

        }
        return cont;
    }

    public ArrayList<Contact> getContacts() throws SQLException {
        ArrayList<Contact> contacts = new ArrayList<>();
        stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM contacts");
        while (rs.next()) {
            Contact cont = new Contact();
            cont.setContact_id(rs.getInt("contact_id"));
            cont.setContact_name(rs.getString("contact_name"));
            cont.setEmail(rs.getString("email"));
            contacts.add(cont);
        }
        return contacts;
    }
}

