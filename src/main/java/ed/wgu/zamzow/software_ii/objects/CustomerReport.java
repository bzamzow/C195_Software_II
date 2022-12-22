package ed.wgu.zamzow.software_ii.objects;

/**
 * Class to hold customer report information from the database
 *
 * @author Bret Zamzow
 */
public class CustomerReport {

    private String customerName;
    private int count;
    private String appointmentType;

    /**
     * Method to return the count
     * @return
     */
    public int getCount() {
        return count;
    }

    /**
     * Method to return the customer name
     * @return
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Method to set the count
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Method to set the customer name
     * @param customerName
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Method to get the appointment type
     * @return
     */
    public String getAppointmentType() {
        return appointmentType;
    }

    /**
     * Method to set the appointment type
     * @param appointmentType
     */
    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }
}
