import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import appliance.*;
import com.google.gson.JsonSyntaxException;
import java.util.Map;
import java.util.HashMap;

public class DatabaseService {

    static boolean ValidateInput() {
        return true;
    }

    static JSONArray readDB(){
        try {
            FileReader reader = new FileReader("src/appliance_db.json");
            JSONArray jsonArray = new JSONArray(new JSONTokener(reader));
            reader.close();
            return jsonArray;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    static boolean writeDB(JSONArray jsonArray){
        try {
            FileWriter fileWriter = new FileWriter("src/appliance_db.json");
            fileWriter.write(jsonArray.toString(4));
            fileWriter.close();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    static JSONObject retrieveAppliance(String name){
        JSONArray jsonArray = readDB();
        if (jsonArray == null) {
            return null;
        }
        else {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject appliance = jsonArray.getJSONObject(i);
                if (appliance.getString("name").equals(name)) {
                    return appliance; // Return the matching appliance
                }
            }
        }
        return null;
    }

    static Map<appliance.ApplianceType,List<String>> getApplianceList() {
        Map<ApplianceType, List<String>> applianceMap = new HashMap<>();

        JSONArray jsonArray = readDB();
        if (jsonArray == null) {
            return null;
        }


        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject appliance = jsonArray.getJSONObject(i);
            String name = appliance.getString("name");
            ApplianceType type = ApplianceType.valueOf(appliance.getString("type"));

            applianceMap.putIfAbsent(type, new ArrayList<>());
            applianceMap.get(type).add(name);
        }

        return applianceMap;
    }

    static boolean addAppliance(String name, ApplianceType type, float powerConsumption, int embodiedEmissions) {
        JSONArray jsonArray = readDB();
        if (jsonArray == null) {
            return false;
        }
        else {
            JSONObject newApplianceJson = new JSONObject();
            if (retrieveAppliance(name) != null) {
                return false;
            }
            else {
                newApplianceJson.put("name", name);
                newApplianceJson.put("type", type);
                newApplianceJson.put("power_consumption_kwh", powerConsumption);
                newApplianceJson.put("embodied_emissions_kgCO2e", embodiedEmissions);

                jsonArray.put(newApplianceJson);
                return writeDB(jsonArray);
            }
        }
    }

    static boolean updateAppliance(String name, ApplianceType type, float powerConsumption, int embodiedEmissions ) {
        JSONArray jsonArray = readDB();
        if (jsonArray == null) {
            return false;
        }

        boolean applianceFound = false;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject appliance = jsonArray.getJSONObject(i);

            if (appliance.getString("name").equals(name)) {
                appliance.put("type", type.toString());
                appliance.put("power_consumption_kwh", powerConsumption);
                appliance.put("embodied_emissions_kgCO2e", embodiedEmissions);
                applianceFound = true;
                break;
            }
        }

        if (applianceFound) {
            return writeDB(jsonArray);
        }
        else {
            return false;
        }
    }

    static boolean deleteAppliance(String name) {
        JSONObject applianceToDelete = retrieveAppliance(name);
        if (applianceToDelete == null) {
            return false;
        }

        JSONArray jsonArray = readDB();
        if (jsonArray == null) {
            return false;
        }

        else {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject appliance = jsonArray.getJSONObject(i);
                if (appliance.getString("name").equals(name)) {
                    jsonArray.remove(i);
                    break;
                }
            }
            return writeDB(jsonArray);
        }
    }
/*
    static void printDB(List<Appliance> appliances){
        for (Appliance appliance : appliances) {
            System.out.println("Name: " + appliance.getName());
            System.out.println("Type: " + appliance.getType());
            System.out.println("Power Consumption (kWh): " + appliance.getPowerConsumption());
            System.out.println("Embodied Emissions (kgCO2e): " + appliance.getEmbodiedEmissions());
            System.out.println("\n");
        }
    }*/
}