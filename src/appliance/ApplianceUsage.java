package appliance;

import carbon_intensity.CarbonIntensityService;
import carbon_intensity.Country;
import database_service.ApplianceService;
import room.Room;

import java.util.Comparator;
import java.util.List;


public class ApplianceUsage {
    private Appliance appliance;
    private int startTime;
    private int endTime;
    private List<Integer> carbonIntensity;
    private Room room;
    private int carbonFootprint;
    private boolean alwaysOn;


    public ApplianceUsage(String name, Room room, Country country, boolean alwaysOn, int start, int end) {
        this.appliance = ApplianceService.retrieveAppliance(name);
        this.room = room;
        this.alwaysOn = alwaysOn;
        this.startTime = start;
        this.endTime = end;
        this.carbonIntensity = CarbonIntensityService.getCarbonIntensity(country.getCaption(),start,end);
    }
    public ApplianceUsage(String name, String room, boolean alwaysOn, Country country) {
        this.appliance = ApplianceService.retrieveAppliance(name);
        this.room = Room.valueOf(room);
        this.alwaysOn = alwaysOn;
        setTimes(alwaysOn, 0, 0);
        this.carbonIntensity = CarbonIntensityService.getCarbonIntensity(country.getCaption(),this.startTime, this.endTime);
    }

    // ensuring immutability of the object
    public Appliance getAppliance(){
        return new Appliance(this.appliance.getName(),
                this.appliance.getType(),
                this.appliance.getPowerConsumption(),
                this.appliance.getEmbodiedEmissions());
    }

    public void setTimes(boolean alwaysOn, int startTime, int endTime){
        if(alwaysOn){
            this.startTime = 0;
            this.endTime = 24;
            this.alwaysOn = true;
        }
        else{
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }
    public int getStartTime(){
        return this.startTime;
    }

    public int getEndTime(){
        return this.endTime;
    }
    public int getTimeRange(){
        return this.endTime-this.startTime;
    }
    public boolean getUsageMode(){
        return alwaysOn;
    }

    private void calculateCarbonFootPrint(){
        //TODO
        // Is carbon intensity calculated correctly?
        this.carbonFootprint = (int) (this.appliance.getEmbodiedEmissions()+
                        this.appliance.getPowerConsumption() * this.getTimeRange()+
                        this.carbonIntensity.stream().mapToInt(k->k).sum());
    }

    public int getCarbonFootprint(){
        calculateCarbonFootPrint();
        return this.carbonFootprint;
    }

    private Room getRoom(){
        return this.room;
    }

    // Comparator based on time range
    public static final Comparator<ApplianceUsage> COMPARE_BY_TIME = (a1, a2) -> Integer.compare(a1.getTimeRange(), a2.getTimeRange());

    // Comparator based on carbon footprint
    public static final Comparator<ApplianceUsage> COMPARE_BY_CARBON_FOOTPRINT =
            new Comparator<ApplianceUsage>() {
                @Override
                public int compare(ApplianceUsage a1, ApplianceUsage a2) {
                   return Integer.compare(a1.getCarbonFootprint(), a2.getCarbonFootprint());}
    };





}
