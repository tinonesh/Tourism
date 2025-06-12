public class Destination {
    private String country;
    private String city;
    private String description;

    public Destination(String country, String city, String description) {
        this.country = country;
        this.city = city;
        this.description = description;
    }

    public String getCountry() { return country; }
    public String getCity() { return city; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return country + ", " + city + " - " + description;
    }
}
