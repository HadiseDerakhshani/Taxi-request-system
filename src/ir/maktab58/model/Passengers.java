package ir.maktab58.model;

import ir.maktab58.enums.StatusTravel;

public class Passengers extends Person{
    public Passengers() {
    }

    public Passengers(String string, String resultSetString, int anInt, String setString, int resultSetInt) {
    }

    public Passengers(int id, String name, String family, int userName,
                      String phoneNumber, int balance, StatusTravel status) {
        super(id, name, family, userName, phoneNumber, balance, status);
    }

    public Passengers(String name, String family, int userName, String phoneNumber, int balance, StatusTravel waiting) {
        super(name, family, userName, phoneNumber, balance);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
