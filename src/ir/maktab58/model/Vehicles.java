package ir.maktab58.model;

import ir.maktab58.VehiclesType;

public class Vehicles {
    private int id;
    private String name;
    private int model;
    private String color;
    private String plateNumber;
    private VehiclesType type;
    public Vehicles() {
    }

    public int getId() {
        return id;
    }

    public Vehicles(int id, String name, int model, String color, String plateNumber, VehiclesType type) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.color = color;
        this.plateNumber = plateNumber;
        this.type = type;
    }

    public Vehicles(String name, int model, String color, String plateNumber, VehiclesType type) {
        this.name = name;
        this.model = model;
        this.color = color;
        this.plateNumber = plateNumber;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public VehiclesType getType() {
        return type;
    }

    public void setType(VehiclesType type) {
        this.type = type;
    }
}
