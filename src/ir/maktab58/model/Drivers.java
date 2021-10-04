package ir.maktab58.model;

import ir.maktab58.VehiclesType;

public class Drivers extends Person {
    private Vehicles vehicles;
    public Drivers() {
    }

    public Drivers(String name, String family, int userName, String phoneNumber, double balance) {
        super(name, family, userName, phoneNumber, balance);
    }

    public Drivers(String name, String family, int userName, String phoneNumber, double balance, Vehicles vehicles) {
        super(name, family, userName, phoneNumber, balance);
        this.vehicles = vehicles;
    }

    public Vehicles getVehicles() {
        return vehicles;
    }

    public void setVehicles(Vehicles vehicles) {
        this.vehicles = vehicles;
    }
}
