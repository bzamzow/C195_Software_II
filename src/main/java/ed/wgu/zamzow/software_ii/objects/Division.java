package ed.wgu.zamzow.software_ii.objects;

/**
 * Class to hold division information
 *
 * @author Bret Zamzow
 */
public class Division {

    private int division_id;
    private String division;
    private int country_id;

    public Division() {}

    /**
     * Method to set the division ID
     * @param division_id
     */
    public void setDivision_id(int division_id) {
        this.division_id = division_id;
    }

    /**
     * Method to get the division ID
     * @return
     */
    public int getDivision_id() {
        return division_id;
    }

    /**
     * Method to get the division name
     * @return
     */
    public String getDivision() {
        return division;
    }

    /**
     * Method to set the division name
     * @param division
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * Method to set the country ID
     * @param country_id
     */
    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    /**
     * Method to get the country ID
     * @return
     */
    public int getCountry_id() {
        return country_id;
    }
}
