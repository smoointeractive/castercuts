package com.smoointeractive.project.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.smoointeractive.project.client.DataService;

/**
 * Created by sachamoo on 4/19/17.
 */
public class DataServiceImpl extends RemoteServiceServlet implements DataService{
    @Override
    public String LoadData() throws IllegalArgumentException {
        return null;
    }
}
