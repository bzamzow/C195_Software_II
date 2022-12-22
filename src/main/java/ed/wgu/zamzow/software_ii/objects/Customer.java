package ed.wgu.zamzow.software_ii.objects;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Object for storing the customer information to be used throughout the application
 *
 * @author Bret Zamzow
 */
public class Customer {

    private int customer_id;
    private String customer_name;
    private String address;
    private String postal_code;
    private String phone;
    private Date create_date;
    private String created_by;
    private Timestamp last_update;
    private String last_updated_by;
    private int division_id;

    /**
     * Method to instantiate the object with preset data
     * @param customer_id
     * @param customer_name
     * @param address
     * @param postal_code
     * @param phone
     * @param create_date
     * @param created_by
     * @param last_update
     * @param last_updated_by
     * @param division_id
     */
    public Customer(int customer_id, String customer_name, String address, String postal_code, String phone, Date create_date, String created_by, Timestamp last_update, String last_updated_by, int division_id) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.address = address;
        this.postal_code = postal_code;
        this.phone = phone;
        this.create_date = create_date;
        this.created_by = created_by;
        this.last_update = last_update;
        this.last_updated_by = last_updated_by;
        this.division_id = division_id;
    }

    public Customer(){}

    /**
     * Method to set who last updated the customer
     * @param last_updated_by
     */
    public void setLast_updated_by(String last_updated_by) {
        this.last_updated_by = last_updated_by;
    }

    /**
     * Method to set when the customer was last updated
     * @param last_update
     */
    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }

    /**
     * Method to set who created the customer
     * @param created_by
     */
    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    /**
     * Method to set when the customer was created
     * @param create_date
     */
    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    /**
     * Method to get who created the customer
     * @return
     */
    public String getCreated_by() {
        return created_by;
    }

    /**
     * Method to get when the customer was last updated
     * @return
     */
    public Timestamp getLast_update() {
        return last_update;
    }

    /**
     * Method to get the creation date of the customer
     * @return
     */
    public Date getCreate_date() {
        return create_date;
    }

    /**
     * Method to get who last updated the user
     * @return
     */
    public String getLast_updated_by() {
        return last_updated_by;
    }

    /**
     * Method to get the customer ID
     * @return
     */
    public int getCustomer_id() {
        return customer_id;
    }

    /**
     * Method to get the division ID of the customer
     * @return
     */
    public int getDivision_id() {
        return division_id;
    }

    /**
     * Method to get the customer name
     * @return
     */
    public String getCustomer_name() {
        return customer_name;
    }

    /**
     * Method to set the customer ID
     * @param customer_id
     */
    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    /**
     * Method to get the customer address
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * Method to get the phone number
     * @return
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Method to get the postal code
     * @return
     */
    public String getPostal_code() {
        return postal_code;
    }

    /**
     * Method to set the customer name
     * @param customer_name
     */
    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    /**
     * Method to set the address
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Method to set the division ID
     * @param division_id
     */
    public void setDivision_id(int division_id) {
        this.division_id = division_id;
    }

    /**
     * Method to set the phone number
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Method to set the postal code
     * @param postal_code
     */
    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }
}

