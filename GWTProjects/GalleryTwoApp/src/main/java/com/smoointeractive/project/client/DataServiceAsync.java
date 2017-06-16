package com.smoointeractive.project.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smoointeractive.project.shared.AvailableDatabases;
import com.smoointeractive.project.shared.DatabaseConnectionResponse;
import com.smoointeractive.project.shared.DummyBookModel;
import com.smoointeractive.project.shared.ImageGalleryDataModel;

import java.util.ArrayList;

/**
 * Created by sachamoo on 4/19/17.
 */
public interface DataServiceAsync {
    void LoadData(AvailableDatabases db, AsyncCallback<DatabaseConnectionResponse> callback);
    void GetImageGalleryData(AsyncCallback<ArrayList<ImageGalleryDataModel>> callback);
    void GetDummyBookData(AsyncCallback<ArrayList<DummyBookModel>> callback);
}
