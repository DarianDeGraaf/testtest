package data_files.carbon_intensity;
public enum Country {
    DE("Germany"),
    FR("France"),
    IT("Italy"),
    ES("Spain"),
    NL("Netherlands"),
    BE("Belgium"),
    SE("Sweden"),
    AT("Austria"),
    PL("Poland"),
    DK("Denmark");

    private final String countryName;

    // Constructor for the enum constants
    Country(String countryName) {
        this.countryName = countryName;
    }

    // Getter for the human-readable country name
    public String getCountryName() {
        return countryName;
    }
}
