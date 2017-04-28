package com.smoointeractive.project.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smoointeractive.project.shared.AvailableDatabases;
import com.smoointeractive.project.shared.ImageGalleryDataModel;

import java.util.ArrayList;

/**
 * Created by sachamoo on 4/19/17.
 */
public interface DataServiceAsync {
    void LoadData(AvailableDatabases db, AsyncCallback<String> callback);
    void GetData(AsyncCallback<ArrayList<ImageGalleryDataModel>> callback);
}
