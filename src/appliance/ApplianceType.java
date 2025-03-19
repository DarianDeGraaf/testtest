package appliance;

public enum ApplianceType {
    REFRIGERATOR("Refrigerator"),
    FREEZER("Freezer"),
    OVEN("Oven"),
    MICROWAVE("Microwave"),
    DISHWASHER("Dishwasher"),
    COFFEE_MAKER("Coffee Maker"),
    KETTLE("Kettle"),
    BLENDER("Blender"),
    WASHING_MACHINE("Washing Machine"),
    DRYER("Dryer"),
    VACUUM_CLEANER("Vacuum Cleaner"),
    IRON("Iron"),
    AIR_CONDITIONER("Air Conditioner"),
    HEATER("Heater"),
    FAN("Fan"),
    TELEVISION("Television"),
    SOUND_SYSTEM("Sound System"),
    GAMING_CONSOLE("Gaming Console"),
    HAIR_DRYER("Hair Dryer"),
    ELECTRIC_SHAVER("Electric Shaver"),
    ELECTRIC_TOOTHBRUSH("Electric Toothbrush"),
    ROUTER("Router"),
    SECURITY_CAMERA("Security Camera"),
    COMPUTER("Computer");

    private final String displayName;

    ApplianceType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static ApplianceType[] getAllApplianceTypes() {
        return values();
    }
}