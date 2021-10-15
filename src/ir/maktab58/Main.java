package ir.maktab58;

import ir.maktab58.dataBaseAccess.DriverDBAccess;
import ir.maktab58.dataBaseAccess.PassengerDBAccess;
import ir.maktab58.model.Admin;
import ir.maktab58.model.Drivers;
import ir.maktab58.model.Passengers;
import ir.maktab58.model.Vehicles;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        PassengerDBAccess passengerDBAccess = new PassengerDBAccess();
        DriverDBAccess driverDBAccess = new DriverDBAccess();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        boolean check;


       /* for(Object passengers: passengerDBAccess.showList()) {
            System.out.println(passengers);
        }*/
        System.out.println(passengerDBAccess.findByUserName(14));
        Admin admin = new Admin();
        System.out.println("enter userName :");
        if (admin.checkAdmin(scanner.next())) {
            do {
                System.out.println("Select of Item : ");
                choice =admin. menu();
                int id;
                switch (choice) {
                    case 1:
                        System.out.println("How many drivers do you want to add : ");
                        for (int i = 0; i < scanner.nextInt(); i++)
                            admin.addDriver();
                        break;
                    case 2:
                        System.out.println("How many passengers do you want to add : ");
                        for (int i = 0; i < scanner.nextInt(); i++)
                            admin.addPassengers();
                        break;
                    case 3:
                        System.out.println("enter userName : ");
                        id = scanner.nextInt();
                        choice = admin.checked(driverDBAccess.search(id), 3);
                        break;
                    case 4:
                        System.out.println("enter userName : ");
                        id = scanner.nextInt();
                        choice = admin.checked(passengerDBAccess.search(id), 4);
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
    }




}
