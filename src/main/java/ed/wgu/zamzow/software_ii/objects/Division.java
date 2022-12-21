package ed.wgu.zamzow.software_ii.objects;

public class Division {

    private int division_id;
    private String division;
    private int country_id;

    public Division() {}

    public void setDivision_id(int division_id) {
        this.division_id = division_id;
    }

    public int getDivision_id() {
        return division_id;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public int getCountry_id() {
        return country_id;
    }
}
