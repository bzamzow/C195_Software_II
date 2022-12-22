package ed.wgu.zamzow.software_ii.objects;

public class CustomerReport {

    private String customerName;
    private int count;
    private String appointmentType;

    public int getCount() {
        return count;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }
}
