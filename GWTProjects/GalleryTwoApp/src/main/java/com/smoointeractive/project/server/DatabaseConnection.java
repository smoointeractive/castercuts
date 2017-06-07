package com.smoointeractive.project.server;

import com.smoointeractive.project.shared.AvailableDatabases;
import com.smoointeractive.project.shared.DummyBookModel;
import com.smoointeractive.project.shared.ImageGalleryDataModel;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

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
           InitializeDatabaseConnection connection = new InitializeDatabaseConnection();
           databaseConnection = connection.getDataConnection();

            if(databaseConnection == null) {
                System.out.println("----->>>" + databaseConnection.getClientInfo());
                return "databaseConnnection is invalid!";
            }

            Statement statement = databaseConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM "+table);

            int callCount = 0;

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
//                System.out.println("************** count is " + callCount);

                if(galleryDatabase == table) {
//                    System.out.println("gallery data");
                    populateImageGalleryList(resultSet);
                } else if(dummyDatabase == table){
//                    System.out.println("dummy book data");
                    populateDummyBookList(resultSet);
                }
                callCount++;
            }

            databaseConnection.close();

//            System.out.println(imageGalleryList.size());
//            System.out.println(imageGalleryList.get(1).getName());
//            System.out.println(imageGalleryList.get(2));

            connectionResult = "Connected to Database.";

        } catch (SQLException e) {
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
        int imageBlobDataLength = (int)imageBlobData.length();
        byte[] imageBlobDataBytes = imageBlobData.getBytes(1, imageBlobDataLength);
        // store blob image data as base64 image string
        String base64Prefix = "data:image/jpg;base64,";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(base64Prefix);
        stringBuilder.append(
                StringUtils.newStringUtf8(Base64.encodeBase64(imageBlobDataBytes, false))
        );
        String base64ImageResult = stringBuilder.toString();
//        System.out.println(imageBlobData.length());
//        System.out.println(base64ImageResult);
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
