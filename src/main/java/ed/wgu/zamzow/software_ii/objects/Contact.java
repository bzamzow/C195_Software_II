package ed.wgu.zamzow.software_ii.objects;

/**
 * Object to store contact information
 *
 * @author Bret Zamzow
 */
public class Contact {
    private int contact_id;
    private String contact_name;
    private String email;

    public Contact() {}

    /**
     * Method to get the contact ID
     * @return
     */
    public int getContact_id() {
        return contact_id;
    }

    /**
     * Method to set the contact ID
     * @param contact_id
     */
    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }

    /**
     * Method to get the contact name
     * @return
     */
    public String getContact_name() {
        return contact_name;
    }

    /**
     * Method to set the contact name
     * @param contact_name
     */
    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    /**
     * Method to get the email
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method to set the email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
