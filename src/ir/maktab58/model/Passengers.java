package ir.maktab58.model;

import ir.maktab58.enums.StatusTravel;

public class Passengers extends Person{
    public Passengers() {
    }

    public Passengers(String name, String family, int userName, String phoneNumber, double balance, StatusTravel status) {
        super(name, family, userName, phoneNumber, balance, status);
    }

    public Passengers(int id, String name, String family, int userName, String phoneNumber, double balance, StatusTravel status) {
        super(id, name, family, userName, phoneNumber, balance, status);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
