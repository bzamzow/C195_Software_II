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

/**
 * Class the holds all of the database queries for the application
 *
 * @author Bret Zamzow
 */
public class DBQuery {

    private Statement stmt;
    private ResultSet rs;
    private ResourceBundle bundle;

    /**
     * Initialize the class and create the connection
     */
    public DBQuery() {
        try {
            con = DBConnect.ConnectToDB();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        bundle = ResourceBundle.getBundle("UIResources");

    }

    /**
     * Method to sign-in
     * @param username
     * @param password
     * @return True/False depending on success status
     * @throws SQLException
     */
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

    /**
     * Method to get a list of customers
     * @return list of customer objects
     * @throws SQLException
     */
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

    /**
     * Method to get a specific customer based on customer's name
     * @param customer_name
     * @return Customer object found in the database
     * @throws SQLException
     */
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

    /**
     * Method to get a specific user
     * @param username
     * @return User object for user found in the database
     * @throws SQLException
     */
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

    /**
     * Method to get a list of all users found in the database
     * @return List of user objects
     * @throws SQLException
     */
    public ArrayList<User> getUsers() throws SQLException {
        stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM users");
        ArrayList<User> users = new ArrayList<>();
        while (rs.next()) {
            User user = new User();
            user.setUser_id(rs.getInt("user_id"));
            user.setUser_name(rs.getString("user_name"));
            user.setCreate_date(rs.getDate("create_date"));
            user.setPassword(rs.getString("password"));
            user.setCreated_by(rs.getString("created_by"));
            user.setLast_update(rs.getTimestamp("last_update"));
            user.setLast_updated_by(rs.getString("last_updated_by"));
            users.add(user);
        }

        return users;
    }

    /**
     * Method to get a specific user using user id
     * @param user_id
     * @return User object
     * @throws SQLException
     */
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

    /**
     * Method to get a list of divisions
     * @return
     * @throws SQLException
     */
    public ArrayList<Division> getDivisions() throws SQLException {
        stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM `first_level_divisions`");
        ArrayList<Division> divisions = new ArrayList<>();
        while(rs.next()) {
            Division division = new Division();
            division.setDivision_id(rs.getInt("division_id"));
            division.setDivision(rs.getString("division"));
            divisions.add(division);
        }
        return divisions;
    }

    /**
     * Method to get a list of divisions sharing a country id
     * @param country_id
     * @return
     * @throws SQLException
     */
    public ArrayList<Division> getDivisions(int country_id) throws SQLException {
        stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM `first_level_divisions` WHERE country_id = " + country_id);
        ArrayList<Division> divisions = new ArrayList<>();
        while(rs.next()) {
            Division division = new Division();
            division.setDivision_id(rs.getInt("division_id"));
            division.setDivision(rs.getString("division"));
            divisions.add(division);
        }
        return divisions;
    }

    /**
     * Method to get a specific division using division ID
     * @param division_id
     * @return
     * @throws SQLException
     */
    public Division getDivision(int division_id) throws SQLException {
        stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM `first_level_divisions` WHERE division_id = " + division_id);
        Division division = new Division();
        while(rs.next()) {
            division.setDivision_id(rs.getInt("division_id"));
            division.setDivision(rs.getString("division"));
            division.setCountry_id(rs.getInt("country_id"));
        }
        return division;
    }

    /**
     * Method to get a list of countries
     * @return
     * @throws SQLException
     */
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

    /**
     * Method to get a specific country
     * @param country
     * @return
     * @throws SQLException
     */
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

    /**
     * Method to get a list of appointments
     * @return
     * @throws SQLException
     */
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
            appointment.setLast_update(rs.getTimestamp("last_update"));
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

    /**
     * Method to get a count of appointments per customer and type for the month
     * @return
     * @throws SQLException
     */
    public ArrayList<CustomerReport> getAppointmentsCustomerMonth() throws SQLException {
        stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT customers.customer_name,appointments.Type,COUNT(*) AS 'Count' FROM appointments INNER JOIN users ON appointments.user_id=users.user_id INNER JOIN customers ON appointments.customer_id=customers.customer_id INNER JOIN contacts ON appointments.contact_id=contacts.contact_id GROUP BY MONTH(appointments.start), customers.Customer_Name");
        ArrayList<CustomerReport> reports = new ArrayList<>();
        while(rs.next()) {
            CustomerReport report = new CustomerReport();
            report.setCustomerName(rs.getString("customer_name"));
            report.setAppointmentType(rs.getString("type"));
            report.setCount(rs.getInt("count"));

            reports.add(report);
        }
        return reports;
    }

    /**
     * Method to get each contact's schedule
     * @param contact
     * @return
     * @throws SQLException
     */
    public ArrayList<Appointment> getSchedulePerContact(String contact) throws SQLException {
        stmt = con.createStatement();
        PreparedStatement ps = con.prepareStatement("SELECT appointments.appointment_id, appointments.Title, appointments.Type, appointments.Description, appointments.start, appointments.end, customers.Customer_ID, contacts.Contact_ID, customers.customer_name, contacts.contact_name FROM appointments INNER JOIN users ON appointments.user_id=users.user_id INNER JOIN customers ON appointments.customer_id=customers.customer_id INNER JOIN contacts ON appointments.contact_id=contacts.contact_id where contacts.contact_name = ?");
        ps.setString(1,contact);
        rs = ps.executeQuery();

        ArrayList<Appointment> appointments = new ArrayList<>();
        while(rs.next()) {
            Appointment appointment = new Appointment();
            appointment.setAppointment_id(rs.getInt("appointment_id"));
            appointment.setTitle(rs.getString("title"));
            appointment.setDesc(rs.getString("description"));
            appointment.setType(rs.getString("type"));
            appointment.setStartDate(appUtils.GMTTolocal(rs.getTimestamp("start")));
            appointment.setEndDate(appUtils.GMTTolocal(rs.getTimestamp("end")));
            appointment.setCust_id(rs.getInt("customer_id"));
            appointment.setContact_id(rs.getInt("contact_id"));
            appointment.setCont_name(rs.getString("contact_name"));
            appointments.add(appointment);
        }
        return appointments;
    }

    /**
     * Method to get a list of appointments created by each user
     * @param contact
     * @return
     * @throws SQLException
     */
    public ArrayList<Appointment> getSchedulePerUser(String contact) throws SQLException {
        stmt = con.createStatement();
        PreparedStatement ps = con.prepareStatement("SELECT appointments.created_by, appointments.appointment_id, appointments.Title, appointments.Type, appointments.Description, appointments.start, appointments.end, customers.Customer_ID, contacts.Contact_ID, customers.customer_name, contacts.contact_name FROM appointments INNER JOIN users ON appointments.user_id=users.user_id INNER JOIN customers ON appointments.customer_id=customers.customer_id INNER JOIN contacts ON appointments.contact_id=contacts.contact_id where appointments.created_by = ?");
        ps.setString(1,contact);
        rs = ps.executeQuery();

        ArrayList<Appointment> appointments = new ArrayList<>();
        while(rs.next()) {
            Appointment appointment = new Appointment();
            appointment.setAppointment_id(rs.getInt("appointment_id"));
            appointment.setTitle(rs.getString("title"));
            appointment.setDesc(rs.getString("description"));
            appointment.setType(rs.getString("type"));
            appointment.setStartDate(appUtils.GMTTolocal(rs.getTimestamp("start")));
            appointment.setEndDate(appUtils.GMTTolocal(rs.getTimestamp("end")));
            appointment.setUser_name(rs.getString("created_by"));
            appointment.setContact_id(rs.getInt("contact_id"));
            appointment.setCont_name(rs.getString("contact_name"));
            appointments.add(appointment);
        }
        return appointments;
    }

    /**
     * Method to get a list of appointments that have not yet happened
     * @return
     * @throws SQLException
     */
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
            appointment.setLast_update(rs.getTimestamp("last_update"));
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

    /**
     * Method to get list of appointments for the week
     * @return
     * @throws SQLException
     */
    public ArrayList<Appointment> getWeeklyAppointments() throws SQLException {
        stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM appointments INNER JOIN users ON appointments.user_id=users.user_id INNER JOIN customers ON appointments.customer_id=customers.customer_id INNER JOIN contacts ON appointments.contact_id=contacts.contact_id WHERE week(appointments.start) = week(CURRENT_DATE()) AND YEAR(appointments.Start) = YEAR(CURRENT_DATE())");
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
            appointment.setLast_update(rs.getTimestamp("last_update"));
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

    /**
     * Method to get a list of appointments for the month
     * @return
     * @throws SQLException
     */
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
            appointment.setLast_update(rs.getTimestamp("last_update"));
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

    /**
     * Method to get a list of conflicts for the appointment being checked
     * @param checkingAppointment
     * @return
     * @throws SQLException
     */
    public ArrayList<Appointment> getConflicts(Appointment checkingAppointment) throws SQLException {
        stmt = con.createStatement();

        PreparedStatement ps = con.prepareStatement("SELECT * FROM appointments INNER JOIN users ON appointments.user_id=users.user_id " +
                "INNER JOIN customers ON appointments.customer_id=customers.customer_id INNER JOIN contacts ON " +
                "appointments.contact_id=contacts.contact_id WHERE ? BETWEEN appointments.start AND appointments.end OR ? BETWEEN appointments.start AND appointments.end AND customers.customer_id = ?");
        ps.setTimestamp(1, checkingAppointment.getStartDate());
        ps.setTimestamp(2, checkingAppointment.getEndDate());
        ps.setInt(3, checkingAppointment.getCust_id());
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
            appointment.setLast_update(rs.getTimestamp("last_update"));
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

    /**
     * Method to get a specific contact using contact name
     * @param contact_name
     * @return
     * @throws SQLException
     */
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

    /**
     * Method to get a specific contact using contact ID
     * @param contact_id
     * @return
     * @throws SQLException
     */
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

    /**
     * Method to get a list of all contacts
     * @return
     * @throws SQLException
     */
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

