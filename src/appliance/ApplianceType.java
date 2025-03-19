package appliance;

import enum_template.EnumTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum ApplianceType implements EnumTemplate{
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

    private final String caption;
    private static final Map<String, ApplianceType> LOOKUP_MAP = new HashMap<>();

    static {
        for (ApplianceType applianceType : ApplianceType.values()) {
            LOOKUP_MAP.put(applianceType.caption, applianceType);
        }
    }
    ApplianceType(String caption) {
        this.caption = caption;
    }

    @Override
    public String getCaption() {
        return this.caption;
    }
    public static Set<String> getAllEnumCaptions() {
        return EnumTemplate.getEnumCaptions(LOOKUP_MAP);
    }

    public static ApplianceType getEnumByCaption(String caption) {
        return EnumTemplate.getEnumByCaption(LOOKUP_MAP, caption);
    }

    public static void main(String[] args) {
        System.out.println(ApplianceType.getAllEnumCaptions());
        ApplianceType app1 = ApplianceType.getEnumByCaption("Hair Dryer");
        System.out.println(app1.caption);
    }
}