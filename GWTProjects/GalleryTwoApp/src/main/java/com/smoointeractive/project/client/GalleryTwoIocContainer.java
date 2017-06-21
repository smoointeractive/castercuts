package com.smoointeractive.project.client;

import com.google.gwt.core.client.GWT;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * Created by sachamoo on 6/15/17.
 */
public class GalleryTwoIocContainer {
    private static GalleryTwoIocContainer sInstance = null;
    private HashMap<String, Object>objectInstanceTable;

    public GalleryTwoIocContainer()
    {
        objectInstanceTable = new HashMap<>();
        objectInstanceTable.put("logger", Logger.getLogger(""));
        objectInstanceTable.put("dataservice", GWT.create(DataService.class));
        objectInstanceTable.put("dataloader", new DataLoader(
                (DataServiceAsync) objectInstanceTable.get("dataservice"),
                (Logger) objectInstanceTable.get("logger")
        ));
    }

    public static GalleryTwoIocContainer GetInstance()
    {
        if(null == sInstance) {
            sInstance = new GalleryTwoIocContainer();
        }

        return sInstance;
    }

    public Object Resolve(String objectName)
    {
        return objectInstanceTable.get(objectName);
    }
}
