package ed.wgu.zamzow.software_ii.objects;

public class Country {

    private int country_id;
    private String country;

    public Country() {}

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public int getCountry_id() {
        return country_id;
    }

    public String getCountry() {
        return country;
    }
}
