package ir.maktab58.model;

import ir.maktab58.enums.StatusTravel;
import ir.maktab58.dataBaseAccess.DriverDBAccess;
import ir.maktab58.dataBaseAccess.PassengerDBAccess;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin {
    private static  String userName="admin";
    private  static String password="admin";
    Scanner scanner = new Scanner(System.in);
    private PassengerDBAccess passengerDBAccess = new PassengerDBAccess();
    private DriverDBAccess driverDBAccess = new DriverDBAccess();
    private Drivers drivers = new Drivers();
    private  Vehicles vehicles = new Vehicles();
    private Passengers passengers = new Passengers();

    public Admin() throws SQLException, ClassNotFoundException {
    }

    public  boolean checkAdmin(String input) {
        boolean check=false;
        int select=0;

        do{
            if (input.equals(userName)) {
                System.out.println("enter password :");
                if (scanner.next().equals(password)) {
                    check = true;
                    break;
                } else {
                    System.out.println("--error-- ");
                    System.out.println("1.try again    2.exit");
                    select = scanner.nextInt();
                }
            }

        }while(select!=2) ;
        return check;
    }
    public  int menu() {
        int choice = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("1.Add a group of drivers \n" +
                "2.Add a group of passengers \n" +
                "3.Driver signup or login \n" +
                "4.Passenger signup or login \n" +
                "5.Show a list of drivers \n" +
                "6.Show a list of passengers\n" +
                "7.Exit ");
        String input = scanner.nextLine();
        try {
            choice = isValid(input);
        } catch (RuntimeException runtimeExc) {
            System.out.println(runtimeExc.getMessage());
        }
        return choice;
    }
    public  int isValid(String input) {
        if (input.equals("")) {
            throw new NullPointerException("--value is Null--");
        }
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) {
                throw new InputMismatchException("--value is not digit-- ");
            }
        }
        if (input.length() == 1) {
            int number = Integer.parseInt(input);
            if (number < 1 || number > 7)
                throw new ArrayIndexOutOfBoundsException("--value is out of bands--");
        }
        if (input.length() > 1) {
            throw new ArrayIndexOutOfBoundsException("--value is not valid--");
        }

        return Integer.parseInt(input);
    }

    public  void addDriver() throws SQLException, ClassNotFoundException {
        String vehiclesType;
       System.out.println("enter name & family & userName & phoneNumber & balance :");
        String name = scanner.next();
        String family = scanner.next();
        int id = scanner.nextInt();
        String phone = scanner.next();
        double balance = scanner.nextDouble();
        System.out.println("enter vehicles 1.car   2.motor    3.vanet    4.van");
        switch (scanner.nextInt()) {
            case 1:
                vehiclesType = "Car";
                System.out.println("enter name & model & color & plateNumber of Car");
                vehicles = drivers.addVehicles(vehiclesType, scanner.next(), scanner.nextInt(),
                        scanner.next(), scanner.next());
                break;
            case 2:
                vehiclesType = "Motor";
            case 3:
                vehiclesType = "Vanet";
            case 4:
                vehiclesType = "Van";
                System.out.println("YET STILL THERE IS NOT IN SYSTEM");
                break;

        }


        drivers = new Drivers(name, family, id, phone, balance, StatusTravel.WAITING, vehicles,null);
        DriverDBAccess driverDBAccess = new DriverDBAccess();
        driverDBAccess.save(drivers);

    }
    public  void addPassengers() throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        PassengerDBAccess passengerDBAccess = new PassengerDBAccess();
        System.out.println("enter  name & family & userName & phoneNumber & balance : ");
        passengers = new Passengers(scanner.next(), scanner.next(),
                scanner.nextInt(), scanner.next(), scanner.nextDouble(), StatusTravel.WAITING);
        passengerDBAccess.save(passengers);
    }
    public  int checked(Integer check, int item) throws SQLException, ClassNotFoundException {
        int choice = 0;
        Scanner scanner = new Scanner(System.in);
        if (check != null) {
            System.out.println("1.Increase account balance \n" +
                    "2.Exit\n");
            choice = scanner.nextInt();
            if (choice == 1 && item == 3) {
                DriverDBAccess driverDBAccess = new DriverDBAccess();
                System.out.println("enter amount for update balance");
                driverDBAccess.updateBalance(check, Double.parseDouble(scanner.next()));
            } else if (choice == 1 && item == 4) {
                PassengerDBAccess passengerDBAccess = new PassengerDBAccess();
                System.out.println("enter amount for update balance");
                passengerDBAccess.updateBalance(check, Double.parseDouble(scanner.next()));
            } else if (choice == 2) {
                choice = menu();
            }
        } else {
            System.out.println("1.Register \n" +
                    "2.Exit");
            choice = scanner.nextInt();
            if (choice == 1 && item == 3) {
                addDriver();
            } else if (choice == 1 && item == 4) {
                addPassengers();
            } else if (choice == 2) {
                choice = menu();
            }
        }

        return choice;
    }

}
