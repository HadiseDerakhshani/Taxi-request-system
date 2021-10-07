package ir.maktab58.model;

import ir.maktab58.VehiclesType;
import ir.maktab58.dataBaseAccess.VehicleDBAccess;

import java.sql.SQLException;

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

    public Vehicles addVehicles(String vehiclesType, String name, int model, String color, String plateNumber) throws SQLException, ClassNotFoundException {
        if (vehiclesType.equals(VehiclesType.CAR.getType())) {
            Vehicles vehicles = new Vehicles(name, model, color, plateNumber, VehiclesType.CAR);
            VehicleDBAccess vehicleDBAccess = new VehicleDBAccess();
            vehicleDBAccess.save(vehicles);
            return vehicles;

        } else
            return null;
    }

    @Override
    public String toString() {
        return
                super.toString();

    }
}
