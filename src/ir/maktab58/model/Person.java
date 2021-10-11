package ir.maktab58.model;

import ir.maktab58.enums.StatusTravel;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return userName == person.userName && Double.compare(person.balance, balance) == 0 && Objects.equals(name, person.name) && Objects.equals(family, person.family) && Objects.equals(phoneNumber, person.phoneNumber) && status == person.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, family, userName, phoneNumber, balance, status);
    }
}
