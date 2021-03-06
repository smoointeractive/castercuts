package com.smoointeractive.project.server;

import com.smoointeractive.project.shared.AvailableDatabases;
import com.smoointeractive.project.shared.DatabaseConnectionResponse;
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
    private ResultSet resultSet;

    public DatabaseConnection()
    {
        imageGalleryList = new ArrayList<>();
        dummyBookImageList = new ArrayList<>();
    }

    public DatabaseConnectionResponse ConnectToDatabase(AvailableDatabases db) throws SQLException {
        DatabaseConnectionResponse connectionResponse = DatabaseConnectionResponse.FAILURE;

        switch(db)
        {
            case GALLERY:
                resultSet = connectToTable(galleryDatabase);
                populateImageGalleryList(resultSet);
                connectionResponse = DatabaseConnectionResponse.SUCCESS;
                break;
            case DUMMY:
                resultSet = connectToTable(dummyDatabase);
                populateDummyBookList(resultSet);
                connectionResponse = DatabaseConnectionResponse.SUCCESS;
                break;
            default:
                break;
        }
        return connectionResponse;
    }

    private ResultSet connectToTable(String table) {
        try
        {
           InitializeDatabaseConnection connection = new InitializeDatabaseConnection();
           databaseConnection = connection.getDataConnection();

            if(null == databaseConnection) {
                System.out.println("----->>>" + databaseConnection.getClientInfo());
                System.out.println( "databaseConnnection is invalid!");
            }

            Statement statement = databaseConnection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM " + table);

            return resultSet;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error connecting to database - not good eh. Error message: " + e.toString());
            return null;
        }
    }

    private void populateImageGalleryList(ResultSet resultSet) throws SQLException {

        while(resultSet.next()) {
            ImageGalleryDataModel imageGalleryDataModel = new ImageGalleryDataModel();
            imageGalleryDataModel.setId(resultSet.getInt("id"));
            imageGalleryDataModel.setName(resultSet.getString("name"));
            imageGalleryDataModel.setDescription(resultSet.getString("description"));
            imageGalleryDataModel.setImageurl(resultSet.getString("imageurl"));

            // convert image blog data to base64 string
            Blob imageBlobData = resultSet.getBlob("thumbnail");
            int imageBlobDataLength = (int) imageBlobData.length();
            byte[] imageBlobDataBytes = imageBlobData.getBytes(1, imageBlobDataLength);

            // store blob image data as base64 image string
            String base64Prefix = "data:image/jpg;base64,";
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(base64Prefix);
            stringBuilder.append(
                    StringUtils.newStringUtf8(Base64.encodeBase64(imageBlobDataBytes, false))
            );
            String base64ImageResult = stringBuilder.toString();
            imageGalleryDataModel.setThumbnail(base64ImageResult);

            imageGalleryList.add(imageGalleryDataModel);
            imageBlobData.free();
        }
    }

    private void populateDummyBookList(ResultSet resultSet) throws SQLException {
        while(resultSet.next()) {
            DummyBookModel dummyBookModel = new DummyBookModel();
            dummyBookModel.setId(resultSet.getInt("id"));
            dummyBookModel.setName(resultSet.getString("name"));
            dummyBookModel.setDescription(resultSet.getString("description"));
            dummyBookModel.setImageurl(resultSet.getString("imageurl"));
            dummyBookImageList.add(dummyBookModel);
        }
    }

    public ArrayList<ImageGalleryDataModel> GetImageGalleryList()
    {
        System.out.println("GetImageGalleryList called");
        return imageGalleryList;
    }
    public ArrayList<DummyBookModel> GetDummyBookImageList()
    {
        System.out.println("GetDummyBookImageList called");
        return dummyBookImageList;
    }
}
