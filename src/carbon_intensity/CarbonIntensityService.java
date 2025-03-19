package data_files.carbon_intensity;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonArray;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CarbonIntensityService {
    // This method loads the JSON data from a file using Gson
    private static JsonObject loadJsonData(String filePath) throws IOException {
        Gson gson = new Gson();
        FileReader reader = new FileReader(filePath);
        return gson.fromJson(reader, JsonObject.class);
    }

    // This method fetches the carbon intensity values for a given country and time frame
    public static List<Integer> getCarbonIntensity(String country, int startTime, int endTime, String filePath) {
        List<Integer> carbonIntensityValues = new ArrayList<>();
        try {
            // Load the JSON data
            JsonObject data = loadJsonData(filePath);

            // Get the carbon intensity data for the given country
            JsonObject countryData = data.getAsJsonObject(country);
            
            // Iterate through the specified time range and collect the carbon intensity values
            for (int i = startTime; i <= endTime; i++) {
                String hourKey = String.valueOf(i);
                if (countryData.has(hourKey)) {
                    JsonElement intensityElement = countryData.get(hourKey);
                    carbonIntensityValues.add(intensityElement.getAsInt());
                } else {
                    carbonIntensityValues.add(-1);  // If no data for the hour, add a default value (-1 or some other placeholder)
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return carbonIntensityValues;
    }

    // Sample usage
    public static void main(String[] args) {
        String country = "FR";  // Country code
        int startTime = 8;           // Start hour (e.g., 8 AM)
        int endTime = 14;            // End hour (e.g., 2 PM)
        String filePath = "carbon_intensity.json";  // Path to your JSON file

        List<Integer> carbonIntensity = getCarbonIntensity(country, startTime, endTime, filePath);
        
        // Print out the result
        System.out.println("Carbon Intensity for " + country + " from hour " + startTime + " to " + endTime + ":");
        for (Integer intensity : carbonIntensity) {
            System.out.println(intensity + " gCO2/kWh");
        }
    }
}
