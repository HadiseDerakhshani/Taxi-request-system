package ir.maktab58.model;

import ir.maktab58.enums.StatusTravel;

import java.util.Objects;

public class Person {
    private int id;
    private String name;
    private String family;
    private int userName;
    private String phoneNumber;
    private int balance;
    private StatusTravel status;
    public Person() {
    }

    public Person(String name, String family, int userName, String phoneNumber, int balance) {
        this.name = name;
        this.family = family;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }

    public Person(int id, String name, String family, int userName,
                  String phoneNumber, int balance, StatusTravel status) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
        this.status = status;
    }

    public Person(String name, String family, int userName, String phoneNumber, int balance, StatusTravel status) {
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

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return id == person.id && userName == person.userName && balance == person.balance &&
                Objects.equals(name, person.name) && Objects.equals(family, person.family) &&
                Objects.equals(phoneNumber, person.phoneNumber) && status == person.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, family, userName, phoneNumber, balance, status);
    }
}
