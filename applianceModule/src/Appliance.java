/**
 * This class represents an appliance with properties like name, type,
 * power consumption, and embodied emissions.

 * immutability: Fields are private final, and no setters are provided.
 * Encapsulation: Properties are accessed via getters only.
 *
 */
public class Appliance {
    private final String name;
    private final ApplianceType applianceType;
    private final float powerConsumption;
    private final int embodiedEmissions;

    public Appliance(String name, ApplianceType type, float powerConsumption, int embodiedEmissions) {
        this.name = name;
        this.applianceType = type;
        this.powerConsumption = powerConsumption;
        this.embodiedEmissions = embodiedEmissions;
    }

    public String getName() {
        return name;
    }

    public ApplianceType getType() {
        return applianceType;
    }

    public float getPowerConsumption() {
        return powerConsumption;
    }

    public int getEmbodiedEmissions() {
        return embodiedEmissions;
    }

    @Override
    public String toString() {
        return String.format("Appliance{name='%s', type=%s, powerConsumption=%.2f, embodiedEmissions=%d}",
                name, applianceType, powerConsumption, embodiedEmissions);
    }
}
