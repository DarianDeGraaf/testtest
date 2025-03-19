import com.google.gson.Gson;
import org.json.JSONObject;
import appliance.*;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args){
        new HomePage();

        DatabaseService.validateDatabase();


        //GET APPLIANCE MAP(APPLIANCE TYPE, NAME)
        Map<ApplianceType, List<String>> applianceMap = new HashMap<>();
        applianceMap = DatabaseService.getApplianceList();
        System.out.print(applianceMap);

        String name =  "Nest Cam IQ Security Camera";
        JSONObject test;
        test = DatabaseService.retrieveAppliance(name);
        System.out.print("\n");
        System.out.print(test);
        System.out.print("\n");


        // ADD APPLIANCE
        name = "";
        ApplianceType type = appliance.ApplianceType.valueOf("ROUTER");
        float powerConsumption = 1.9f;
        int embodiedEmissions = 30;

        if (DatabaseService.addAppliance(name, type, powerConsumption, embodiedEmissions)) {
            test = DatabaseService.retrieveAppliance(name);
            System.out.print(test + "\n");
        }
        else {
            System.out.print("add appliance failed\n");
        }

        //DELETE APPLIANCE
        name = "";
        if (DatabaseService.deleteAppliance(name)) {
            System.out.print("appliance deleted successfully\n");
        }
        else {
            System.out.print("appliance not found\n");
        }


        //UPDATE DB
        name = "Test Appliance";
        type = appliance.ApplianceType.valueOf("BLENDER");
        powerConsumption = 2.9f;
        embodiedEmissions = 20;
        if(DatabaseService.updateAppliance(name, type, powerConsumption, embodiedEmissions)){
            System.out.print("appliance updated successfully\n");
        }
        else {
            System.out.print("appliance not found\n");
        }

    }
}
