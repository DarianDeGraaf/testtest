import org.json.JSONObject;
import org.json.JSONArray;

import java.util.*;

public class ApplianceService {

    public static Appliance retrieveAppliance(String name) {
        JSONObject object = DatabaseService.retrieveObject(name);

        if (object != null) {
            String applianceName = object.getString(DatabaseService.DataBaseType.NAME);
            ApplianceType applianceType = ApplianceType.getEnumByCaption(object.getString(DatabaseService.DataBaseType.TYPE));
            float appliancePC = object.getFloat(DatabaseService.DataBaseType.POWER_CONSUMPTION);
            int embodiedEmissions = object.getInt(DatabaseService.DataBaseType.EMBODIED_EMISSION);

            return new Appliance(applianceName, applianceType, appliancePC, embodiedEmissions);
        }
        return null;
    }

    public static Map<ApplianceType, List<String>> getApplianceList() {
        Map<ApplianceType, List<String>> applianceMap = new HashMap<>();

        JSONArray jsonArray = DatabaseService.readDB();
        if (jsonArray == null) {
            return null;
        }

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject appliance = jsonArray.getJSONObject(i);
            String name = appliance.getString(DatabaseService.DataBaseType.NAME);
            // In database String value are Enum Equivalent not Caption
            ApplianceType type = ApplianceType.valueOf(appliance.getString(DatabaseService.DataBaseType.TYPE));
            applianceMap.putIfAbsent(type, new ArrayList<>());
            applianceMap.get(type).add(name);
        }

        return applianceMap;
    }

    public static boolean addAppliance(String name, ApplianceType type, float powerConsumption, int embodiedEmissions) {
        // Additional input validation can go here
        JSONArray jsonArray = DatabaseService.readDB();
        if (jsonArray == null) {
            return false;
        }
        if (DatabaseService.retrieveObject(name) != null) {
            return false;
        }
        JSONObject newApplianceJson = new JSONObject();
        newApplianceJson.put(DatabaseService.DataBaseType.NAME, name);
        newApplianceJson.put(DatabaseService.DataBaseType.TYPE, type.toString());
        newApplianceJson.put(DatabaseService.DataBaseType.POWER_CONSUMPTION, powerConsumption);
        newApplianceJson.put(DatabaseService.DataBaseType.EMBODIED_EMISSION, embodiedEmissions);

        jsonArray.put(newApplianceJson);
        return DatabaseService.writeDB(jsonArray);
    }

    public static boolean updateAppliance(String name, ApplianceType type, float powerConsumption, int embodiedEmissions) {
        JSONArray jsonArray = DatabaseService.readDB();
        if (jsonArray == null) {
            return false;
        }

        boolean applianceFound = false;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject appliance = jsonArray.getJSONObject(i);
            if (appliance.getString(DatabaseService.DataBaseType.NAME).equals(name)) {
                appliance.put(DatabaseService.DataBaseType.TYPE, type.toString());
                appliance.put(DatabaseService.DataBaseType.POWER_CONSUMPTION, powerConsumption);
                appliance.put(DatabaseService.DataBaseType.EMBODIED_EMISSION, embodiedEmissions);
                applianceFound = true;
                break;
            }
        }
        return applianceFound && DatabaseService.writeDB(jsonArray);
    }

    public static boolean deleteAppliance(String name) {
        return DatabaseService.deleteAppliance(name);
    }


    public static void print(Map<ApplianceType, List<String>> map){
        for (Map.Entry<ApplianceType, List<String>> entry: map.entrySet()){
            System.out.print(entry.getKey());
            System.out.print(entry.getValue());
            System.out.println();
        }

    }


    public static void main(String[] args) {
        ApplianceType t = ApplianceType.BLENDER;
        System.out.println(ApplianceService.getApplianceList().get(t));
    }
}

