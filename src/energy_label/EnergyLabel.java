package energy_label;

import enum_template.EnumTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum EnergyLabel implements EnumTemplate{
    A_PLUS_PLUS(0.7, "A++"),
    A_PLUS(0.75, "A+"),
    A(0.85, "A"),
    B(1.0, "B"),
    C(1.2, "C"),
    D(1.4, "D"),
    E(1.6, "E"),
    F(1.8, "F"),
    G(2.0, "G");

    private final double efficiencyFactor;
    private final String caption;
    private static final Map<String, EnergyLabel> LOOKUP_MAP = new HashMap<>();

    static {
        for (EnergyLabel energyLabel : EnergyLabel.values()) {
            LOOKUP_MAP.put(energyLabel.caption, energyLabel);
        }
    }

    EnergyLabel(double efficiencyFactor, String caption) {
        this.efficiencyFactor = efficiencyFactor;
        this.caption = caption;
    }
    @Override
    public String getCaption() {
        return this.caption;
    }
    public double getEfficiencyFactor() {
        return efficiencyFactor;
    }
    public static Set<String> getAllEnumCaptions() {
        return EnumTemplate.getEnumCaptions(LOOKUP_MAP);
    }
    public static EnergyLabel getEnumByCaption(String caption) {
        return EnumTemplate.getEnumByCaption(LOOKUP_MAP, caption);
    }

    public static void main(String[] args) {
        System.out.println(EnergyLabel.getAllEnumCaptions());
        EnergyLabel el = EnergyLabel.getEnumByCaption("A++");
        System.out.println(el.caption);
        System.out.println(el.getEfficiencyFactor());
        EnergyLabel a = EnergyLabel.C;
    }


}