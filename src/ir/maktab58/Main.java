package ir.maktab58;

import ir.maktab58.dataBaseAccess.DriverDBAccess;
import ir.maktab58.dataBaseAccess.PassengerDBAccess;
import ir.maktab58.model.Drivers;
import ir.maktab58.model.Passengers;
import ir.maktab58.model.Vehicles;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static Drivers drivers = new Drivers();
    public static Vehicles vehicles = new Vehicles();
    public static Passengers passengers = new Passengers();

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        PassengerDBAccess passengerDBAccess = new PassengerDBAccess();
        DriverDBAccess driverDBAccess = new DriverDBAccess();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        boolean check;

        do {
            System.out.println("Select of Item : ");
            choice = menu();
            int id;
            switch (choice) {
                case 1:
                    addDriver();
                    break;
                case 2:
                    addPassengers();
                    break;
                case 3:
                    System.out.println("enter userName : ");
                    id = scanner.nextInt();
                    choice = checked(driverDBAccess.search(id), 3);
                    break;
                case 4:
                    System.out.println("enter userName : ");
                    id = scanner.nextInt();
                    choice = checked(passengerDBAccess.search(id), 4);
                    break;
                case 5:
                    driverDBAccess.showList();
                    break;
                case 6:
                    passengerDBAccess.showList();
                    break;
                case 7:
                    break;
            }
        } while (choice != 7);
    }

    public static int menu() {
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

    public static void addDriver() throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
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


        drivers = new Drivers(name, family, id, phone, balance, vehicles);
        DriverDBAccess driverDBAccess = new DriverDBAccess();
        driverDBAccess.save(drivers);

    }

    public static void addPassengers() throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        PassengerDBAccess passengerDBAccess = new PassengerDBAccess();
        System.out.println("enter  name & family & userName & phoneNumber & balance : ");
        passengers = new Passengers(scanner.next(), scanner.next(),
                scanner.nextInt(), scanner.next(), scanner.nextDouble());
        passengerDBAccess.save(passengers);
    }

    public static int isValid(String input) {
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

    public static int checked(Integer check, int item) throws SQLException, ClassNotFoundException {
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
