package com.smoointeractive.project.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Created by sachamoo on 4/19/17.
 */
public interface DataServiceAsync {
    void LoadData(AsyncCallback<String> callback) throws IllegalArgumentException;
}
