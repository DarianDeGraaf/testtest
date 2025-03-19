package carbon_intensity;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarbonIntensityService {
    private static final String FILE_PATH = "carbon_intensity/carbon_intensity.json";

    private static Optional<JSONObject> loadJsonData() {
        try (InputStream inputStream = new FileInputStream(FILE_PATH);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            JSONTokener tokener = new JSONTokener(reader);
            return Optional.of(new JSONObject(tokener));
        } catch (IOException e) {
            System.err.println("Error loading carbon intensity data: " + e.getMessage());
            return Optional.empty();
        }
    }

    public static List<Integer> getCarbonIntensity(String country, int startTime, int endTime) {
        List<Integer> carbonIntensityValues = new ArrayList<>();

        loadJsonData().ifPresent(data -> {
            JSONObject countryData = data.optJSONObject(country);
            if (countryData != null) {
                for (int i = startTime; i < endTime; i++) {
                    carbonIntensityValues.add(countryData.optInt(String.valueOf(i), -1)); // Default -1 if missing
                }
            }
        });

        return carbonIntensityValues;
    }


    // Sample usage
    public static void main(String[] args) {
        String country = "France";
        int startTime = 0;
        int endTime = 4;

        List<Integer> carbonIntensity = getCarbonIntensity(country, startTime, endTime);
        System.out.println("Carbon Intensity for " + country + " from hour " + startTime + " to " + endTime + ":");
        System.out.println(carbonIntensity);

    }
}
