package ed.wgu.zamzow.software_ii.objects;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Appointment object for use throughout the application
 *
 * @author Bret Zamzow
 */
public class Appointment {

    private String title;
    private String desc;
    private String loc;
    private String type;
    private Timestamp startDate;
    private Timestamp endDate;
    private Date create_date;
    private String created_by;
    private Timestamp last_update;
    private String last_updated_by;
    private int cust_id;
    private int user_id;
    private int contact_id;
    private int appointment_id;
    private String cust_name;
    private String cont_name;
    private String user_name;

    /**
     * Method to set the contact ID
     * @param contact_id
     */
    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }

    /**
     * Method to set the create date
     * @param create_date
     */
    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    /**
     * Method to set the user that created the appointment
     * @param created_by
     */
    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    /**
     * Method to set the customer ID
     * @param cust_id
     */
    public void setCust_id(int cust_id) {
        this.cust_id = cust_id;
    }

    /**
     * Method to set the description of the appointment
     * @param desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Method to set the end time of the appointment
     * @param endDate
     */
    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    /**
     * Method to set the last time the appointment was updated
     * @param last_update
     */
    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }

    /**
     * Method to set who updated the appointment last
     * @param last_updated_by
     */
    public void setLast_updated_by(String last_updated_by) {
        this.last_updated_by = last_updated_by;
    }

    /**
     * Method to set the location of the appointment
     * @param loc
     */
    public void setLoc(String loc) {
        this.loc = loc;
    }

    /**
     * Method set the start date of the appointment
     * @param startDate
     */
    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    /**
     * Method to set the title of the appointment
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Method to set the type of appointment
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Method to set the user id for the appointment
     * @param user_id
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    /**
     * Method to set the appointment ID
     * @param appointment_id
     */
    public void setAppointment_id(int appointment_id) {
        this.appointment_id = appointment_id;
    }

    /**
     * Method to set the contact name
     * @param cont_name
     */
    public void setCont_name(String cont_name) {
        this.cont_name = cont_name;
    }

    /**
     * Method to set the customer name
     * @param cust_name
     */
    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    /**
     * Method to set the user name
     * @param user_name
     */
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    /**
     * Method to get the creation date
     * @return
     */
    public Date getCreate_date() {
        return create_date;
    }

    /**
     * Method to get the end date
     * @return
     */
    public Timestamp getEndDate() {
        return endDate;
    }

    /**
     * Method to get the start date
     * @return
     */
    public Timestamp getStartDate() {
        return startDate;
    }

    /**
     * Method to get the contact ID
     * @return
     */
    public int getContact_id() {
        return contact_id;
    }

    /**
     * Method to get the customer ID
     * @return
     */
    public int getCust_id() {
        return cust_id;
    }

    /**
     * Method to get the user ID
     * @return
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * Method to get who created the appointment
     * @return
     */
    public String getCreated_by() {
        return created_by;
    }

    /**
     * Method to get the description
     * @return
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Method to get who last updated the appointment
     * @return
     */
    public String getLast_updated_by() {
        return last_updated_by;
    }

    /**
     * Method to get the location of the appointment
     * @return
     */
    public String getLoc() {
        return loc;
    }

    /**
     * Method to get the title
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * Method to get the type
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * Method to get when the last update was
     * @return
     */
    public Timestamp getLast_update() {
        return last_update;
    }

    /**
     * Method to get the contact name
     * @return
     */
    public String getCont_name() {
        return cont_name;
    }

    /**
     * Method to get the customer name
     * @return
     */
    public String getCust_name() {
        return cust_name;
    }

    /**
     * Method to get the user name
     * @return
     */
    public String getUser_name() {
        return user_name;
    }

    /**
     * Method to get the appointment ID
     * @return
     */
    public int getAppointment_id() {
        return appointment_id;
    }
}
