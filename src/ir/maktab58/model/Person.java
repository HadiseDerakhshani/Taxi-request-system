package ir.maktab58.model;

import com.sun.org.apache.xerces.internal.util.Status;
import ir.maktab58.StatusTravel;

public class Person {
    private String name;
    private String family;
    private int userName;
    private String phoneNumber;
    private double balance;
    private StatusTravel status;
    public Person() {
    }

    public Person(String name, String family, int userName, String phoneNumber, double balance, StatusTravel status) {
        this.name = name;
        this.family = family;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
        this.status = status;
    }

    public StatusTravel getStatus() {
        return status;
    }

    public void setStatus(StatusTravel status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public int getUserName() {
        return userName;
    }

    public void setUserName(int userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", userName=" + userName +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", balance=" + balance +
                ", status=" + status +
                '}';
    }
}
