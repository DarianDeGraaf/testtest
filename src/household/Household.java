package household;

import appliance.ApplianceUsage;
import carbon_intensity.Country;
import energy_label.EnergyLabel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Household {
    private Country country;
    private List<ApplianceUsage> appliances;
    private EnergyLabel energyLabel;

    public Household(Country country, EnergyLabel energyLabel) {
        if (country == null || energyLabel == null) {
            throw new IllegalArgumentException("Country and EnergyLabel cannot be null");
        }
        this.country = country;
        this.energyLabel = energyLabel;
        this.appliances = new ArrayList<>();
    }

    public void addAppliance(ApplianceUsage applianceUsage) {
        if (applianceUsage != null) {
            appliances.add(applianceUsage);
        }
    }

    public void setCountry(Country newCountry){
        this.country = newCountry;
    }
    public void setEnergyLabel(EnergyLabel energyLabel){
        this.energyLabel = energyLabel;
    }

    public EnergyLabel getEnergyLabel(){
        return this.energyLabel;
    }

    public void removeAppliance(ApplianceUsage applianceUsage){
        this.appliances.remove(applianceUsage);
    }

    public List<ApplianceUsage> sortByCarbonFootprint() {
        return sortAppliances(ApplianceUsage.COMPARE_BY_CARBON_FOOTPRINT);
    }

    public List<ApplianceUsage> sortByTime() {
        return sortAppliances(ApplianceUsage.COMPARE_BY_TIME);
    }

    private List<ApplianceUsage> sortAppliances(Comparator<ApplianceUsage> comparator) {
        List<ApplianceUsage> sortedList = new ArrayList<>(appliances);
        sortedList.sort(comparator);
        return sortedList;
    }
}
