package appliance;

public class ApplianceUsage {
    private Appliance appliance;
    private int startTime;
    private int endTime;
    //private Array[Int] carbonIntensity; TODO
    //private RoomType roomtype; TODO
    private int carbonFootprint;
    private boolean alwaysOn;


    public void setAppliance(Appliance appliance){
        this.appliance =  appliance;
    }

    public Appliance getAppliance(){
        return appliance;
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
        return startTime;
    }

    public int getEndTime(){
        return endTime;
    }

    public boolean getUsageMode(){
        return alwaysOn;
    }

    //TODO carbon intensity and roomtype getters/setters

}
