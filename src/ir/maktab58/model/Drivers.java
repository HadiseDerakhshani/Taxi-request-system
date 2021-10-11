package ir.maktab58.model;

import ir.maktab58.enums.StatusTravel;
import ir.maktab58.enums.VehiclesType;
import ir.maktab58.dataBaseAccess.VehicleDBAccess;

import java.sql.SQLException;

public class Drivers extends Person {
    private Vehicles vehicles;
    private String location;


    public Drivers() {
    }

    public Drivers(String name, String family, int userName, String phoneNumber, double balance,
                   StatusTravel status, Vehicles vehicles,String location) {
        super(name, family, userName, phoneNumber, balance, status);
        this.vehicles = vehicles;
        this.location=location;
    }

    public Drivers(String name, String family, int userName, String phoneNumber, double balance, StatusTravel status) {
        super(name, family, userName, phoneNumber, balance, status);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
           vehicles.setId(vehicleDBAccess.save(vehicles));
            return vehicles;

        } else
            return null;
    }

    @Override
    public String toString() {
        return  "vehicles" +vehicles.getType().getType() +" , "+
                "location" + getLocation()+" , "+
                super.toString();

    }
}
