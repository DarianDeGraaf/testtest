package database_service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class DatabaseService {

    static String DB_PATH = "src/database_service/appliance_db.json";

    public static class DataBaseType{
        final static String NAME = "name";
        final static String POWER_CONSUMPTION = "power_consumption_kwh";
        final static String TYPE = "type";
        final static String EMBODIED_EMISSION = "embodied_emissions_kgCO2e";
    }

    static boolean ValidateInput() {
        return true;
    }

    static JSONArray readDB(){
        try {
            FileReader reader = new FileReader(DB_PATH);
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
            FileWriter fileWriter = new FileWriter(DB_PATH);
            fileWriter.write(jsonArray.toString(4));
            fileWriter.close();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static JSONObject retrieveObject(String name){
        JSONArray jsonArray = readDB();
        if (jsonArray == null) {
            return null;
        }
        else {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject appliance = jsonArray.getJSONObject(i);
                if (appliance.getString(DataBaseType.NAME).equals(name)) {
                    return appliance; // Return the matching appliance
                }
            }
        }
        return null;
    }
    static boolean deleteAppliance(String name) {
        JSONObject applianceToDelete = retrieveObject(name);
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