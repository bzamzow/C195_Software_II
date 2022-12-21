package ed.wgu.zamzow.software_ii.objects;

import java.sql.Date;
import java.sql.Timestamp;

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

    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public void setCust_id(int cust_id) {
        this.cust_id = cust_id;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }

    public void setLast_updated_by(String last_updated_by) {
        this.last_updated_by = last_updated_by;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setAppointment_id(int appointment_id) {
        this.appointment_id = appointment_id;
    }

    public void setCont_name(String cont_name) {
        this.cont_name = cont_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public int getContact_id() {
        return contact_id;
    }

    public int getCust_id() {
        return cust_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getCreated_by() {
        return created_by;
    }

    public String getDesc() {
        return desc;
    }

    public String getLast_updated_by() {
        return last_updated_by;
    }

    public String getLoc() {
        return loc;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public Timestamp getLast_update() {
        return last_update;
    }

    public String getCont_name() {
        return cont_name;
    }

    public String getCust_name() {
        return cust_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public int getAppointment_id() {
        return appointment_id;
    }
}
