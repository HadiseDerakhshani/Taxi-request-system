package ir.maktab58;

public enum VehiclesType {
    CAR("Car"),
    MOTOR("Motor"),
    VANET("Vanet"),
    VAN("Van");
    String type;

    VehiclesType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
