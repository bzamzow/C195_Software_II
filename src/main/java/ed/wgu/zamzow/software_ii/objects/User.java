package ed.wgu.zamzow.software_ii.objects;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Class to store user object information
 *
 * @author Bret Zamzow
 */
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

    /**
     * Method to instantiate the object using pre-set data
     * @param user_id
     * @param user_name
     * @param password
     * @param create_date
     * @param created_by
     * @param last_update
     * @param last_updated_by
     */
    public User(int user_id, String user_name, String password, Date create_date, String created_by, Timestamp last_update, String last_updated_by) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.password = password;
        this.create_date = create_date;
        this.created_by = created_by;
        this.last_update = last_update;
        this.last_updated_by = last_updated_by;
    }

    /**
     * Method to get the user ID
     * @return
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * Method to get the create date
     * @return
     */
    public Date getCreate_date() {
        return create_date;
    }

    /**
     * Method to get who created the user
     * @return
     */
    public String getCreated_by() {
        return created_by;
    }

    /**
     * Method to get who last updated the user
     * @return
     */
    public String getLast_updated_by() {
        return last_updated_by;
    }

    /**
     * Method to get the user name
     * @return
     */
    public String getUser_name() {
        return user_name;
    }

    /**
     * Method to get the password
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method to get the last time the user was updated
     * @return
     */
    public Timestamp getLast_update() {
        return last_update;
    }

    /**
     * Method to set when the user was created
     * @param create_date
     */
    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    /**
     * Method to set the user ID
     * @param user_id
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    /**
     * Method to set who created the user
     * @param created_by
     */
    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    /**
     * Method to set the user name
     * @param user_name
     */
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    /**
     * Method to set who last updated the user
     * @param last_updated_by
     */
    public void setLast_updated_by(String last_updated_by) {
        this.last_updated_by = last_updated_by;
    }

    /**
     * Method to set what the password is
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method to set the last time the user was updated
     * @param last_update
     */
    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }
}
