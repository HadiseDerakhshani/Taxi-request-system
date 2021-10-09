package ir.maktab58.model;

import java.util.Scanner;

public class Admin {
    private static  String userName="admin";
    private  static String password="admin";
    public  boolean checkAdmin(String input) {
        boolean check=false;
        int select=0;
        Scanner scanner = new Scanner(System.in);
        do{
            if (input.equals(userName)) {
                System.out.println("enter password :");
                if (scanner.next().equals(password)) {
                    check = true;
                    break;
                }
            }
            else{ System.out.println("--error-- ");
                System.out.println("1.try again    2.exit");
                select=scanner.nextInt();
            }

        }while(select!=2) ;
        return check;
    }
}
