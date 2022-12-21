package ed.wgu.zamzow.software_ii.objects;

import java.sql.Timestamp;
import java.util.Date;

public class User {
    private int user_id;
    private String user_name;
    private String password;
    private Date create_date;
    private String created_by;
    private java.sql.Timestamp last_update;
    private String last_updated_by;

    public User() {
    }

    public User(int user_id, String user_name, String password, Date create_date, String created_by, Timestamp last_update, String last_updated_by) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.password = password;
        this.create_date = create_date;
        this.created_by = created_by;
        this.last_update = last_update;
        this.last_updated_by = last_updated_by;
    }

    public int getUser_id() {
        return user_id;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public String getCreated_by() {
        return created_by;
    }

    public String getLast_updated_by() {
        return last_updated_by;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getPassword() {
        return password;
    }

    public Timestamp getLast_update() {
        return last_update;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setLast_updated_by(String last_updated_by) {
        this.last_updated_by = last_updated_by;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }
}
