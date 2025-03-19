import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import appliance.*;

import java.util.Map;
import java.util.HashMap;


public class DatabaseService {

    static boolean validateInput(JSONObject appliance) {
        return (!invalidName(appliance) && !invalidType(appliance) && !invalidEmissions(appliance) && !invalidPowerConsumption(appliance));
    }

    private static boolean invalidName(JSONObject appliance) {
        return (!appliance.has("name") || !(appliance.get("name") instanceof String) || appliance.getString("name").trim().isEmpty());
    }
    private static boolean invalidPowerConsumption(JSONObject appliance) {
        return (!appliance.has("power_consumption_kwh") || !(appliance.get("power_consumption_kwh") instanceof Number) || appliance.getDouble("power_consumption_kwh") <= 0);
    }
    private static boolean invalidEmissions(JSONObject appliance) {
        return (!appliance.has("embodied_emissions_kgCO2e") || !(appliance.get("embodied_emissions_kgCO2e") instanceof Number) || appliance.getInt("embodied_emissions_kgCO2e") < 0);
    }
    private static boolean invalidType(JSONObject appliance) {
        return (!appliance.has("type") || isValidApplianceType(appliance.getString("type")));
    }
    private static boolean isValidApplianceType(String type) {
        try {
            ApplianceType.valueOf(type);
            return false;
        } 
        catch (IllegalArgumentException e) {
            return true;
        }
    }

    private static void printInvalidEntries(List<Integer> invalidIndexes) {
        JSONArray jsonArray = readDB();
        for (Integer invalidIndex : invalidIndexes) {
            System.out.print("invalid entry at index: " + invalidIndex + "\n" + jsonArray.get(invalidIndex) + "\n");
        }
    }

    
    static boolean validateDatabase() {
        JSONArray jsonArray = readDB();
        List<Integer> invalidIndexes = new ArrayList<>();

        if (jsonArray == null) {
            return false;
        }

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject appliance = jsonArray.getJSONObject(i);
            
            if (invalidName(appliance)) {
                invalidIndexes.add(i);
            }
            
            else if (invalidPowerConsumption(appliance)) {
                invalidIndexes.add(i);
            }
            
            else if (invalidEmissions(appliance)) {
                invalidIndexes.add(i);
            }
            
            else if (invalidType(appliance)) {
                invalidIndexes.add(i);
            }
        }
        if(!invalidIndexes.isEmpty()){
            printInvalidEntries(invalidIndexes);
            return false;
        }

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
                if (!invalidName(appliance)) {
                    if (appliance.getString("name").equals(name)) {
                        return appliance;
                    }
                }

            }
        }
        return null;
    }

    static Map<ApplianceType,List<String>> getApplianceList() {
        Map<ApplianceType, List<String>> applianceMap = new HashMap<>();

        JSONArray jsonArray = readDB();
        if (jsonArray == null) {
            return null;
        }


        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject appliance = jsonArray.getJSONObject(i);
            String name;
            ApplianceType type;
            if (!invalidName(appliance) &&  !invalidType(appliance)) {
                name = appliance.getString("name");
                type = ApplianceType.valueOf(appliance.getString("type"));
                applianceMap.putIfAbsent(type, new ArrayList<>());
                applianceMap.get(type).add(name);
            }
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
                newApplianceJson.put("type", type.toString());
                newApplianceJson.put("power_consumption_kwh", powerConsumption);
                newApplianceJson.put("embodied_emissions_kgCO2e", embodiedEmissions);

                if(validateInput(newApplianceJson)) {
                    jsonArray.put(newApplianceJson);
                    return writeDB(jsonArray);
                }
                else {
                    return false;
                }

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

}