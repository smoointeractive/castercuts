package com.smoointeractive.project.server;

import com.smoointeractive.project.shared.AvailableDatabases;
import com.smoointeractive.project.shared.ImageGalleryDataModel;
import org.apache.commons.codec.binary.Base64;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by sachamoo on 4/24/17.
 */
public class DatabaseConnection {
    private Connection databaseConnection;
    private ArrayList<ImageGalleryDataModel> imageGalleryList;
    private static final String galleryDatabase = "imagegallerydb";
    private static final String dummyDatabase = "dummybookdb";

    public DatabaseConnection() {
        imageGalleryList = new ArrayList<>();
    }

    public String ConnectToDatabase(AvailableDatabases db)
    {
        String connectionResult = "Error connecting to selected database";
        switch(db)
        {
            case GALLERY:
                connectTo(galleryDatabase);
                break;
            case DUMMY:
                connectTo(dummyDatabase);
                break;
            default:
                break;
        }
        return connectionResult;
    }

    private String connectTo(String table) {
        try
        {
            String driver = "com.mysql.jdbc.Driver";
            String databaseUrl = "jdbc:mysql://localhost/imagedb";
            String user = "root";
            String password = "OB?A1a&-KGw1"; // temp

            Class.forName(driver).newInstance();
            databaseConnection = DriverManager.getConnection(databaseUrl,
                                                             user,
                                                             password);

            Statement statement = databaseConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM "+table);

            while(resultSet.next())
            {
                ImageGalleryDataModel imageGalleryDataModel = new ImageGalleryDataModel();
                imageGalleryDataModel.setId(resultSet.getInt("id"));
                imageGalleryDataModel.setName(resultSet.getString("name"));
                imageGalleryDataModel.setDescription(resultSet.getString("description"));
                imageGalleryDataModel.setImageurl(resultSet.getString("imageurl"));

                // convert image blog data to base64 string
                Blob imageBlobData = resultSet.getBlob("thumbnail");
                // store blob image data as base64 image string
                String base64Prefix = "data:image/jpg;base64,";
                String base64ImageResult = base64Prefix +
                                            Base64.encodeBase64String(
                                                    imageBlobData.getBytes(1,
                                                            (int)imageBlobData.length()));
                imageGalleryDataModel.setThumbnail(base64ImageResult);

                imageGalleryList.add(imageGalleryDataModel);
                imageBlobData.free();
            }

            databaseConnection.close();

            System.out.println(imageGalleryList.size());
            System.out.println(imageGalleryList.get(1).getName());
            System.out.println(imageGalleryList.get(2));

            return "Connected to Database.";

        } catch (IllegalAccessException e) {
            e.printStackTrace();
            System.out.println("Error connecting to database - not good eh. Error message: "+ e.toString());
            return e.toString();
        } catch (InstantiationException e) {
            e.printStackTrace();
            System.out.println("Error connecting to database - not good eh. Error message: "+ e.toString());
            return e.toString();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error connecting to database - not good eh. Error message: "+ e.toString());
            return e.toString();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error connecting to database - not good eh. Error message: "+ e.toString());
            return e.toString();
        }
    }

    public ArrayList<ImageGalleryDataModel> GetImageGalleryList()
    {
        return imageGalleryList;
    }
}
