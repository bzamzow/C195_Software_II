package ed.wgu.zamzow.software_ii.objects;

import java.sql.Timestamp;
import java.util.Date;

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

    public void setLast_updated_by(String last_updated_by) {
        this.last_updated_by = last_updated_by;
    }

    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public String getCreated_by() {
        return created_by;
    }

    public Timestamp getLast_update() {
        return last_update;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public String getLast_updated_by() {
        return last_updated_by;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public int getDivision_id() {
        return division_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDivision_id(int division_id) {
        this.division_id = division_id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }
}

