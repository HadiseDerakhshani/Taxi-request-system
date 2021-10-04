package ir.maktab58;

import ir.maktab58.dataBaseAccess.DBAccess;
import ir.maktab58.dataBaseAccess.DriverDBAccess;
import ir.maktab58.dataBaseAccess.PassengerDBAccess;
import ir.maktab58.model.Drivers;
import ir.maktab58.model.Passengers;
import ir.maktab58.model.Vehicles;

import java.sql.Driver;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static   Drivers drivers=new Drivers();
    public static   Vehicles vehicles=new Vehicles();
    public static Passengers passengers=new Passengers();
    // public static DriverDBAccess driverDBAccess=new DriverDBAccess();
    public static void main(String[] args) {




    }
    public static void menu(){
        System.out.println("1.Add a group of drivers \n" +
                "2.Add a group of passengers \n" +
                "3.Driver signup or login \n" +
                "4.Passenger signup or login \n" +
                "5.Show a list of drivers \n" +
                "6.Show a list of passengers\n" +
                "7.Exit ");
    }
    public static void  addDriver() throws SQLException, ClassNotFoundException {
         Scanner scanner=new Scanner(System.in);
        String vehiclesType;
        System.out.println("enter name & family & userName & phoneNumber & balance :");
        String name=scanner.next();
        String family=scanner.next();
        int id=scanner.nextInt();
        String phone=scanner.next();
        double balance=scanner.nextDouble();
        System.out.println("enter vehicles 1.car   2.motor    3.vanet    4.van");
        switch (scanner.nextInt()){
            case 1:
                vehiclesType="Car";
                System.out.println("enter name & model & color & plateNumber of Car");
                vehicles=drivers.addVehicles(vehiclesType,scanner.next(), scanner.nextInt(),
                        scanner.next(), scanner.next());
                break;
            case 2:
                vehiclesType="Motor";
                break;
            case 3:
                vehiclesType="Vanet";
                break;
            case 4:
                vehiclesType="Van";
                break;

        }

        if(vehicles!=null){
            drivers=new Drivers(name,family,id,phone,balance,vehicles);
          DriverDBAccess driverDBAccess=new DriverDBAccess();
         int check= driverDBAccess.save(drivers);
        }

    }
    public static void addPassengers() throws SQLException, ClassNotFoundException {
        Scanner scanner=new Scanner(System.in);
        PassengerDBAccess passengerDBAccess=new PassengerDBAccess();
        System.out.println("enter  name & family & userName & phoneNumber & balance : ");
        passengers=new Passengers(scanner.next(), scanner.next(),
                scanner.nextInt(), scanner.next(), scanner.nextDouble());
        passengerDBAccess.save(passengers);
    }



}
