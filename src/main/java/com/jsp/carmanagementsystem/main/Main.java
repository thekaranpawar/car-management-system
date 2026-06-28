package com.jsp.carmanagementsystem.main;

import java.util.Scanner;
import com.jsp.carmanagementsystem.dao.CarDao;
import com.jsp.carmanagementsystem.dao.UserDao;
import com.jsp.carmanagementsystem.exception.CarNotFoundException;
import com.jsp.carmanagementsystem.utility.DBConnection;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // 🔐 LOGIN SYSTEM
        UserDao userDao = new UserDao(DBConnection.getConnection());

        System.out.println("===== LOGIN =====");

        System.out.print("Enter Username: ");
        String username = sc.next();

        System.out.print("Enter Password: ");
        String password = sc.next();

        String role = userDao.login(username, password);

        if (role == null) {
            System.out.println("Invalid credentials!");
            sc.close();
            return;
        }

        System.out.println("Login Successful! Role: " + role);

        CarDao dao = new CarDao();
        boolean flag = true;

        //  Admin access
        if (role.equals("ADMIN")) {

            while (flag) {

                System.out.println("\n===== ADMIN MENU =====");
                System.out.println("1. Insert Car");
                System.out.println("2. Fetch all cars");
                System.out.println("3. Search car");
                System.out.println("4. Update car");
                System.out.println("5. Delete car");
                System.out.println("6. EXIT");

                int choice = sc.nextInt();

                switch (choice) {

                    case 1:
                        dao.insertCar();
                        break;

                    case 2:
                        try {
                            dao.allCar();
                        } catch (CarNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 3:
                        try {
                            dao.searchCar();
                        } catch (CarNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 4:
                        dao.updateCar();
                        break;

                    case 5:
                        dao.deleteCar();
                        break;

                    case 6:
                        System.out.println("Are you sure:\n1. Yes\n2. No");
                        int s = sc.nextInt();
                        if (s == 1) {
                            flag = false;
                            System.out.println("Thank You......");
                        }
                        break;

                    default:
                        System.out.println("Invalid choice");
                }
            }
        }

        // User access (Read only)
        else if (role.equals("USER")) {

            while (flag) {

                System.out.println("\n===== USER MENU =====");
                System.out.println("1. Fetch all cars");
                System.out.println("2. Search car");
                System.out.println("3. EXIT");

                int choice = sc.nextInt();

                switch (choice) {

                    case 1:
                        try {
                            dao.allCar();
                        } catch (CarNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 2:
                        try {
                            dao.searchCar();
                        } catch (CarNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 3:
                        System.out.println("Are you sure:\n1. Yes\n2. No");
                        int s = sc.nextInt();
                        if (s == 1) {
                            flag = false;
                            System.out.println("Thank You......");
                        }
                        break;

                    default:
                        System.out.println("Invalid choice");
                }
            }
        }

        sc.close();
    }
}