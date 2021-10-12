package ir.maktab58.model;

import ir.maktab58.dataBaseAccess.TripDBAccess;
import ir.maktab58.enums.StatusTravel;
import ir.maktab58.dataBaseAccess.DriverDBAccess;
import ir.maktab58.dataBaseAccess.PassengerDBAccess;
import ir.maktab58.enums.StatusTrip;

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
    private Trip trip=new Trip();
  private TripDBAccess tripDBAccess=new TripDBAccess();
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
    public  int checked(Integer checkid, int item) throws SQLException, ClassNotFoundException {
        int choice = 0;
        int check=0;
        if (checkid != null) {
            if ( item == 3) {
               // DriverDBAccess driverDBAccess = new DriverDBAccess();
                driverDBAccess.showDriver(checkid);
                if(check==1){
            System.out.println("1.Confirm cash receipt\n" +
                    "2.Travel finished\n" +
                    "3.Exit");
                }else
                    System.out.println("1.Travel finished\n" +
                            "2.Exit");
                      choice = scanner.nextInt();
              //  System.out.println("enter amount for update balance");
               // driverDBAccess.updateBalance(checkid, Double.parseDouble(scanner.next()));
            } else if ( item == 4) {
               // PassengerDBAccess passengerDBAccess = new PassengerDBAccess();
                passengerDBAccess.showPassenger(checkid);

                System.out.println("1.Travel request (pay by cash)\n" +
                        "2.Travel request (pay by account balance)\n" +
                        "3.Increase account balance\n" +
                        "4.Exit ");
                choice = scanner.nextInt();
                   switch (choice){
                       case 1:
                           requestTravel(passengerDBAccess.findByUserName(checkid),1);
                           check=1;
                           break;
                       case 2:
                           requestTravel(passengerDBAccess.findByUserName(checkid),2);
                            check=0;
                           break;
                       case 3:
                             System.out.println("enter amount for update balance");
                             passengerDBAccess.updateBalance(checkid, Double.parseDouble(scanner.next()));
                           break;
                       case 4:
                           break;
                   }
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
public int requestTravel(Passengers passengers,int select) throws SQLException {
        int amount=0;
        String locationOrigin;
    String locationDestination;
    System.out.println("Enter the origin of your travel (x000,y000): ");
    locationOrigin=scanner.next();
    System.out.println("Enter the destination of your travel (x000,y000): ");
    locationDestination=scanner.next();
    String[] splitOrigin=locationOrigin.split(",");
    String[] splitDestination=locationDestination.split(",");
   amount=1000*((Integer.parseInt(splitDestination[0])-Integer.parseInt(splitOrigin[0]))+
           (Integer.parseInt(splitDestination[0])-Integer.parseInt(splitOrigin[0])));
    drivers=driverDBAccess.findByLocation(locationOrigin);
    trip=new Trip(locationOrigin,locationDestination, StatusTrip.DOING,drivers,passengers);
     if(select==2){
       if (passengerDBAccess.checkBalance(passengers,amount))
           tripDBAccess.save(trip);
     }else tripDBAccess.save(trip);
   return amount;
}

}
