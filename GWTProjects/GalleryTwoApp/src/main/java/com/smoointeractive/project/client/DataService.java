package com.smoointeractive.project.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.smoointeractive.project.shared.AvailableDatabases;
import com.smoointeractive.project.shared.DatabaseConnectionResponse;
import com.smoointeractive.project.shared.DummyBookModel;
import com.smoointeractive.project.shared.ImageGalleryDataModel;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by sachamoo on 4/19/17.
 */
@RemoteServiceRelativePath("getdata")
public interface DataService extends RemoteService {
    DatabaseConnectionResponse LoadData(AvailableDatabases db);
    ArrayList<ImageGalleryDataModel> GetImageGalleryData();
    ArrayList<DummyBookModel> GetDummyBookData();
}
