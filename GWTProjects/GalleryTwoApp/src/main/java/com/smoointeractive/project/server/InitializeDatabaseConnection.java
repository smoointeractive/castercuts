package com.smoointeractive.project.server;

import javax.servlet.ServletException;
import java.sql.Connection;
import java.lang.Exception;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by sachamoo on 6/3/17.
 */
public class InitializeDatabaseConnection {
    private static InitializeDatabaseConnection sInstance = null;
    private Connection dataConnection;
    private final String username = "root";
    private final String password = "7K5LJaNOchhJ6ex1";

    public InitializeDatabaseConnection()
    {
        try {
            initializeConnection();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static InitializeDatabaseConnection sGetInstance()
    {
        if(sInstance == null) {
            sInstance = new InitializeDatabaseConnection();
        }

        return sInstance;
    }

    private void initializeConnection() throws ServletException, SQLException {
        String url;
        if (System
                .getProperty("com.google.appengine.runtime.version").startsWith("Google App Engine/")) {
            // Check the System properties to determine if we are running on appengine or not
            // Google App Engine sets a few system properties that will reliably be present on a remote
            // instance.
            url = System.getProperty("ae-cloudsql.cloudsql-database-url");
            try {
                // Load the class that provides the new "jdbc:google:mysql://" prefix.
                Class.forName("com.mysql.jdbc.GoogleDriver");
            } catch (ClassNotFoundException e) {
                throw new ServletException("Error loading Google JDBC Driver", e);
            }
        } else {
            // Set the url with the local MySQL database connection url when running locally
            url = System.getProperty("ae-cloudsql.local-database-url");
        }

//        try (sInstance.dataConnection = DriverManager.getConnection(url))
        System.out.println("--->" + url);
        dataConnection = DriverManager.getConnection(url, username, password);
    }

    public Connection getDataConnection()
    {
        return dataConnection;
    }

}
