public enum EnergyLabel {
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

    EnergyLabel(double efficiencyFactor, String caption) {
        this.efficiencyFactor = efficiencyFactor;
        this.caption = caption;
    }

    public double getEfficiencyFactor() {
        return efficiencyFactor;
    }

    public String getCaption() {
        return caption;
    }
}