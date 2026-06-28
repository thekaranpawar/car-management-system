package com.jsp.carmanagementsystem.dao;
import com.jsp.carmanagementsystem.exception.CarNotFoundException;
import com.jsp.carmanagementsystem.utility.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CarDao {

    Connection con = DBConnection.getConnection();
    Scanner sc = new Scanner(System.in);

    // insert new car
    public void insertCar() {

        String query = "INSERT INTO cars(name, color, brand, price, model, mileage, manufacturing_year, fuel_type) VALUES (?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pt = con.prepareStatement(query);

            System.out.println("Enter Name:");
            String name = sc.nextLine();

            System.out.println("Enter Color:");
            String color = sc.nextLine();

            System.out.println("Enter Brand:");
            String brand = sc.nextLine();

            System.out.println("Enter Price:");
            double price = sc.nextDouble();
            sc.nextLine();

            System.out.println("Enter Model:");
            String model = sc.nextLine();

            System.out.println("Enter Mileage:");
            int mileage = sc.nextInt();
            
            System.out.println("Enter Manufacturing Year:");
            int year = sc.nextInt();
            sc.nextLine();
            
            System.out.println("Enter Fuel type:");
            String fuel_type = sc.nextLine();

            pt.setString(1, name);
            pt.setString(2, color);
            pt.setString(3, brand);
            pt.setDouble(4, price);
            pt.setString(5, model);
            pt.setInt(6, mileage);
            pt.setInt(7, year);
            pt.setString(8, fuel_type);

            pt.executeUpdate();

            System.out.println("Car Inserted Successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
 // fetch all car
    public void allCar() {

        String query = "SELECT * FROM cars";

        try {
            PreparedStatement pt = con.prepareStatement(query);

            ResultSet rs = pt.executeQuery();

            while (rs.next()) {

                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Color: " + rs.getString("color"));
                System.out.println("Brand: " + rs.getString("brand"));
                System.out.println("Price: " + rs.getDouble("price"));
                System.out.println("Model: " + rs.getString("model"));
                System.out.println("Mileage: " + rs.getInt("mileage"));
                System.out.println("Manufacturing_year: " + rs.getInt("manufacturing_year"));
                System.out.println("Fuel type: " + rs.getString("fuel_type"));
                System.out.println("----------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // search car
    public void searchCar() {

        System.out.println("Search By:");
        System.out.println("1. Name");
        System.out.println("2. Brand");
        System.out.println("3. Color");
        System.out.println("4. Manufacturing Year");
        System.out.println("5. Fuel Type");

        int choice = sc.nextInt();
        sc.nextLine();

        String query = "";
        String value = "";

        switch (choice) {
            case 1:
                query = "SELECT * FROM cars WHERE LOWER(name) = LOWER(?)";
                System.out.print("Enter Car Name: ");
                value = sc.nextLine();
                break;

            case 2:
                query = "SELECT * FROM cars WHERE LOWER(brand) = LOWER(?)";
                System.out.print("Enter Brand: ");
                value = sc.nextLine();
                break;

            case 3:
                query = "SELECT * FROM cars WHERE LOWER(color) = LOWER(?)";
                System.out.print("Enter Color: ");
                value = sc.nextLine();
                break;

            case 4:
                query = "SELECT * FROM cars WHERE manufacturing_year = ?";
                System.out.print("Enter Manufacturing Year: ");
                value = sc.nextLine();
                break;

            case 5:
                query = "SELECT * FROM cars WHERE LOWER(fuel_type) = LOWER(?)";
                System.out.print("Enter Fuel Type: ");
                value = sc.nextLine();
                break;

            default:
                System.out.println("Invalid Choice");
                return;
        }

        try {
            PreparedStatement pt = con.prepareStatement(query);

            if (choice == 4) {
                pt.setInt(1, Integer.parseInt(value));
            } else {
                pt.setString(1, value);
            }

            ResultSet rs = pt.executeQuery();

            boolean found = false;

            while (rs.next()) {
                found = true;

                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Color: " + rs.getString("color"));
                System.out.println("Brand: " + rs.getString("brand"));
                System.out.println("Price: " + rs.getDouble("price"));
                System.out.println("Model: " + rs.getString("model"));
                System.out.println("Mileage: " + rs.getInt("mileage"));
                System.out.println("Manufacturing Year: " + rs.getInt("manufacturing_year"));
                System.out.println("Fuel Type: " + rs.getString("fuel_type"));
                System.out.println("----------------------------");
            }

            if (!found) {
                System.out.println("No matching cars found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
 // update car
    public void updateCar() {

        System.out.println("Enter ID:");
        int id = sc.nextInt();
        sc.nextLine();

        try {
            // Check if the car exists
            String checkQuery = "SELECT * FROM cars WHERE id = ?";
            PreparedStatement checkPt = con.prepareStatement(checkQuery);
            checkPt.setInt(1, id);

            ResultSet rs = checkPt.executeQuery();

            if (!rs.next()) {
                System.out.println("No car found with ID: " + id);
                return;
            }

            // Take updated details
            System.out.println("Enter Name:");
            String name = sc.nextLine();

            System.out.println("Enter Color:");
            String color = sc.nextLine();

            System.out.println("Enter Price:");
            double price = sc.nextDouble();

            System.out.println("Enter Mileage:");
            int mileage = sc.nextInt();

            System.out.println("Enter Manufacturing Year:");
            int year = sc.nextInt();

            // Update query
            String query = "UPDATE cars SET name=?, color=?, price=?, mileage=?, manufacturing_year=? WHERE id=?";

            PreparedStatement pt = con.prepareStatement(query);

            pt.setString(1, name);
            pt.setString(2, color);
            pt.setDouble(3, price);
            pt.setInt(4, mileage);
            pt.setInt(5, year);   // Correct: manufacturing_year
            pt.setInt(6, id);     // Correct: WHERE id

            int rows = pt.executeUpdate();

            if (rows > 0) {
                System.out.println("Car Updated Successfully.");
            } else {
                System.out.println("Update Failed.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // delete car
    public void deleteCar() {

        String query = "DELETE FROM cars WHERE id=?";

        try {
            PreparedStatement pt = con.prepareStatement(query);

            System.out.println("Enter Car ID:");
            int id = sc.nextInt();

            pt.setInt(1, id);

            int rows = pt.executeUpdate();

            if (rows > 0) {
                System.out.println("Deleted Successfully");
            } else {
                throw new CarNotFoundException("Car not found with ID: " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}