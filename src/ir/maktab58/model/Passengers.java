package ir.maktab58.model;

import com.sun.org.apache.xerces.internal.util.Status;
import ir.maktab58.StatusTravel;

public class Passengers extends Person{
    public Passengers() {
    }

    public Passengers(String name, String family, int userName, String phoneNumber, double balance, StatusTravel status) {
        super(name, family, userName, phoneNumber, balance, status);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
