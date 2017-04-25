package com.smoointeractive.project.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.smoointeractive.project.client.DataService;
import com.smoointeractive.project.shared.ImageGalleryDataModel;

import java.util.ArrayList;

/**
 * Created by sachamoo on 4/19/17.
 */
public class DataServiceImpl extends RemoteServiceServlet implements DataService{

    public DatabaseConnection databaseConnection;

    @Override
    public String LoadData() {
        databaseConnection = new DatabaseConnection();
        String databaseInitializationResult = databaseConnection.ConnectToDatabase();

        return databaseInitializationResult;
    }

    @Override
    public ArrayList<ImageGalleryDataModel> GetData() {
        return databaseConnection.GetImageGalleryList();
    }
}
