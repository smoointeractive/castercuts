package com.smoointeractive.project.server;

import com.smoointeractive.project.shared.AvailableDatabases;
import com.smoointeractive.project.shared.DummyBookModel;
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
    private ArrayList<DummyBookModel> dummyBookImageList;
    private static final String galleryDatabase = "imagegallerydb";
    private static final String dummyDatabase = "dummybookdb";

    public DatabaseConnection()
    {
        imageGalleryList = new ArrayList<>();
        dummyBookImageList = new ArrayList<>();
    }

    public String ConnectToDatabase(AvailableDatabases db)
    {
        String connectionResult = "Error connecting to selected database";
        switch(db)
        {
            case GALLERY:
                connectionResult = connectToTable(galleryDatabase);
                break;
            case DUMMY:
                connectionResult = connectToTable(dummyDatabase);
                break;
            default:
                break;
        }
        return connectionResult;
    }

    private String connectToTable(String table) {
        String connectionResult = "Error connecting to database";
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
//                switch(table) {
//                    case galleryDatabase:
//                        System.out.println("gallery data");
//                        populateImageGalleryList(resultSet);
//                        break;
//                    case dummyDatabase:
//                        System.out.println("dummy book data");
//                        populateDummyBookList(resultSet);
//                        break;
//                }
                if(galleryDatabase == table) {
                    System.out.println("gallery data");
                    populateImageGalleryList(resultSet);
                } else if(dummyDatabase == table){
                    System.out.println("dummy book data");
                    populateDummyBookList(resultSet);
                }
            }

            databaseConnection.close();

//            System.out.println(imageGalleryList.size());
//            System.out.println(imageGalleryList.get(1).getName());
//            System.out.println(imageGalleryList.get(2));

            connectionResult = "Connected to Database.";

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

        return connectionResult;
    }

    private void populateImageGalleryList(ResultSet resultSet) throws SQLException {
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

//        System.out.println(imageGalleryList.size());
//        System.out.println(imageGalleryList.get(1).getName());
//        System.out.println(imageGalleryList.get(2));
    }

    private void populateDummyBookList(ResultSet resultSet) throws SQLException {
        DummyBookModel dummyBookModel = new DummyBookModel();
        dummyBookModel.setId(resultSet.getInt("id"));
        dummyBookModel.setName(resultSet.getString("name"));
        dummyBookModel.setDescription(resultSet.getString("description"));
        dummyBookModel.setImageurl(resultSet.getString("imageurl"));

        dummyBookImageList.add(dummyBookModel);

//        System.out.println(dummyBookImageList.size());
//        System.out.println(dummyBookImageList.get(1).getName());
//        System.out.println(dummyBookImageList.get(2));
    }

    public ArrayList<ImageGalleryDataModel> GetImageGalleryList()
    {
        return imageGalleryList;
    }
    public ArrayList<DummyBookModel> GetDummyBookImageList() {return dummyBookImageList;}
}
