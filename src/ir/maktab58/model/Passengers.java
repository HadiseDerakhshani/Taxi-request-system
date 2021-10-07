package ir.maktab58.model;

public class Passengers extends Person{
    public Passengers() {
    }

    public Passengers(String name, String family, int userName, String phoneNumber, double balance) {
        super(name, family, userName, phoneNumber, balance);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
