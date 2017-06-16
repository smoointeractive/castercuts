package com.smoointeractive.project.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.smoointeractive.project.client.DataService;
import com.smoointeractive.project.shared.AvailableDatabases;
import com.smoointeractive.project.shared.DatabaseConnectionResponse;
import com.smoointeractive.project.shared.DummyBookModel;
import com.smoointeractive.project.shared.ImageGalleryDataModel;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by sachamoo on 4/19/17.
 */
public class DataServiceImpl extends RemoteServiceServlet implements DataService{

    public DatabaseConnection databaseConnection;

    @Override
    public DatabaseConnectionResponse LoadData(AvailableDatabases db) {
        databaseConnection = new DatabaseConnection();
        DatabaseConnectionResponse databaseInitializationResult = null;
        try {
            databaseInitializationResult = databaseConnection.ConnectToDatabase(db);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return databaseInitializationResult;
    }

    @Override
    public ArrayList<ImageGalleryDataModel> GetImageGalleryData() {
        return databaseConnection.GetImageGalleryList();
    }

    @Override
    public ArrayList<DummyBookModel> GetDummyBookData() { return databaseConnection.GetDummyBookImageList(); }
}
