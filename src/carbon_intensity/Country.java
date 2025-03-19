package carbon_intensity;

import enum_template.EnumTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public enum Country implements EnumTemplate {
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

    private final String caption;
    private static final Map<String, Country> LOOKUP_MAP = new HashMap<>();

    static {
        for (Country country : Country.values()) {
            LOOKUP_MAP.put(country.caption, country);
        }
    }

    Country(String caption) {
        this.caption = caption;
    }

    @Override
    public String getCaption() {
        return this.caption;
    }

    public static Set<String> getAllEnumCaptions() {
        return EnumTemplate.getEnumCaptions(LOOKUP_MAP);
    }

    public static Country getEnumByCaption(String caption) {
        return EnumTemplate.getEnumByCaption(LOOKUP_MAP, caption);
    }

    public static void main(String[] args) {
        System.out.println("Available Country Captions: " + Country.getAllEnumCaptions());
        Country country = Country.getEnumByCaption("Poland");
    }
}
