package com.jsp.carmanagementsystem.utility;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {

    private static Connection con;

    public static Connection getConnection() {

        if (con != null) {
            return con;
        }

        try {
            Properties props = new Properties();

            InputStream fis = DBConnection.class
                    .getClassLoader()
                    .getResourceAsStream("db.properties");

            if (fis == null) {
                throw new RuntimeException("db.properties NOT found in classpath");
            }

            props.load(fis);

            String url = props.getProperty("db.url");
            String username = props.getProperty("db.username");
            String password = props.getProperty("db.password");
            String driver = props.getProperty("db.driver");

            Class.forName(driver);

            con = DriverManager.getConnection(url, username, password);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }
}