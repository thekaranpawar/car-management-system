package com.jsp.carmanagementsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {

    Connection con;

    public UserDao(Connection con) {
        this.con = con;
    }

    public String login(String username, String password) {

        String role = null;

        String query = "SELECT role FROM users WHERE username = ? AND password = ?";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                role = rs.getString("role");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return role;
    }
}