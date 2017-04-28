package com.smoointeractive.project.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.smoointeractive.project.shared.AvailableDatabases;
import com.smoointeractive.project.shared.ImageGalleryDataModel;

import java.util.ArrayList;

/**
 * Created by sachamoo on 4/19/17.
 */
@RemoteServiceRelativePath("getdata")
public interface DataService extends RemoteService {
    String LoadData(AvailableDatabases db);
    ArrayList<ImageGalleryDataModel> GetData();
}
