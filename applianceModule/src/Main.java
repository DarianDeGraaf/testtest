public class Main {
    public static void main(String[] args){

//        //GET APPLIANCE MAP(APPLIANCE TYPE, NAME)
//        Map<ApplianceType, List<String>> applianceMap = new HashMap<>();
//        applianceMap = DatabaseService.getApplianceList();
//        System.out.print(applianceMap);

        String name =  "Nest Cam IQ Security Camera";
        Appliance test;
        test = ApplianceService.retrieveAppliance(name);
//        System.out.println(test);
        System.out.println(test.getName());




////         ADD APPLIANCE
        name = "Test Appliance";
        ApplianceType type = ApplianceType.valueOf("ROUTER");
        float powerConsumption = 1.9f;
        int embodiedEmissions = 30;

        if (ApplianceService.addAppliance(name, type, powerConsumption, embodiedEmissions)) {
            test = ApplianceService.retrieveAppliance(name);
            System.out.print(test + "\n");
        }
        else {
            System.out.print("appliance already in db\n");
        }


////        DELETE APPLIANCE
        name = "asdasfasd";
        if (ApplianceService.deleteAppliance(name)) {
            System.out.print("appliance deleted successfully\n");
        }
        else {
            System.out.print("appliance not found\n");
        }


//        UPDATE DB
        name = "Test Appliance";
        type = ApplianceType.valueOf("BLENDER");
        powerConsumption = 2.9f;
        embodiedEmissions = 20;
        if(ApplianceService.updateAppliance(name, type, powerConsumption, embodiedEmissions)){
            System.out.print("appliance updated successfully\n");
        }
        else {
            System.out.print("appliance not found\n");
        }

    }
}
