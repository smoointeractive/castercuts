package com.smoointeractive.project.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Created by sachamoo on 4/19/17.
 */
@RemoteServiceRelativePath("getdata")
public interface DataService extends RemoteService {
    String LoadData() throws IllegalArgumentException;
}
