package ed.wgu.zamzow.software_ii.objects;

/**
 * Object ot hold country data
 *
 * @author Bret Zamzow
 */
public class Country {

    private int country_id;
    private String country;

    public Country() {}

    /**
     * Method to set the country
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
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

    /**
     * Method to get the country name
     * @return
     */
    public String getCountry() {
        return country;
    }
}
